package cn.caohangwei.cimit.server.service.impl;

import cn.caohangwei.cimit.common.LimiterRule;
import cn.caohangwei.cimit.core.LimiterFactory;
import cn.caohangwei.cimit.server.service.CimitService;
import org.springframework.stereotype.Service;

@Service
public class CimitImpl implements CimitService {

    @Override
    public boolean acquire(LimiterRule rule) {
        return LimiterFactory.getLeakyBucketLimiter(rule).acquire();
    }

    @Override
    public boolean tryAcquire(LimiterRule rule) {
        return LimiterFactory.getLeakyBucketLimiter(rule).tryAcquire();
    }
}
