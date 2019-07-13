package cn.caohangwei.cimit.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LimiterFactoryTest {

    @Test
    void getLeakyBucketLimiter() {
        LeakyBucketLimiter limiter = (LeakyBucketLimiter) LimiterFactory.getLeakyBucketLimiter("Cimit");
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                if (limiter.tryAcquire()) {
                    System.out.println("Hello Cimit!");
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}