package cn.caohangwei.cimit.server.service.impl;

import cn.caohangwei.cimit.common.CimitRule;
import cn.caohangwei.cimit.core.CimitFactory;
import cn.caohangwei.cimit.server.service.CimitService;
import org.springframework.stereotype.Service;

/**
 * Implementation of CimitService.
 *
 * @author PinuoC
 * @since 0.3.0
 */
@Service
public class CimitImpl implements CimitService {

    @Override
    public boolean acquire(CimitRule rule) {
        return CimitFactory.getLeakyBucketLimiter(rule).acquire();
    }

    @Override
    public boolean tryAcquire(CimitRule rule) {
        return CimitFactory.getLeakyBucketLimiter(rule).tryAcquire();
    }
}
