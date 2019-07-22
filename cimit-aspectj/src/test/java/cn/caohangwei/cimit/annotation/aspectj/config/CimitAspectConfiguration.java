package cn.caohangwei.cimit.annotation.aspectj.config;

import cn.caohangwei.cimit.annotation.aspectj.CimitAspect;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("cn.caohangwei.cimit.annotation.aspectj")
public class CimitAspectConfiguration {

    @Bean
    public CimitAspect cimitAspect() {
        return new CimitAspect();
    }
}
