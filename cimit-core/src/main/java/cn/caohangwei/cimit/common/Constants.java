package cn.caohangwei.cimit.common;

import java.util.concurrent.TimeUnit;

/**
 * The Default Limiter Rule.
 *
 * @author PinuoC
 * @since 0.1.0
 */
public class Constants {

    public static final String DEFAULT_LIMIT_NAME = "cimit";

    public static final int DEFAULT_CAPACITY = 1000;

    public static final int DEFAULT_RATE = 100;

    public static final int DEFAULT_PERIOD = 1;

    public static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;

    public static final String DEFAULT_CIMIT_SERVER_URL = "cimit.server.url";

    public static final boolean DEFAULT_DISTRIBUTED = false;

    public static final String DEFAULT_DOWN_GRADE = "failFast";

    public static final int DEFAULT_POLL_TIMES = 10;
}
