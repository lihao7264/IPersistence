package com.atlihao.test;

import com.atlihao.dao.IUserDao;
import com.atlihao.io.Resources;
import com.atlihao.pojo.User;
import com.atlihao.sqlSession.SqlSession;
import com.atlihao.sqlSession.SqlSessionFactory;
import com.atlihao.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lihao
 * @ClassName IPersistenceTest
 * @Since 2020/5/17
 * @Description
 */
public class IPersistenceTest {

    /**
     * 测试方法
     *
     * @throws PropertyVetoException
     * @throws DocumentException
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws InstantiationException
     * @throws NoSuchFieldException
     * @throws SQLException
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     */
    @Test
    public void test() throws PropertyVetoException, DocumentException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user.setUsername("李豪");
//        User user2 = sqlSession.selectOne("user.selectOne", user);
//        System.out.println(user2);
//
//        List<User> users = sqlSession.selectList("user.selectList");
//        for (User user1 : users) {
//            System.out.println(user1);
//        }

        //代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findByCondition(user);
        System.out.println(user2);

        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }

    }
}
