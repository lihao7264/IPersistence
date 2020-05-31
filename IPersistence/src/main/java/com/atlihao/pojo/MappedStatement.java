package com.atlihao.pojo;

/**
 * @author lihao
 * @ClassName MappedStatement
 * @Since 2020/5/17
 * @Description 存放UserMapper.xml这样的文件，主要包括sql语句、statement类型、输入参数java类型、输出参数java类型
 * 注意：每一个sql语句就是一个statement
 */
public class MappedStatement {

    //id标识
    private String id;

    //返回值类型
    private String resultType;

    //参数值类型
    private String paramterType;

    //SQL语句
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParamterType() {
        return paramterType;
    }

    public void setParamterType(String paramterType) {
        this.paramterType = paramterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
