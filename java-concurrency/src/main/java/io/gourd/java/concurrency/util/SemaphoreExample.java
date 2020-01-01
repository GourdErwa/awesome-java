package io.gourd.java.concurrency.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @author Li.Wei by 2019/12/26
 */
@Slf4j
@AllArgsConstructor
public abstract class SemaphoreExample {

    private final Object[] connections; // 数据库可用连接
    private final Semaphore available = new Semaphore(10, true); // 信号量

    // 控制访问数量的方式获取可用连接，达到访问数量最大值时，阻塞
    public Object getConnection() throws InterruptedException {
        available.acquire();
        return getNextAvailableConnection();
    }

    // 放回连接
    public void putConnection(Object x) {
        if (markAsUnused(x))
            available.release();
    }

    abstract Object getNextAvailableConnection();

    abstract boolean markAsUnused(Object connection);
}

