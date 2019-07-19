package cn.caohangwei.cimit.core;


import cn.caohangwei.cimit.common.CimitRule;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

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
public class CimitFactory {

    private static volatile Map<String, AbstractLimiter> limiterMap = new ConcurrentHashMap<>();

    private static ScheduledExecutorService executor;

    private static final String DISTRIBUTED = "distributed";

    static {
        init();
    }

    private static void init() {
        executor = new ScheduledThreadPoolExecutor(limiterMap.size(), new ThreadFactoryBuilder().setNameFormat("WaterScheduler").build());
    }

    public static AbstractLimiter getLeakyBucketLimiter(String name) {
        return getLeakyBucketLimiter(new CimitRule(name));
    }

    public static AbstractLimiter getLeakyBucketLimiter(CimitRule rule) {
        if (rule.getDistributed()) {
            return getDistributedLimiter(rule);
        }
        if (limiterMap.get(rule.getName()) == null) {
            synchronized (CimitFactory.class) {
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

    private static AbstractLimiter getDistributedLimiter(CimitRule rule) {
        if (limiterMap.get(DISTRIBUTED) == null) {
            synchronized (CimitFactory.class) {
                if (limiterMap.get(DISTRIBUTED) == null) {
                    DistributedLimiter limiter = new DistributedLimiter(rule);
                    limiterMap.put(DISTRIBUTED, limiter);
                    return limiter;
                } else {
                    return limiterMap.get(DISTRIBUTED);
                }
            }
        } else {
            return limiterMap.get(DISTRIBUTED);
        }
    }

}
