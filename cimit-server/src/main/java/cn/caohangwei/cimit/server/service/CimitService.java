package cn.caohangwei.cimit.server.service;

import cn.caohangwei.cimit.common.CimitRule;

public interface CimitService {

    boolean acquire(CimitRule rule);

    boolean tryAcquire(CimitRule rule);

}
