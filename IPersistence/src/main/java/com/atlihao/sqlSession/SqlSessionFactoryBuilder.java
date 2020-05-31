package com.atlihao.sqlSession;

import com.atlihao.config.XMLConfigBuilder;
import com.atlihao.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author lihao
 * @ClassName SqlSessionFactoryBuilder
 * @Since 2020/5/17
 * @Description 完成第三步的解析动作和创建sqlSessionFactory动作
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream in) throws DocumentException, PropertyVetoException {
        //1、使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        //2、创建SqlSessionFactory对象:工厂类：生产SqlSession（也就是会话对象）
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }
}
