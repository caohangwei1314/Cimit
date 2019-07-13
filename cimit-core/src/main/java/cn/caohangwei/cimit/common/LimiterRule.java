package cn.caohangwei.cimit.common;

import java.util.concurrent.TimeUnit;

public class CimitRule {

    String name;

    int capacity;

    int rate;

    TimeUnit timeUnit;

    public CimitRule(String name){
        this.name = name;
        this.capacity = Constants.DEFAULT_CAPACITY;
        this.rate = Constants.DEFAULT_RATE;
        this.timeUnit = Constants.DEFAULT_TIME_UNIT;
    }

    public CimitRule(String name,int capacity,int rate,TimeUnit timeUnit){
        this.name = name;
        this.capacity = capacity;
        this.rate = rate;
        this.timeUnit = timeUnit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public String toString() {
        return "CimitRule {" +
                "name=" + name +
                ", capacity=" + capacity +
                ", rate=" + rate +
                ", timeUnit=" + timeUnit +
                "}";
    }
}
