package cn.caohangwei.cimit.core;

import org.junit.jupiter.api.Test;


import java.util.concurrent.TimeUnit;

class LeakyBucketLimiterTest {

    @Test
    void acquire() {
        LeakyBucketLimiter limiter = new LeakyBucketLimiter(10,1, TimeUnit.NANOSECONDS);
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