/*
 *
 * The MIT License Copyright © 2018-2018 GourdErwa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.gourd.demand.alias.replacement;

import io.gourd.demand.alias.replacement.producer.base.AliasProducer;
import io.gourd.demand.alias.replacement.producer.base.AliasProducerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiFunction;

/**
 * 别名替换中心. 使用示例参考 {@linkplain Application}
 *
 * @author wei.Li by 2018/10/23
 */
final class AliasCenter {

    /**
     * 转换函数 (original, alias) -> original(alias)
     */
    static final BiFunction<String, String, String> BI_FUNCTION_PARENTHESES = (original, alias) -> original + "-("
        + alias + ")";
    private static final Logger LOGGER = LoggerFactory.getLogger(AliasCenter.class);
    /**
     * 注册别名生产者
     */
    private final Map<String, AliasProducer> register = new HashMap<>();
    /**
     * 标识是否启动 启动前:不可执行别名注册操作、重置数据操作 启动后:不可执行注册操作
     */
    private boolean start = false;

    private AliasCenter() {
    }

    /**
     * Create alias center.
     *
     * @return the alias center
     */
    static AliasCenter create() {
        return new AliasCenter();
    }

    /**
     * 注册生产者.
     *
     * @param aliasProducer 待注册生产者
     * @return the alias center
     */
    AliasCenter register(AliasProducer aliasProducer) {

        this.checkStart();

        final AliasProducerKey aliasProducerKey = aliasProducer.aliasProducerKey();
        final String key = aliasProducerKey.key();
        final AliasProducer producer = this.register.get(key);
        if (producer != null) {
            throw new AliasException("aliasProducerKey:" + key + ", class " + aliasProducer.getClass().getSimpleName()
                + " already exists");
        }
        this.register.put(key, aliasProducer);
        return this;
    }

    /**
     * 启动生产者中心. 启动逻辑包括： #修改启动状态为已启动 #载入所有别名生产者数据
     *
     * @return the alias center
     */
    synchronized AliasCenter start() {
        this.checkStart();
        start = true;
        LOGGER.info("AliasCenter start() start...");
        LOGGER.info("AliasCenter reLoadAliasProducer ...");
        this.reLoadAliasProducer();
        this.register.forEach((s, aliasProducer) -> LOGGER.info("AliasCenter registered key:{} aliasProducer:{}", s,
            aliasProducer.getClass().getSimpleName()));
        LOGGER.info("AliasCenter start() start...");
        LOGGER.info("AliasCenter start() end...");
        return this;
    }

    /**
     * 校验是否启动，启动状态下抛出异常
     */
    private void checkStart() {
        if (this.start) {
            throw new AliasException("AliasCenter already start");
        }
    }

    /**
     * 校验是否启动，未启动状态下抛出异常
     */
    private void checkNotStart() {
        if (!this.start) {
            throw new AliasException("AliasCenter failure , please invoke start()");
        }
    }

    /**
     * 刷新所有生产者别名数据.
     */
    void reLoadAliasProducer() {
        this.checkNotStart();
        this.register.values().forEach(AliasProducer::reLoad);
    }

    /**
     * 别名替换.
     *
     * @param aliasCenterKey 别名替换 key
     * @param originals      待替换原始数据
     * @param strategy       转换策略函数 , 给予 [原始,别名] 2个字符，自由组合为结果字符
     * @return the string [ ]
     */
    String[]
    aliasReplace(AliasCenterKey aliasCenterKey, String[] originals, BiFunction<String, String, String> strategy) {

        return this.aliasReplace(aliasCenterKey, Arrays.asList(originals), strategy).toArray(new String[0]);
    }

    /**
     * 别名替换.
     *
     * @param aliasCenterKey 别名替换 key
     * @param originals      待替换原始数据
     * @param function       转换函数 , 给予 [原始,别名] 2个字符，自由组合为结果字符
     * @return the list
     */
    private List<String> aliasReplace(AliasCenterKey aliasCenterKey, List<String> originals,
                                      BiFunction<String, String, String> function) {

        this.checkNotStart();
        // 匹配对应别名生产者
        final AliasProducer aliasProducer = register.get(aliasCenterKey.getAliasProducerKey().key());
        if (aliasProducer == null) {
            throw new AliasException("aliasCenterKey:" + aliasCenterKey.toString() + " doesn't exist");
        }
        // 待返回数据集
        final List<String> r = new ArrayList<>();
        // 替换后的数据集
        final List<String> alias = aliasProducer.alias(aliasCenterKey, originals);

        // 校验数据是否合法，保证原始数据集合与替换别名后集合数据一致
        if (alias.size() != originals.size()) {
            throw new AliasException("alias originals size unlikeness");
        }

        // 使用 BiFunction 函数进行数据处理
        for (int i = 0; i < originals.size(); i++) {
            final String original = originals.get(i);
            final String alia = alias.get(i);
            r.add(function.apply(original, alia));
        }
        return r;
    }

    /**
     * 关闭资源.
     */
    void close() {
        for (AliasProducer aliasProducer : this.register.values()) {
            try {
                aliasProducer.close();
            } catch (Exception ignored) {
                // Empty on purpose or missing piece of code
            }
        }
    }
}
