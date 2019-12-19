package io.gourd.java.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * {@link Thread#join()}
 *
 * @author Li.Wei by 2019/12/19
 */
public class JoinExample {

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 3; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            final Thread thread = new Thread(new Domino(previous), "t-" + i);
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);

        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        private Thread previous; // 当前线程的前一个线程引用

        public Domino(Thread previous) {
            this.previous = previous;
        }

        @Override
        public void run() {
            final String name = Thread.currentThread().getName();
            try {
                System.out.println(name + " before .join()"); // join 方法调用前逻辑
                previous.join();
                System.out.println(name + " after .join()"); // join 方法调后前逻辑
            } catch (InterruptedException e) {
            }
            System.out.println(name + " terminate.");
        }
    }
    /*
    最终打印

    t-0 before .join()
    t-2 before .join()
    t-1 before .join()
    main terminate.
    t-0 after .join()
    t-0 terminate.
    t-1 after .join()
    t-1 terminate.
    t-2 after .join()
    t-2 terminate.
     */
}
