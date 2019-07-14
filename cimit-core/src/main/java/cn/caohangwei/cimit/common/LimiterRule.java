package cn.caohangwei.cimit.common;

import java.util.concurrent.TimeUnit;

public class LimiterRule {

    String name;

    int capacity;

    int rate;

    int period;

    TimeUnit timeUnit;

    public LimiterRule(String name) {
        this.name = name;
        this.capacity = Constants.DEFAULT_CAPACITY;
        this.rate = Constants.DEFAULT_RATE;
        this.period = Constants.DEFAULT_PERIOD;
        this.timeUnit = Constants.DEFAULT_TIME_UNIT;
    }

    public LimiterRule(String name, int capacity, int rate, int period, TimeUnit timeUnit) {
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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
                ", period=" + period +
                ", timeUnit=" + timeUnit +
                "}";
    }
}
