package io.gourd.java.concurrency.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Li.Wei by 2019/12/26
 */
@Slf4j
public class CyclicBarrierExample {

    // 线程池
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    /**
     * 密室项目
     *
     * @param people 参与人数
     */
    public void play(int people) {

        final CyclicBarrier latch = new CyclicBarrier(people);
        for (int i = 0; i < people; i++) {
            int pName = i;
            executor.submit(() -> {
                log.info("{} , 进入密室...", pName);
                try {
                    latch.await(); // 第一道门

                    Thread.sleep(new Random().nextInt(1000)); // 模拟一个随机时间，作为迷宫的寻找时间
                    latch.await(); // 第二道门

                    Thread.sleep(new Random().nextInt(1000));
                    latch.await(); // 第三道门

                    Thread.sleep(new Random().nextInt(1000));
                    latch.await(); // 第四道门

                    Thread.sleep(new Random().nextInt(1000));
                    latch.await(); // 第五道门
                } catch (InterruptedException | BrokenBarrierException e) {
                    log.info("等待过程异常");
                }

                log.info("{} , 逃出密室...", pName);
            });
        }
    }

    public static void main(String[] args) {
        new CyclicBarrierExample().play(5);
    }
}
