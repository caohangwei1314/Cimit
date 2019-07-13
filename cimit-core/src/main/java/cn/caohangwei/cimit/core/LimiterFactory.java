package cn.caohangwei.cimit.core;


import java.util.concurrent.TimeUnit;

/**
 * Factory model.
 *
 * @author PinuoC
 * @since 0.1.0
 */
public class LimiterFactory {

    public static AbstractLimiter getLeakyBucketLimiter() {
        return new LeakyBucketLimiter();
    }

    public static AbstractLimiter getLeakyBucketLimiter(int capacity, int rate, TimeUnit timeUnit) {
        return new LeakyBucketLimiter(capacity, rate, timeUnit);
    }

}
