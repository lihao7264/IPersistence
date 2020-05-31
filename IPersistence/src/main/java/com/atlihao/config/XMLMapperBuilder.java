package com.atlihao.config;

import com.atlihao.pojo.Configuration;
import com.atlihao.pojo.MappedStatement;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author lihao
 * @ClassName XMLMapperBuilder
 * @Since 2020/5/17
 * @Description mapper文件解析
 */
public class XMLMapperBuilder {

    /**
     * 配日志
     */
    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {
        Document document = new SAXReader().read(inputStream);
        //1、获取根对象（也就是<mapper></mapper>）
        Element rootElement = document.getRootElement();
        //1.1 获取namespace的值
        String namespace = rootElement.attributeValue("namespace");

        //2、获取到所有<select></select>>
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            //2.1、获取到id属性
            String id = element.attributeValue("id");

            //2.2、获取到resultType属性
            String resultType = element.attributeValue("resultType");

            //2.3、获取到paramterType属性
            String paramterType = element.attributeValue("paramterType");

            //2.4、获取到id属性
            //去除两边空格，拿到element的信息
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);

            //2.5 key值等于=namespace + "." + id  ，也就是statementId
            String key = namespace + "." + id;
            configuration.getMappedStatementMap().put(key, mappedStatement);
        }
    }

}
