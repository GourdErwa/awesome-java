package io.gourd.java.concurrency.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Li.Wei by 2019/12/19
 */
public class InterruptedExample {

    public static void main(String[] args) throws InterruptedException {
        // sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);

        // busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        // busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        System.out.println("判断第一阶段-SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("判断第一阶段-BusyThread interrupted is " + busyThread.isInterrupted());

        // 休眠5秒，让sleepThread和busyThread充分运行
        TimeUnit.SECONDS.sleep(3);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("判断第二阶段 bef-SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("判断第二阶段 bef-BusyThread interrupted is " + busyThread.isInterrupted());
        // 防止sleepThread和busyThread立刻退出
        TimeUnit.SECONDS.sleep(5);
        System.out.println("判断第三阶段 aft-SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("判断第三阶段 aft-BusyThread interrupted is " + busyThread.isInterrupted());

    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("SleepRunner");

                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                // System.out.println("BusyRunner");
            }
        }

    }
}
