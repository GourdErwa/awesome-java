package io.gourd.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Li.Wei by 2019/12/22
 */
@Slf4j
public class AtomicIntegerExample {

    public static void main(String[] args) {
        final AtomicInteger atomic = new AtomicInteger(1);

        log.info("incrementAndGet return = {}", atomic.incrementAndGet());
        log.info("get = {}", atomic.get());

        log.info("getAndIncrement return = {}", atomic.getAndIncrement());
        log.info("get = {}", atomic.get());

        log.info("compareAndSet = {}", atomic.compareAndSet(3, 30));
        log.info("get = {}", atomic.get());

        log.info("getAndSet = {}", atomic.getAndSet(3));
        log.info("get = {}", atomic.get());
    }
}
