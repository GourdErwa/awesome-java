package io.gourd.java.concurrency.test;

/**
 * @author Li.Wei by 2020/2/26
 */
public class Test1 {

    static volatile Integer count = 0;
    // static final Integer countObject = new Integer(count);
    static final Object object = new Object();

    private static void test() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                synchronized (count) { // count 在同步块里改变了值
                    //System.out.println("thread1-> " + count);
                    count++;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                synchronized (count) {
                    // System.out.println("thread2-> " + count);
                    count++;
                }
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("r=" + Test1.count);
    }

    public static void main(String[] args) throws InterruptedException {
        Test1.test();
    }
}
