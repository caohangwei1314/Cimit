package cn.caohangwei.cimit.demo.annotation.service.impl;

import cn.caohangwei.cimit.annotation.Cimit;
import cn.caohangwei.cimit.demo.annotation.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestImpl implements TestService {

    @Override
    @Cimit(value = "demo", capacity = 10, rate = 10, downgrade = "downGrade")
    public String test(String name) {
        return "welcome " + name;
    }

    public String downGrade(String name) {
        return "downGrade " + name;
    }
}
