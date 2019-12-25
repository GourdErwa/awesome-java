package io.gourd.java.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Li.Wei by 2019/12/16
 */
@Slf4j
public class ReentrantLockExample {
    int value = 0;
    private final ReentrantLock lock = new ReentrantLock(false);

    public void writer() {
        lock.lock();        // 获取锁
        try {
            //value++;
            lock.lock(); // 再次获取锁(重入)
            try {
                value++;
                log.info("lock.HoldCount = {}", lock.getHoldCount()); // 2
            } finally {
                lock.unlock();  // 释放锁
            }
        } finally {
            lock.unlock();  // 再次释放锁
        }
    }

    public void reader() {
        lock.lock();        // 获取锁
        try {
            int tmp = value;
            // do something ...
            log.info("tmp = {}", tmp);
        } finally {
            lock.unlock();  // 释放锁
        }
    }

    public static void main(String[] args) {
        final ReentrantLockExample lockExample = new ReentrantLockExample();
        lockExample.writer();
        lockExample.reader();
    }
}
