package cn.caohangwei.cimit.core;

import cn.caohangwei.cimit.common.LimiterRule;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

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

    @Test
    void distributed(){
        LimiterRule rule = new LimiterRule("distributed",10,10,1, TimeUnit.SECONDS,true);
        AbstractLimiter limiter = LimiterFactory.getLeakyBucketLimiter(rule);
        limiter.tryAcquire();
        for(int i=0;i<20;i++){
            new Thread(()->{
                if(limiter.tryAcquire()){
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