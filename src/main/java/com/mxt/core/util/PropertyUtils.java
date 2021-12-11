package com.mxt.core.util;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Desc:properties文件获取工具类
 */
public class PropertyUtils {
    private Properties props;
    private static Logger logger = Logger.getLogger(PropertyUtils.class);
    
    public static void main(String[] args) {
//        PropertyUtils propertyUtils = new PropertyUtils();
//        propertyUtils.setProperty("driverClassName1", "com.xxx.xxxxx11111");
    }
    
    synchronized private void loadProps() {
//        logger.info("开始加载properties文件内容.......");
        props = new Properties();
        InputStream in = null;
        try {
//            第一种，通过类加载器进行获取properties文件流
            in = PropertyUtils.class.getClassLoader().getResourceAsStream("setting.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
            logger.error("jdbc.properties文件未找到");
        } catch (IOException e) {
            logger.error("出现IOException");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("出现IOException");
            }
        }
//        logger.info("加载properties文件内容完成...........");
    }
    
    public void reloadProperty(){
        loadProps();
    }
    
    public Set<String> getAllPropertyNames() {
        if (null == props) {
            loadProps();
        }
        return props.stringPropertyNames();
    }
    
    public String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        String property = props.getProperty(key);
        if (property != null) {
            return property;
        } else {
            return "0";
        }
    }
    
    public String getProperty(String key, String defaultValue) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
    
    public void setProperty(String key, String value) {
        loadProps();
//        System.err.println(props);
        props.setProperty(key, value);
        try {
            // 从输入流中读取属性列表（键和元素对）
            props.setProperty(key, value);
            //Global.class为当前类名,project.properties为路径
            String path = PropertyUtils.class.getResource("/setting.properties").getPath();
            FileOutputStream outputFile = new FileOutputStream(path);
            props.store(outputFile, "modify");
            outputFile.flush();
            outputFile.close();
            loadProps();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}