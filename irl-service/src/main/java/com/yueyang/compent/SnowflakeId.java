package com.yueyang.compent;

/**
 * @author pengyueyang
 * @create 2019/6/25 11:51
 * 使用Snowflake（雪花算法）生成分布式id
 *
 * SnowFlake的结构如下：
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识符，正数为0负数为1，使用0
 * 41位时间戳（从1970年到现在的毫秒数），41位时间戳可以使用69年因此只能使用到2038年 (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 * 不过可以减去一个开始的时间戳，比较合适的方法就是使用机器开始生产id开始的时间，这里使用2019-01-01
 * 10位数据机器，因此最多可部署1024个节点
 * 12位序列，一毫秒内最多产生4096个ID序号
 * 一共64位正好为一个Long
 */
public class SnowflakeId {

    /** 开始时间截 (2019-01-01) */
    private final long START_TIME_STAMP = 1546272000000L;

    /**
     * 最多数据中心个数
     */
    private final int MAX_DATA_CENTER = 31;

    /**
     * 一个数据中心最多机器数
     */
    private final int MAX_WORKER = 31;

    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    /** 数据中心id */
    private int dataCenterId;

    /** 数据中心左移17位 */
    private final int DATA_CENTER_SHIFT = 17;

    /** 机器id */
    private int workerId;

    /** 机器左移17位 */
    private final int WORKER_SHIFT = 12;

    /** 毫秒内序列(0~4095) */
    private int sequence = 0;

    /** 序列掩码 */
    private final int SEQUENCE_MASK = 4095;

    /** 时间截向左移22位 */
    private final int TIMESTAMP_LEFT_SHIFT = 22;

    public SnowflakeId(int dataCenterId, int workerId) {
        if (dataCenterId > MAX_DATA_CENTER || dataCenterId < 0) {
            throw new IllegalArgumentException("Data center id should in 0-31");
        }
        if (workerId > MAX_WORKER || workerId < 0) {
            throw new IllegalArgumentException("Worker id should in 0-31");
        }
        this.dataCenterId = dataCenterId;
        this.workerId = workerId;
    }

    public synchronized Long getGenerateId() {
        long currentTimestamp = System.currentTimeMillis();
        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (currentTimestamp < lastTimestamp) {
            throw new SnowflakeIdException("Get generate id error:clock moved backwards.");
        }
        //如果是同一时间生成的，则进行毫秒内递增
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //一毫秒内的序列用完了
            if (sequence == 0) {
                currentTimestamp = nexMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = currentTimestamp;
        return ((currentTimestamp - START_TIME_STAMP) << TIMESTAMP_LEFT_SHIFT)
                | (dataCenterId << DATA_CENTER_SHIFT)
                | (workerId << WORKER_SHIFT)
                | sequence;
    }

    /**
     * 获取下一毫秒
     * @param lastTimestamp
     * @return
     */
    private long nexMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
