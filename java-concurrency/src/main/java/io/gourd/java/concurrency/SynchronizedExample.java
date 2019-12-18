package io.gourd.java.concurrency;

/**
 * @author Li.Wei by 2019/12/16
 */
public class SynchronizedExample {

    private final Object lock = new Object();

    private int value = 10;
    private static int staticValue = 10;

    public void setValue(int i) {
        // 对于同步方法块，锁是 synchronized 括号里配置的对象。
        synchronized (lock) {
            this.value = i % 10;
        }
    }

    // 对于普通同步方法，锁是当前实例对象。
    public synchronized int getValue() {
        return value;
    }

    // 对于静态同步方法，锁是当前类的Class对象。
    public static synchronized int lock() {
        return staticValue;
    }


    public static void main(String[] args) {
        Task2 mTask2 = new Task2();

        new Thread(() -> mTask2.doLongTimeTaskA()).start();
        //new Thread(() -> mTask2.doLongTimeTaskB()).start();
        //new Thread(() -> mTask2.doLongTimeTaskC()).start();
        new Thread(() -> mTask2.doLongTimeTaskD()).start();
    }
}

class Task2 {

    public synchronized static void doLongTimeTaskA() {
        System.out.println("doLongTimeTaskA begin");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("doLongTimeTaskA end");
    }

    public synchronized static void doLongTimeTaskB() {
        System.out.println("doLongTimeTaskB begin");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("doLongTimeTaskB end");
    }

    public synchronized void doLongTimeTaskC() {

        System.out.println("doLongTimeTaskC begin");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("doLongTimeTaskC end");

    }

    public void doLongTimeTaskD() {

        synchronized (Task2.class) {
            System.out.println("doLongTimeTaskD begin");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("doLongTimeTaskD end");
        }
    }
}
