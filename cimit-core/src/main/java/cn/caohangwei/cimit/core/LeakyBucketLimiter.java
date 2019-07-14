package cn.caohangwei.cimit.core;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.caohangwei.cimit.common.LimiterRule;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Current limiting based on leaky bucket algorithms
 *
 * @author PinuoC
 * @since 0.1.0
 */
public class LeakyBucketLimiter extends AbstractLimiter {

    private AtomicInteger water = new AtomicInteger(0);

    private LimiterRule rule;

    public LeakyBucketLimiter(LimiterRule rule) {
        this.rule = rule;
    }

    public AtomicInteger getWater() {
        return water;
    }

    public LimiterRule getRule() {
        return rule;
    }

    @Override
    public boolean acquire() {
        while (true) {
            int size = water.get();
            if (size < rule.getCapacity()) {
                if (water.compareAndSet(size, size + 1)) {
                    return true;
                }
            }
        }
    }

    @Override
    public boolean tryAcquire() {
        int size = water.get();
        if (size < rule.getCapacity()) {
            return water.compareAndSet(size, size + 1);
        } else {
            return false;
        }
    }

}
