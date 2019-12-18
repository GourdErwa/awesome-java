package io.gourd.java.concurrency;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Li.Wei by 2019/12/16
 */
public class ReentrantLockExample {
    int value = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void writer() {
        lock.lock();        // 获取锁
        try {
            value++;
        } finally {
            lock.unlock();  // 释放锁
        }
    }

    public void reader() {
        lock.lock();        // 获取锁
        try {
            int tmp = value;
            // do something ...
            System.out.println(tmp);
        } finally {
            lock.unlock();  // 释放锁
        }
    }
}
