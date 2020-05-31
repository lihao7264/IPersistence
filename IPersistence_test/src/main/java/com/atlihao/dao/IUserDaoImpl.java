package com.atlihao.dao;

import com.atlihao.io.Resources;
import com.atlihao.pojo.User;
import com.atlihao.sqlSession.SqlSession;
import com.atlihao.sqlSession.SqlSessionFactory;
import com.atlihao.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lihao
 * @ClassName IUserDaoImpl
 * @Since 2020/5/23  dao层实现
 * @Description
 */
public class IUserDaoImpl implements IUserDao {

    @Override
    public List<User> findAll() throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException, PropertyVetoException, DocumentException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }
        return users;
    }

    @Override
    public User findByCondition(User user) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException, PropertyVetoException, DocumentException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user2 = sqlSession.selectOne("user.selectOne", user);
        System.out.println(user2);
        return user2;
    }
}
