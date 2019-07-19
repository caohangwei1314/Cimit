package cn.caohangwei.cimit.server.controller;

import cn.caohangwei.cimit.common.LimiterRule;
import cn.caohangwei.cimit.server.service.CimitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CimitController {

    @Autowired
    private CimitService cimitService;

    @RequestMapping(value = "/acquire", method = RequestMethod.POST)
    public String acquire(@RequestBody LimiterRule rule){
        return String.valueOf(cimitService.acquire(rule));
    }

    @RequestMapping(value = "/tryAcquire", method = RequestMethod.POST)
    public String tryAcquire(@RequestBody LimiterRule rule){
        System.out.println("*******");
        return String.valueOf(cimitService.tryAcquire(rule));
    }

}
