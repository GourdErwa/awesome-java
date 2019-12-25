package io.gourd.java.concurrency.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Li.Wei by 2019/12/20
 */
@Slf4j
public class ThreadPoolExecutorExample {
    final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
        2,
        10,
        30, TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(20, true),
        Thread::new,
        new ThreadPoolExecutor.DiscardPolicy()
    );

    public static void main(String[] args) {
        final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            2,
            10,
            30, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(20, true),
            Thread::new,
            new ThreadPoolExecutor.DiscardPolicy()
        );
        poolExecutor.submit(() -> log.info(""));
        poolExecutor.submit(() -> 100);

        poolExecutor.shutdown();
    }
}
