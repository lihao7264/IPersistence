package com.atlihao.config;

import com.atlihao.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lihao
 * @ClassName BoundSql
 * @Since 2020/5/17
 * @Description 存放转换过后的sql语句以及解析过程中对#{}解析出来的里面的参数名称
 */
public class BoundSql {

    //解析过后的sql
    private String sqlText;

    //从#{}中解析出来的里面的参数名称
    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
