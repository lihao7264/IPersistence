package com.atlihao.sqlSession;

import com.atlihao.pojo.Configuration;

/**
 * @author lihao
 * @ClassName DefaultSqlSessionFactory
 * @Since 2020/5/17
 * @Description
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
