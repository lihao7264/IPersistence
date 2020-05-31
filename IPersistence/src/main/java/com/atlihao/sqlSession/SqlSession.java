package com.atlihao.sqlSession;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lihao
 * @ClassName SqlSession
 * @Since 2020/5/17
 * @Description 会话接口
 */
public interface SqlSession {

    /**
     * 查询所有
     *
     * @param statementId SQL的唯一标识（namespace+"."+id）
     * @param params      可变入参
     * @return
     */
    public <E> List<E> selectList(String statementId, Object... params) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException;

    /**
     * 根据条件查询单个
     *
     * @param statementId
     * @param params      可变入参
     * @return
     */
    public <T> T selectOne(String statementId, Object... params) throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException;


    /**
     * 为dao接口生成代理实现类
     * @param mapperClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> mapperClass);
}
