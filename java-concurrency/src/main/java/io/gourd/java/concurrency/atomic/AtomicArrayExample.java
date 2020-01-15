package io.gourd.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Li.Wei by 2019/12/22
 */
@Slf4j
public class AtomicArrayExample {

    public static void main(String[] args) {
        final AtomicIntegerArray atomic = new AtomicIntegerArray(new int[]{1, 2, 3});

        log.info("incrementAndGet return = {}", atomic.incrementAndGet(1));
        log.info("get = {}", atomic.get(1));

        log.info("getAndIncrement return = {}", atomic.getAndIncrement(1));
        log.info("get = {}", atomic.get(1));

        log.info("compareAndSet = {}", atomic.compareAndSet(1, 3, 30));
        log.info("get = {}", atomic.get(1));

        log.info("getAndSet = {}", atomic.getAndSet(1, 3));
        log.info("get = {}", atomic.get(1));
    }
}
