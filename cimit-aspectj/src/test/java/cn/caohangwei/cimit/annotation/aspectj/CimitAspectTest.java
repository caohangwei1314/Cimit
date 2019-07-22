package cn.caohangwei.cimit.annotation.aspectj;

import cn.caohangwei.cimit.annotation.Cimit;
import org.junit.jupiter.api.Test;


class CimitAspectTest {

    void downgradeMethod(){
        System.out.println("I am downgrade method!");
    }

    @Cimit(value = "test",capacity = 10,rate = 10,downgrade = "downgradeMethod")
    void downgradeTest(){
        System.out.println("success!");
    }

    @Test
    void test(){
//        for(int i=0;i<20;i++){
//            new Thread(()->{
//                downgradeTest();
//            }).start();
//        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        downgradeTest();
    }

}