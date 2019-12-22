package io.gourd.java.concurrency.app.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 使用 wait/notifyAll/synchronized 实现的多生产者消费者
 *
 * 存在问题：
 * - 生产者消费者竞争同一把锁，性能较低
 *
 * @author Li.Wei by 2019/12/19
 */
@Slf4j
public class WaitSynchronized extends CarFactory {

    private final List<Car> cars = new ArrayList<>();
    private final Object lock = new Object();

    public WaitSynchronized(int factorySize) {
        super(factorySize);
    }

    @Override
    public void produce() {
        synchronized (lock) {
            try {
                while (cars.size() > super.getFactorySize()) {
                    log.info("cars num > {} , pause produce.", super.getFactorySize());
                    lock.wait();
                }
                cars.add(new Car());
                lock.notifyAll();
            } catch (InterruptedException e) {
                log.error("createCar error {}", e.getMessage());
            }
        }
    }

    @Override
    public void consume() {
        synchronized (lock) {
            try {
                while (cars.isEmpty()) {
                    log.info("cars num = 0 , pause consume.");
                    lock.wait();
                }
                final Iterator<Car> iterator = cars.iterator();
                while (iterator.hasNext()) {
                    final Car car = iterator.next();
                    iterator.remove();
                    log.info("consume car [{}]", car.hashCode());
                }

                lock.notifyAll();
            } catch (InterruptedException e) {
                log.error("consumerCar error {}", e.getMessage());
            }
        }
    }

}
