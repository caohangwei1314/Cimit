package cn.caohangwei.cimit.server.service;

import cn.caohangwei.cimit.common.CimitRule;

/**
 * Interface to get the remaining capacity of the bucket.
 *
 * @author PinuoC
 * @since 0.3.0
 */
public interface CimitService {

    /**
     * Spin until successful.
     *
     * @param rule the limiter rule
     * @return success or failure.
     */
    boolean acquire(CimitRule rule);

    /**
     * If there is no remaining capacity in the bucket,direct return failure.
     *
     * @param rule the limiter rule
     * @return success or failure.
     */
    boolean tryAcquire(CimitRule rule);

}
