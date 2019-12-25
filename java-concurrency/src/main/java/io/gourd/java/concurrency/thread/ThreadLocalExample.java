package io.gourd.java.concurrency.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Li.Wei by 2019/12/20
 */
@Slf4j
public class ThreadLocalExample {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {

        new Thread(() -> {
            THREAD_LOCAL.set("Thread-" + 1);
            // 业务逻辑
            log.info(THREAD_LOCAL.get());
        }, "Thread-" + 1).start();

        new Thread(() -> {
            THREAD_LOCAL.set("Thread-" + 2);
            // 业务逻辑
            log.info(THREAD_LOCAL.get());
        }, "Thread-" + 2).start();

        log.info(THREAD_LOCAL.get());
    }
}
