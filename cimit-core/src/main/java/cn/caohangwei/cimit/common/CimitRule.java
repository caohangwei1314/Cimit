package cn.caohangwei.cimit.common;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import static cn.caohangwei.cimit.common.Constants.*;

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

    private int pollTimes;

    public CimitRule(){
        this(DEFAULT_LIMIT_NAME,DEFAULT_CAPACITY,DEFAULT_RATE,DEFAULT_PERIOD,DEFAULT_TIME_UNIT,DEFAULT_DISTRIBUTED,DEFAULT_POLL_TIMES);
    }

    public CimitRule(String name) {
        this(name,DEFAULT_CAPACITY,DEFAULT_RATE,DEFAULT_PERIOD,DEFAULT_TIME_UNIT,DEFAULT_DISTRIBUTED,DEFAULT_POLL_TIMES);
    }

    public CimitRule(String name,int capacity) {
        this(name,capacity,DEFAULT_RATE,DEFAULT_PERIOD,DEFAULT_TIME_UNIT,DEFAULT_DISTRIBUTED,DEFAULT_POLL_TIMES);
    }

    public CimitRule(String name,int capacity,int rate) {
        this(name,capacity,rate,DEFAULT_PERIOD,DEFAULT_TIME_UNIT,DEFAULT_DISTRIBUTED,DEFAULT_POLL_TIMES);
    }

    public CimitRule(String name,int capacity,int rate,int period) {
        this(name,capacity,rate,period,DEFAULT_TIME_UNIT,DEFAULT_DISTRIBUTED,DEFAULT_POLL_TIMES);
    }

    public CimitRule(String name,int capacity,int rate,int period,TimeUnit timeUnit) {
        this(name,capacity,rate,period,timeUnit,DEFAULT_DISTRIBUTED,DEFAULT_POLL_TIMES);
    }

    public CimitRule(String name, int capacity, int rate, int period, TimeUnit timeUnit, boolean distributed,int pollTimes) {
        this.name = name;
        this.capacity = capacity;
        this.rate = rate;
        this.period = period;
        this.timeUnit = timeUnit;
        this.distributed = distributed;
        this.pollTimes = pollTimes;
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

    public int getPollTimes() {
        return pollTimes;
    }

    public void setPollTimes(int pollTimes) {
        this.pollTimes = pollTimes;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name +
                "\",\"capacity\":" + capacity +
                ",\"rate\":" + rate +
                ",\"period\":" + period +
                ",\"timeUnit\":\"" + timeUnit +
                ",\"pollTimes\":\"" + timeUnit +
                "\"}";
    }
}
