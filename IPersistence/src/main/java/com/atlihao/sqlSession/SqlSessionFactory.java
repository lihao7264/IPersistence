package com.atlihao.sqlSession;

/**
 * @author lihao
 * @ClassName SqlSessionFactory
 * @Since 2020/5/17
 * @Description sqlSession工厂类
 */
public interface SqlSessionFactory {

    public SqlSession openSession();
}
