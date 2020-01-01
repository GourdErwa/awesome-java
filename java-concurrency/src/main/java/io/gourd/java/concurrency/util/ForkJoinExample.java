package io.gourd.java.concurrency.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin
 *
 * @author Li.Wei by 2019/12/28
 */
@Slf4j
@Getter
@AllArgsConstructor
public class ForkJoinExample extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 20;  // 阈值
    private int start;
    private int end;

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            final ForkJoinExample leftTask = new ForkJoinExample(start, middle);
            final ForkJoinExample rightTask = new ForkJoinExample(middle + 1, end);
            leftTask.fork();
            rightTask.fork();

            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算
        final ForkJoinExample task = new ForkJoinExample(1, 100);
        // 异步执行一个任务
        final Future<Integer> result = forkJoinPool.submit(task);
        // final Integer r2 = forkJoinPool.invoke(task); // 同步执行
        try {
            log.info("sum = {}", result.get());
        } catch (InterruptedException | ExecutionException ignored) {
        }
    }
}
