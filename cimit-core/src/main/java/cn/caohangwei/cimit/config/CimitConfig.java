package cn.caohangwei.cimit.config;

import cn.caohangwei.cimit.common.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class CimitConfig {

    private static final String PREFIX = "classpath:";

    private static final String DEFAULT_CIMIT_CONFIG_PATH = "classpath:cimit.properties";

    private static final String DEFAULT_URL = "localhost:8080";

    private static Properties properties = new Properties();

    static {
        load();
    }

    private static void load(){
        String filename = DEFAULT_CIMIT_CONFIG_PATH;
        loadProperties(filename);
    }

    private static void loadProperties(String filename){
        filename = filename.substring(PREFIX.length());
        List<URL> list = new ArrayList<>();
        try {
            Enumeration<URL> urls = CimitConfig.class.getClassLoader().getResources(filename);
            while(urls.hasMoreElements()){
                list.add(urls.nextElement());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(list.isEmpty()){
            Properties p = new Properties();
            p.setProperty(Constants.CIMIT_SERVER_URL,DEFAULT_URL);
        }
        for(URL url : list){
            Properties p = new Properties();
            try {
                InputStream in = url.openStream();
                p.load(in);
                properties.putAll(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
