package com.atlihao.sqlSession;

import com.atlihao.pojo.Configuration;
import com.atlihao.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lihao
 * @ClassName Executor
 * @Since 2020/5/17
 * @Description 执行器接口
 */
public interface Executor {

    /**
     * 查询相关的实现
     *
     * @param configuration   数据库连接配置
     * @param mappedStatement SQL语句
     * @param params          入参
     * @param <E>
     * @return
     */
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, IntrospectionException, InstantiationException, InvocationTargetException;
}
