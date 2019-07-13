package cn.caohangwei.cimit.config;

import cn.caohangwei.cimit.common.CimitRule;
import cn.caohangwei.cimit.core.AbstractLimiter;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class CimitConfig {

    private static volatile Map<String, AbstractLimiter> limiterMap = new ConcurrentHashMap<>();

    private static ScheduledExecutorService executor;

    static {
        init();
    }

    private static void init(){
        executor = new ScheduledThreadPoolExecutor(limiterMap.size(),new ThreadFactoryBuilder().setNameFormat("WaterScheduler").build());
    }

    private static AbstractLimiter register(CimitRule rule){

    }
}
