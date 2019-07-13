package cn.caohangwei.cimit.core;

import java.util.concurrent.TimeUnit;

/**
 * Two methods acquire and tryAcquire are abstracted.
 *
 * @author PinuoC
 * @since 0.1.0
 */
public abstract class AbstractLimiter {


    /**
     * try to inject the bucket with water.if bucket if full,it spins until it succeeds.
     *
     * @return boolean
     */
    abstract boolean acquire();

    /**
     * try to inject the bucket with water.if bucket if full,return false,ortherwise return true.
     *
     * @return boolean
     */
    abstract boolean tryAcquire();

}
