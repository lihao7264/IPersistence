package com.atlihao.pojo;

import java.io.Serializable;

/**
 * @author lihao
 * @ClassName User
 * @Since 2020/5/17
 * @Description
 */
public class User implements Serializable {
    private static final long serialVersionUID = -5370284560026706237L;

    private Integer id;

    private String username;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
