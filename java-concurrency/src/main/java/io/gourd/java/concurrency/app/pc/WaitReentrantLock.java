package io.gourd.java.concurrency.app.pc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 存在 BUG
 * 使用 wait/notifyAll/ReentrantLock 实现的多生产者消费者
 * <p>
 * 存在问题：
 * - 生产者消费者竞争同一把锁，性能较低
 * TODO 异常处理
 * <p>
 * Exception in thread "生产者-4" Exception in thread "生产者-5" Exception in thread "生产者-6" Exception in thread "生产者-7" java.lang.IllegalMonitorStateException
 * at java.lang.Object.wait(Native Method)
 * at java.lang.Object.wait(Object.java:502)
 * at io.gourd.java.concurrency.app.pc.WaitLock.produce(WaitLock.java:33)
 * at io.gourd.java.concurrency.app.pc.CarFactory$Producer.run(CarFactory.java:30)
 * at java.lang.Thread.run(Thread.java:748)
 * java.lang.IllegalMonitorStateException
 * at java.lang.Object.wait(Native Method)
 * at java.lang.Object.wait(Object.java:502)
 * at io.gourd.java.concurrency.app.pc.WaitLock.produce(WaitLock.java:33)
 * at io.gourd.java.concurrency.app.pc.CarFactory$Producer.run(CarFactory.java:30)
 * at java.lang.Thread.run(Thread.java:748)
 * java.lang.IllegalMonitorStateException
 * at java.lang.Object.wait(Native Method)
 * at java.lang.Object.wait(Object.java:502)
 * at io.gourd.java.concurrency.app.pc.WaitLock.produce(WaitLock.java:33)
 * at io.gourd.java.concurrency.app.pc.CarFactory$Producer.run(CarFactory.java:30)
 * at java.lang.Thread.run(Thread.java:748)
 * java.lang.IllegalMonitorStateException
 * at java.lang.Object.wait(Native Method)
 * at java.lang.Object.wait(Object.java:502)
 * at io.gourd.java.concurrency.app.pc.WaitLock.produce(WaitLock.java:33)
 * at io.gourd.java.concurrency.app.pc.CarFactory$Producer.run(CarFactory.java:30)
 * at java.lang.Thread.run(Thread.java:748)
 *
 * @author Li.Wei by 2019/12/19
 */
@Slf4j
public class WaitReentrantLock extends CarFactory {

    private final List<Car> cars = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    public WaitReentrantLock(int factorySize) {
        super(factorySize);
    }

    @Override
    public void produce() {

        lock.lock();
        try {
            while (cars.size() > super.getFactorySize()) {
                log.info("cars num > {} , pause produce.", super.getFactorySize());
                producerCondition.wait();
            }
            cars.add(new Car());
            consumerCondition.signalAll();
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
                consumerCondition.wait();
            }
            final Iterator<Car> iterator = cars.iterator();
            while (iterator.hasNext()) {
                final Car car = iterator.next();
                iterator.remove();
                log.info("consume car [{}]", car.hashCode());
            }

            producerCondition.signalAll();
        } catch (InterruptedException e) {
            log.error("consumerCar error {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }

}
