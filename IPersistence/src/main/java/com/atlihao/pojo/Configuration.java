package com.atlihao.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lihao
 * @ClassName Configuration
 * @Since 2020/5/17
 * @Description 数据库连接配置信息：存放数据库基本信息、Map<唯一标识,Mapper> 唯一标识：namespace+"."+id
 * 对应的是sqlMapperConfig.xml
 */
public class Configuration {

    //数据源对象
    private DataSource dataSource;

    /**
     * key: statementId= namespace+"."+id   SQL的唯一标识
     * value: 封装好的MappedStatement对象
     */
    private Map<String, MappedStatement> MappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return MappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        MappedStatementMap = mappedStatementMap;
    }
}
