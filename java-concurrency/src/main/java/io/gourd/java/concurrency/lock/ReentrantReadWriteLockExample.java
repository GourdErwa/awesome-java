package io.gourd.java.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Li.Wei by 2019/12/24
 */
@Slf4j
public class ReentrantReadWriteLockExample {

    private final Map<String, String> cache = new HashMap<>(100); // 内存数据

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock(); // 读锁
    private final Lock writeLock = lock.writeLock(); // 写锁

    public String get(String key) { // 读操作
        readLock.lock();
        try {
            return cache.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public void put(String key, String value) { // 写操作
        writeLock.lock();
        try {
            cache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    private volatile boolean update = false;

    public void processData() {
        readLock.lock();
        if (!update) {
            readLock.unlock(); // 必须先释放读锁
            writeLock.lock(); // 锁降级从写锁获取到开始
            try {
                if (!update) {
                    // 准备数据的流程(略)
                    update = true;
                }
                readLock.lock();
            } finally {
                writeLock.unlock();
            }
        }
        try {
            log.info("处理逻辑...");
        } finally {
            readLock.unlock();
        }
    }

    // ReentrantReadWriteLock 提供锁状态相关方法示例
    public void logLockStatus() {
        // e.g. 一个线程重入了 n次读锁，那么 getReadHoldCount = 1，getReadLockCount = n
        log.info("当前读锁被获取的次数 = {}", lock.getReadLockCount());

        log.info("当前线程获取读锁的次数 = {}", lock.getReadHoldCount());

        log.info("写锁是否被获取 = {}", lock.isWriteLocked());

        log.info("写锁被获取的次数 = {}", lock.getWriteHoldCount());
    }


}
