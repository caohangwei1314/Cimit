package cn.caohangwei.cimit.config;

import cn.caohangwei.cimit.common.Constants;
import org.junit.jupiter.api.Test;

import java.util.Properties;


class CimitConfigTest {

    @Test
    void load(){
        Properties properties = CimitConfig.getProperties();
        System.out.println(properties.getProperty(Constants.CIMIT_SERVER_URL));
    }

}