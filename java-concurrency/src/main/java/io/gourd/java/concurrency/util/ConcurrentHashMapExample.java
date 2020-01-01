package io.gourd.java.concurrency.util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Li.Wei by 2019/12/29
 */
@Slf4j
public class ConcurrentHashMapExample {

    public static void main(String[] args) {
        int n = 10;
        log.info("n={}", n - (n >>> 2));
    }
}
