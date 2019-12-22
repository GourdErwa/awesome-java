package io.gourd.java.concurrency;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 模拟死锁逻辑说明：
 * 两个线程 thread1、thread2 运行过程中需要在 lock1、lock2 两个共享资源上加锁。
 * - thread1 加锁顺序为 lock1->lock2
 * - thread1 加锁顺序为 lock2->lock1
 *
 * @author Li.Wei by 2019/12/17
 */
public interface DeadLockExample {
}

// synchronized 关键字出现死锁的场景
class SynchronizedDeadLockExample {

    public static void main(String[] args) {
        final Object lock1 = new Object();
        final Object lock2 = new Object();

        final Thread thread1 = new Thread(() -> {
            synchronized (lock1) {              // 对 lock1 加锁
                System.out.println("lock1");
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {          // 对 lock2 加锁
                    System.out.println("lock1->lock2");
                }
            }
        });
        thread1.setName("Thread-DeadLockExample-1");


        final Thread thread2 = new Thread(() -> {
            synchronized (lock2) {            // 对 lock2 加锁
                System.out.println("lock2");
                try {
                    sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {        // 对 lock1 加锁
                    System.out.println("lock2->lock1");
                }
            }
        });
        thread2.setName("Thread-DeadLockExample-2");

        thread1.start();
        thread2.start();
    }
}


// ReentrantLock 出现死锁的场景
// 注意：lock 加锁解锁代码为了方便演示移除 try {} finally {}，实际开发不推荐这种写法
class LockDeadLockExample {

    public static void main(String[] args) {

        final ReentrantLock lock1 = new ReentrantLock();
        final ReentrantLock lock2 = new ReentrantLock();

        final Thread thread1 = new Thread(() -> {
            lock1.lock();
            System.out.println("lock1");
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock2.lock();
            System.out.println("lock1->lock2");
            lock2.unlock();

            lock1.unlock();
        });
        thread1.setName("Thread-DeadLockExample-1");


        final Thread thread2 = new Thread(() -> {
            lock2.lock();
            System.out.println("lock2");
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock1.lock();
            System.out.println("lock2->lock1");
            lock1.unlock();

            lock2.unlock();
        });
        thread2.setName("Thread-DeadLockExample-2");

        thread1.start();
        thread2.start();
    }
}
