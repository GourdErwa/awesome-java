package io.gourd.java.concurrency.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static io.gourd.java.concurrency.thread.WaitAndNotifyExample.FLAG;
import static io.gourd.java.concurrency.thread.WaitAndNotifyExample.LOCK;

/**
 * @author Li.Wei by 2019/12/19
 */
public class WaitAndNotifyExample {

    public static boolean FLAG = true;
    public static final Object LOCK = new Object();

    public static void main(String[] args) {
        new Thread(new Wait(), "[WaitThread]").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
        }
        new Thread(new Notify(), "[NotifyThread]").start();
    }
}

class Wait implements Runnable {
    @Override
    public void run() {
        synchronized (LOCK) {
            final String threadName = Thread.currentThread().getName();
            while (FLAG) {
                System.out.println(new Date() + threadName + " FLAG = true , wait...");
                try {
                    LOCK.wait();
                } catch (InterruptedException ignored) {
                }
            }
            System.out.println(new Date() + threadName + " FLAG = false，开始继续工作");
        }
    }
}

class Notify implements Runnable {
    @Override
    public void run() {
        synchronized (LOCK) {
            final String threadName = Thread.currentThread().getName();
            System.out.println(new Date() + threadName + " 持有锁，发出通知");
            LOCK.notifyAll();
            FLAG = false;
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ignored) {
            }
            synchronized (LOCK) { // 再次加锁
                System.out.println(new Date() + threadName + " 再次拿到锁. sleep @ ");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
