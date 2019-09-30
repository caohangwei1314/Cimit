package cn.caohangwei.cimit.core;

import java.util.concurrent.atomic.AtomicInteger;

import cn.caohangwei.cimit.common.CimitRule;

/**
 * Current limiting based on leaky bucket algorithms
 *
 * @author PinuoC
 * @since 0.1.0
 */
public class LeakyBucketLimiter extends AbstractLimiter {

    private AtomicInteger water = new AtomicInteger(0);

    private CimitRule rule;

    public LeakyBucketLimiter(CimitRule rule) {
        this.rule = rule;
    }

    public AtomicInteger getWater() {
        return water;
    }

    public CimitRule getRule() {
        return rule;
    }

    @Override
    public boolean acquire() {
        int pollTimes = rule.getPollTimes();
        while (pollTimes>0) {
            int size = water.get();
            if (size < rule.getCapacity()) {
                if (water.compareAndSet(size, size + 1)) {
                    return true;
                }
            }
            pollTimes-=1;
        }
        return false;
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
