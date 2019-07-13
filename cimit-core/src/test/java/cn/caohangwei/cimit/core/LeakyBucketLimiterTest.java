package cn.caohangwei.cimit.core;

import cn.caohangwei.cimit.common.CimitRule;
import org.junit.jupiter.api.Test;


import java.util.concurrent.TimeUnit;

class LeakyBucketLimiterTest {

    @Test
    void acquire() {
        LeakyBucketLimiter limiter = (LeakyBucketLimiter) LimiterFactory.getLeakyBucketLimiter("Cimit");
        for(int i=0;i<20;i++){
            new Thread(()->{
                if(limiter.acquire()){
                    System.out.print('*');
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void tryAcquire() {
    }
}