package io.gourd.java.concurrency.app.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 存在 BUG
 * 使用 Condition/ReentrantLock 实现的多生产者消费者
 * <p>
 * 存在问题：
 * - 生产者消费者竞争同一把锁，性能较低
 *
 * @author Li.Wei by 2019/12/19
 */
@Slf4j
public class ConditionReentrantLock extends CarFactory {

    private final List<Car> cars = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    public ConditionReentrantLock(int factorySize) {
        super(factorySize);
    }

    @Override
    public void produce() {

        lock.lock();
        try {
            while (cars.size() > super.getFactorySize()) {
                log.info("cars num > {} , pause produce.", super.getFactorySize());
                producerCondition.wait();// 生产过剩，进入等待
            }
            cars.add(new Car());
            consumerCondition.signalAll(); // 生产后通知继续消费
        } catch (InterruptedException e) {
            log.error("createCar error {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void consume() {
        lock.lock();
        try {
            while (cars.isEmpty()) {
                log.info("cars num = 0 , pause consume.");
                consumerCondition.wait(); // 生产力不足，进入等待
            }
            final Iterator<Car> iterator = cars.iterator();
            while (iterator.hasNext()) {
                final Car car = iterator.next();
                iterator.remove();
                log.info("consume car [{}]", car.hashCode());
            }

            producerCondition.signalAll(); // 消费后通知继续生产
        } catch (InterruptedException e) {
            log.error("consumerCar error {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
