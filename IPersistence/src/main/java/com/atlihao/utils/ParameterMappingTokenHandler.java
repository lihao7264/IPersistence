package com.atlihao.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lihao
 * @ClassName ParameterMappingTokenHandler
 * @Since 2020/5/17
 * @Description 标记处理类：配合标记解析器来完成对占位符的解析处理工作
 */
public class ParameterMappingTokenHandler implements TokenHandler {
    //这里面
    private List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>();

    // context是参数名称 #{id} #{username}中的id或者username
    @Override
    public String handleToken(String content) {
        parameterMappings.add(buildParameterMapping(content));
        return "?";
    }

    private ParameterMapping buildParameterMapping(String content) {
        ParameterMapping parameterMapping = new ParameterMapping(content);
        return parameterMapping;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

}
