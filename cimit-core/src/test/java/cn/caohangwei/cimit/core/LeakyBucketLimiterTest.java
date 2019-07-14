package cn.caohangwei.cimit.core;

import org.junit.jupiter.api.Test;

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
    }

    @Test
    void tryAcquire() {
    }
}