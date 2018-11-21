/*
 *
 * The MIT License
 * Copyright © 2018-2018 GourdErwa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

package com.gourd.alias.replacement.producer.base;


import com.gourd.alias.replacement.AliasCenterKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 别名生产者抽象父类.
 * 实现别名替换相关逻辑
 *
 * @author wei.Li by 2018/10/23
 */
public abstract class AbstractAliasProducer implements AliasProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAliasProducer.class);

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    /**
     * 替换数据
     * key=应用 ID
     * value = <原始字段,别名字段>
     */
    private final Map<String, Map<String, String>> data = new HashMap<>();

    @Override
    public final void load() {

        this.writeLock.lock();

        try {
            this.data.clear();
            // "Preconditions" and logging arguments should not require evaluation
            // this is compliant, because it will not evaluate if log level is above debug.
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("{}  simulate load data form clientPool", this.aliasProducerKey().key());
            }
            this.data.putAll(this.loadData());
        } finally {
            this.writeLock.unlock();
        }
    }

    /**
     * Load data map.
     *
     * @return the map
     */
    public abstract Map<String, Map<String, String>> loadData();

    @Override
    public final void reLoad() {
        this.load();
    }

    @Override
    public String alias(AliasCenterKey aliasCenterKey, String original) {
        this.readLock.lock();
        String alias = null;

        try {
            final Map<String, String> map = this.data.get(aliasCenterKey.getApp());
            if (map != null) {
                alias = map.get(original);
            }
        } finally {
            this.readLock.unlock();
        }
        return (alias == null) ? original + "[original]" : alias;
    }


    @Override
    public void close() {
        //nothing
    }

}
