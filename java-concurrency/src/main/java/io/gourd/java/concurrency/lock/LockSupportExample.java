package io.gourd.java.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * 先入先出的非可重入锁类
 *
 * @author Li.Wei by 2019/12/25
 */
@Slf4j
public class LockSupportExample {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
        boolean wasInterrupted = false;
        // publish current thread for unparkers
        waiters.add(Thread.currentThread());
        // Block while not first in queue or cannot acquire lock
        while (!(waiters.peek() == Thread.currentThread() && locked.compareAndSet(false, true))) {
            LockSupport.park(this);
            log.info("after park...");
            if (Thread.interrupted())
                wasInterrupted = true;
        }
        waiters.remove();
        // ensure correct interrupt status on return
        if (wasInterrupted)
            Thread.currentThread().interrupt();
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

    public static void main(String[] args) {
        final LockSupportExample example = new LockSupportExample();
        example.lock();

        new Thread(() -> {
            example.lock();
            log.info("waiters = {}", example.waiters);
            example.unlock();
        }).start();

        example.unlock();
    }
}

