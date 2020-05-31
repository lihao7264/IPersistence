package com.atlihao.dao;

import com.atlihao.pojo.User;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lihao
 * @ClassName IUserDao
 * @Since 2020/5/23
 * @Description
 */
public interface IUserDao {

    //查询所有用户
    public List<User> findAll() throws IllegalAccessException, ClassNotFoundException, IntrospectionException, InstantiationException, SQLException, InvocationTargetException, NoSuchFieldException, PropertyVetoException, DocumentException;

    //根据条件进行用户查询
    public User findByCondition(User user) throws IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException, PropertyVetoException, DocumentException;
}
