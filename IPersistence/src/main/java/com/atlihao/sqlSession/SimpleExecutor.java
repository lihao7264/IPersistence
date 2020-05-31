package com.atlihao.sqlSession;

import com.atlihao.config.BoundSql;
import com.atlihao.pojo.Configuration;
import com.atlihao.pojo.MappedStatement;
import com.atlihao.utils.GenericTokenParser;
import com.atlihao.utils.ParameterMapping;
import com.atlihao.utils.ParameterMappingTokenHandler;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lihao
 * @ClassName SimpleExecutor
 * @Since 2020/5/17
 * @Description 简单执行器类
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration,
                             MappedStatement mappedStatement,
                             Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException {
        //1、注册驱动,获取连接
        Connection connection = configuration.getDataSource().getConnection();

        //2、获取SQL语句: select * from user where id= #{id} and username= #{username}
        //2.1 转换sql语句(jdbc只识别?的占位符)： 上述SQL转换为 select * from user where id= ? and username= ? ，
        // 转换的过程中，还需要对#{}里面的值进行解析存储
        String sql = mappedStatement.getSql();

        BoundSql boundSql = getBoundSql(sql);

        //3、获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        //4、设置参数
        //4.1 获取到了参数的全路径类型
        String paramterType = mappedStatement.getParamterType();
        Class<?> paramtertypeClass = getClassType(paramterType);
        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            ParameterMapping parameterMapping = parameterMappingList.get(i);
            //#{username}中的username
            String content = parameterMapping.getContent();

            //反射
            //获取属性对象
            Field declaredField = paramtertypeClass.getDeclaredField(content);
            //暴力访问
            declaredField.setAccessible(Boolean.TRUE);
            //获取到对应属性对象的值
            Object o = declaredField.get(params[0]);
            //设置preparedStatement中的参数
            preparedStatement.setObject(i + 1, o);

        }
        //5、执行sql
        ResultSet resultSet = preparedStatement.executeQuery();

        //6、封装返回结果集
        //6.1 获取返回结果类型
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        List<Object> objects = new ArrayList<>();
        while (resultSet.next()) {
            Object o = resultTypeClass.newInstance();
            //元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //metaData.getColumnCount()：查询结果的列数
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                //字段名(获取的下标从1开始)
                String columnName = metaData.getColumnName(i);
                //获取到字段的值
                Object value = resultSet.getObject(columnName);

                //使用反射或者内省,根据数据库表和实体的对应关系，完成封装
                //入参为字段名+封装对象的class对象
                //内省会为这个对象的这个字段生成读写方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(o, value);
            }
            objects.add(o);
        }
        return (List<E>) objects;
    }

    //根据类型全路径获取对应的class类
    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            Class<?> aClass = Class.forName(paramterType);
            return aClass;
        }
        return null;
    }

    /**
     * 完成对#{}的解析工作：
     * 1、将#{}使用？进行代替。
     * 2、解析出#{}里面的值进行存储。
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配合标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        //标记解析器
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出来的sql：比如select * from user where id=? and username=?;
        String parseSql = genericTokenParser.parse(sql);
        //#{}里面解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}
