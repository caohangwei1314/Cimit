package cn.caohangwei.cimit.server.service;

import cn.caohangwei.cimit.common.LimiterRule;

public interface CimitService {

    boolean acquire(LimiterRule rule);

    boolean tryAcquire(LimiterRule rule);

}
