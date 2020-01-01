package io.gourd.java.concurrency.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Exchanger;

/**
 * @author Li.Wei by 2019/12/26
 */
@Slf4j
public class ExchangerExample {
    private final Exchanger<String> exchanger = new Exchanger<>();

    class TypeA implements Runnable {
        public void run() {
            log.info("TypeA 执行计算逻辑...");
            String a = "TypeA 中间计算结果";
            try {
                String exchange = exchanger.exchange(a);
                log.info("获取 TypeB ={}", exchange);
                // 获取 TypeB 内容后继续处理
            } catch (InterruptedException ignored) {
            }
        }
    }

    class TypeB implements Runnable {
        public void run() {
            log.info("TypeB 执行计算逻辑...");
            String b = "TypeB 中间计算结果";
            try {
                String exchange = exchanger.exchange(b);
                log.info("获取 TypeA ={}", exchange);
                // 获取 TypeA 内容后继续处理
            } catch (InterruptedException ignored) {
            }
        }
    }

    public void start() {
        new Thread(new TypeA()).start();
        new Thread(new TypeB()).start();
        new Thread(new TypeA()).start();
        // new Thread(new TypeB()).start();
    }

    public static void main(String[] args) {
        new ExchangerExample().start();
    }
}
