package cn.caohangwei.cimit.common;

import java.util.concurrent.TimeUnit;

/**
 * The Default Limiter Rule.
 *
 * @author PinuoC
 * @since 0.1.0
 */
public class DefaultLimiterRule {
    public static final int CAPACITY = 1000;
    public static final int RATE = 100;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
}
