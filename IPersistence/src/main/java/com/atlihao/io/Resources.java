package com.atlihao.io;

import java.io.InputStream;

/**
 * @author lihao
 * @ClassName Resources
 * @Since 2020/5/17
 * @Description 自定义持久层框架读取配置文件的类
 */
public class Resources {

    /**
     * 根据配置文件的路径，将配置文件加载成字节输入流，存储在内存中
     *
     * @param path
     * @return
     */
    public static InputStream getResourceAsStream(String path) {
        InputStream resourceAsStream = Resources.class.getClassLoader().getResourceAsStream(path);
        return resourceAsStream;
    }
}
