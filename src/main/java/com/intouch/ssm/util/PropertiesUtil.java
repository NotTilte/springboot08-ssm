package com.intouch.ssm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    //定义Properties文件的路径
    private  static  final  String PATN="serviceMethods.properties";
    //针对Properties文件定义一个空的Properties对象
    private  static  Properties propas=new Properties();

    //使用propertie文件填充properties对象
    static {
        //或当前类的类加载器
        ClassLoader classLoader=PropertiesUtil.class.getClassLoader();
        //使用类加载器，加载properties文件进入内存中，行程io流
        InputStream is=classLoader.getResourceAsStream(PATN);

        try {
            propas.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //私有化构造器
    private PropertiesUtil(){}

    //定义一个静态的工具方法
    public static String getProperyValue(String key){
        return propas.getProperty(key);
    }
}
