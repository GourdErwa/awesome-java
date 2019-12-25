package io.gourd.java.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Li.Wei by 2019/12/22
 */
@Slf4j
public class Striped64Example {

    public static void main(String[] args) {
        final LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.add(666);
        System.out.println(longAdder.sum());
        log.info("longAdder.sum() = {}", longAdder.sum()); // 667

        final LongAccumulator longAccumulator =
            new LongAccumulator((left, right) -> left + right * 2, 666);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(3);
        longAccumulator.accumulate(-4);
        log.info("longAccumulator.get() = {}", longAccumulator.get()); // 666
    }
}
