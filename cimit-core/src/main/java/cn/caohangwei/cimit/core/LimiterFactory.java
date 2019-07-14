package cn.caohangwei.cimit.core;


import cn.caohangwei.cimit.common.LimiterRule;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Factory model.
 *
 * @author PinuoC
 * @since 0.1.0
 */
public class LimiterFactory {

    private static volatile Map<String, AbstractLimiter> limiterMap = new ConcurrentHashMap<>();

    private static ScheduledExecutorService executor;

    static {
        init();
    }

    private static void init() {
        executor = new ScheduledThreadPoolExecutor(limiterMap.size(), new ThreadFactoryBuilder().setNameFormat("WaterScheduler").build());
    }

    public static AbstractLimiter getLeakyBucketLimiter(String name) {
        return getLeakyBucketLimiter(new LimiterRule(name));
    }

    public static AbstractLimiter getLeakyBucketLimiter(LimiterRule rule) {
        if (limiterMap.get(rule.getName()) == null) {
            synchronized (LimiterFactory.class) {
                if (limiterMap.get(rule.getName()) == null) {
                    LeakyBucketLimiter limiter = new LeakyBucketLimiter(rule);
                    limiterMap.put(rule.getName(), limiter);
                    executor.scheduleAtFixedRate(() -> {
                        synchronized (limiter) {
                            AtomicInteger water = limiter.getWater();
                            water.set(Math.max(0, water.get() - limiter.getRule().getRate()));
                        }
                    }, 0, rule.getPeriod(), rule.getTimeUnit());
                    return limiter;
                } else {
                    return limiterMap.get(rule.getName());
                }
            }
        } else {
            return limiterMap.get(rule.getName());
        }
    }

}
