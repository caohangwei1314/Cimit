package cn.caohangwei.cimit.core;

import cn.caohangwei.cimit.common.Constants;
import cn.caohangwei.cimit.common.LimiterRule;
import cn.caohangwei.cimit.config.CimitConfig;
import cn.caohangwei.cimit.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The purpose of this limiter is to send a request to the ticket server.
 *
 * @author PinuoC
 * @since 0.3.0
 */
public class DistributedLimiter extends AbstractLimiter{

    private static final Logger logger = LoggerFactory.getLogger(DistributedLimiter.class);

    private static final String URL = CimitConfig.getProperties().getProperty(Constants.CIMIT_SERVER_URL);

    private static final String ACQUIRE = URL + "/acquire";

    private static final String TRY_ACQUIRE = URL + "/tryAcquire";

    private LimiterRule rule;

    public DistributedLimiter(LimiterRule rule){
        this.rule = rule;
    }

    @Override
    public boolean acquire() {
        try {
            return Boolean.valueOf(HttpUtil.sendPost(ACQUIRE, rule.toString()));
        } catch (IOException e) {
            logger.debug("Http send exception: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean tryAcquire() {
        try {
            return Boolean.valueOf(HttpUtil.sendPost(TRY_ACQUIRE,rule.toString()));
        } catch (IOException e) {
            logger.debug("Http send exception: " + e.getMessage());
        }
        return false;
    }
}
