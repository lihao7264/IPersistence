package com.atlihao.sqlSession;

import com.atlihao.pojo.Configuration;
import com.atlihao.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lihao
 * @ClassName DefaultSqlSession
 * @Since 2020/5/17
 * @Description 默认会话对象
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        //1、将要去完成对simpleExecutor的query方法的调用
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        Map<String, MappedStatement> mappedStatementMap = configuration.getMappedStatementMap();
        MappedStatement mappedStatement = mappedStatementMap.get(statementId);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空，或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //使用JDK动态代理来为dao接口生成代理对象，并返回
        //入参：类加载器、class数组、
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader()
                , new Class[]{mapperClass}
                , new InvocationHandler() {
                    /**
                     *
                     * @param proxy  当前代理对象的应用
                     * @param method  当前被调用方法的引用
                     * @param args    传递的参数（也就是user）
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        //底层都还是去执行JDBC代码  //根据不同情况来调用selectList或者selectOne
                        //准备参数 1：statementId：SQL语句的唯一标识。（namespace.id=接口全限定名.方法名）
                        //1.1 方法名：findAll
                        String methodName = method.getName();

                        //1.2 获取到该方法所在类的类名
                        String className = method.getDeclaringClass().getName();
                        String statementId = className + "." + methodName;

                        //准备参数2：params就是args
                        //2.1 获取被调用方法的返回值类型
                        Type genericReturnType = method.getGenericReturnType();
                        //2.2 判断是否进行了 泛型类型参数化（如果返回值有泛型，则认为就是一个集合，如果返回值没有泛型，则就是一个实体）
                        if (genericReturnType instanceof ParameterizedType) {
                            List<Object> objects = selectList(statementId, args);
                            return objects;
                        }
                        return selectOne(statementId, args);
                    }
                });
        return (T) proxyInstance;
    }

}
