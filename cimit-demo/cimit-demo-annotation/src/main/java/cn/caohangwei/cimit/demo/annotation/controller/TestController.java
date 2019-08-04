package cn.caohangwei.cimit.demo.annotation.controller;

import cn.caohangwei.cimit.demo.annotation.service.TestService;
import cn.caohangwei.cimit.demo.annotation.service.impl.TestImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/welcome/{name}")
    public String hello(@PathVariable("name") String name){
        return testService.test(name);
    }

}
