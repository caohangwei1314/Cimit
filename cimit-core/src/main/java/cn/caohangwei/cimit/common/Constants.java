package cn.caohangwei.cimit.common;

import java.util.concurrent.TimeUnit;

/**
 * The Default Limiter Rule.
 *
 * @author PinuoC
 * @since 0.1.0
 */
public class Constants {
    /**
     * Maximum capacity of bucket.
     */
    public static final int DEFAULT_CAPACITY = 1000;

    /**
     * Speed of production or reduction per unit time.
     */
    public static final int DEFAULT_RATE = 100;

    /**
     * Time unit.
     */
    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
}
