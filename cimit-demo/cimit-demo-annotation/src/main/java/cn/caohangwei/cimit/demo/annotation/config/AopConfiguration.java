package cn.caohangwei.cimit.demo.annotation.config;

import cn.caohangwei.cimit.annotation.aspectj.CimitAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfiguration {

    @Bean
    public CimitAspect cimitAspect(){
        return new CimitAspect();
    }

}
