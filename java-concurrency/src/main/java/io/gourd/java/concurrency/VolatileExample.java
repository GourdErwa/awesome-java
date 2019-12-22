package io.gourd.java.concurrency;

/**
 * @author Li.Wei by 2019/12/6
 */
public class VolatileExample {
    int a = 0;  // 普通共享变量
    volatile boolean flag = false; // volatile共享变量

    public void writer() {       // 写线程操作
        a = 1;                   //1
        flag = true;              //2
    }

    public void reader() {       // 读线程操作
        if (flag) {               //3
            int i = a;           //4
            System.out.printf(String.valueOf(i));
            // do ...
        }
    }
}
