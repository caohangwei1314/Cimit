package cn.caohangwei.cimit.common;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Custom rule.
 *
 * @author PinuoC
 * @since 0.2.0
 */
public class CimitRule implements Serializable {

    private String name;

    private int capacity;

    private int rate;

    private int period;

    private TimeUnit timeUnit;

    private transient boolean distributed;

    public CimitRule(){
        this.name = "cimit";
        this.capacity = Constants.DEFAULT_CAPACITY;
        this.rate = Constants.DEFAULT_RATE;
        this.period = Constants.DEFAULT_PERIOD;
        this.timeUnit = Constants.DEFAULT_TIME_UNIT;
        this.distributed = false;
    }

    public CimitRule(String name) {
        this.name = name;
        this.capacity = Constants.DEFAULT_CAPACITY;
        this.rate = Constants.DEFAULT_RATE;
        this.period = Constants.DEFAULT_PERIOD;
        this.timeUnit = Constants.DEFAULT_TIME_UNIT;
    }

    public CimitRule(String name, int capacity, int rate, int period, TimeUnit timeUnit, boolean distributed) {
        this.name = name;
        this.capacity = capacity;
        this.rate = rate;
        this.period = period;
        this.timeUnit = timeUnit;
        this.distributed = distributed;
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

    public boolean getDistributed() {
        return distributed;
    }

    public void setDistributed(boolean distributed) {
        this.distributed = distributed;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name +
                "\",\"capacity\":" + capacity +
                ",\"rate\":" + rate +
                ",\"period\":" + period +
                ",\"timeUnit\":\"" + timeUnit +
                "\"}";
    }
}
