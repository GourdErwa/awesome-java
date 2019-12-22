package io.gourd.java.concurrency.app.pc;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 提取生产消费者模型公共代码
 * {@link CarFactory} 卡车工厂
 * {@link Car} 卡车实体
 * {@link Producer} 生产者
 * {@link Consumer} 消费者
 *
 * @author Li.Wei by 2019/12/19
 */
@AllArgsConstructor
@Getter
public abstract class CarFactory {

    private final int factorySize;

    /**
     * 工厂生产方式实现
     */
    abstract void produce();

    /**
     * 工厂消费方式实现
     */
    abstract void consume();

    static class Car {
    }

    @AllArgsConstructor
    static class Producer implements Runnable {
        private final CarFactory carFactory;
        private final int runMillis;    // 执行生产方法时间间隔

        @Override
        public void run() {
            while (true) {
                carFactory.produce();
                try {
                    Thread.sleep(runMillis);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    @AllArgsConstructor
    static class Consumer implements Runnable {
        private final CarFactory carFactory;
        private final int runMillis; // 执行消费方法时间间隔

        @Override
        public void run() {
            while (true) {
                carFactory.consume();
                try {
                    Thread.sleep(runMillis);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) {

        final int producerNum = 10; // 模拟生产者数量
        final int consumerNum = 1;// 模拟消费者数量

        final int producerRunMillis = 300; // 生产者作业时间间隔
        final int consumerRunMillis = 300;// 消费者作业时间间隔

        final CarFactory carFactory = new WaitSynchronized(5);
        // final CarFactory carFactory = new WaitReentrantLock(5);
        final Producer producer = new Producer(carFactory, producerRunMillis);
        final Consumer consumer = new Consumer(carFactory, consumerRunMillis);

        for (int i = 0; i < producerNum; i++) {
            new Thread(producer, "生产者-" + i).start();
        }
        for (int i = 0; i < consumerNum; i++) {
            new Thread(consumer, "消费者-" + i).start();
        }

    }
}
