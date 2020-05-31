package com.atlihao.utils;

/**
 * @author lihao
 * @ClassName ParameterMapping
 * @Since 2020/5/17
 * @Description  #{username}中的username的参数映射类
 */
public class ParameterMapping {

    //#{username}中的username
    //解析出来的参数名称
    private String content;

    public ParameterMapping(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
