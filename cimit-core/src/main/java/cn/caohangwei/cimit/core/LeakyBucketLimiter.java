package cn.caohangwei.cimit.core;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.caohangwei.cimit.common.DefaultLimiterRule;
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

    private static Logger logger = LoggerFactory.getLogger(LeakyBucketLimiter.class);

    private AtomicInteger water = new AtomicInteger(0);

    private static final Lock LOCK = new ReentrantLock();

    private ScheduledExecutorService executor;

    private TimeUnit timeUnit;

    private int capacity;

    private int rate;

    public LeakyBucketLimiter() {
        this(DefaultLimiterRule.CAPACITY, DefaultLimiterRule.RATE, DefaultLimiterRule.TIME_UNIT);
    }

    public LeakyBucketLimiter(int capacity, int rate, TimeUnit timeUnit) {
        this.capacity = capacity;
        this.rate = rate;
        this.timeUnit = timeUnit;
        this.executor = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("WaterScheduled").build());
        executor.scheduleAtFixedRate(() -> {
            try {
                LOCK.lock();
                water.set(Math.max(0, water.get() - rate));
            } catch (Exception e) {
                logger.debug("Water Decrement Error: " + e.getMessage());
            } finally {
                LOCK.unlock();
            }
        }, 0, 1, timeUnit);
    }

    @Override
    public boolean acquire() {
        while (true) {
            int size = water.get();
            if (size < capacity) {
                if (water.compareAndSet(size, size + 1)) {
                    return true;
                }
            }
        }
    }

    @Override
    public boolean tryAcquire() {
        int size = water.get();
        if (size < capacity) {
            return water.compareAndSet(size, size + 1);
        } else {
            return false;
        }
    }

}
