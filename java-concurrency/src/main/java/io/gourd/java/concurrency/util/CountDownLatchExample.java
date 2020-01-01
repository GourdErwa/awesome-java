package io.gourd.java.concurrency.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Li.Wei by 2019/12/26
 */
@Slf4j
public class CountDownLatchExample {

    // 线程池
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    /**
     * 解析 excel 工作簿
     *
     * @param sheets 多个工作簿
     * @return 解析结果
     */
    public boolean resolveExcel(List<Object> sheets) {

        // 每个工作簿解析为一个计数
        final CountDownLatch latch = new CountDownLatch(sheets.size());

        // 每个工作簿封装为一个工作线程提交到线程池，完成后计数 -1
        sheets.forEach(o ->
            executor.submit(() -> {
                log.info("解析工作簿... {}", o);
                latch.countDown(); // 完成后计数 -1
            }));

        try {
            return latch.await(2, TimeUnit.MINUTES); // 等待 2 min
        } catch (InterruptedException e) {
            log.warn("解析超时，当前计数[{}]", latch.getCount());
        }
        return false;
    }
}
