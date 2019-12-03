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

package io.gourd.demand.alias.replacement.producer.base;

import io.gourd.demand.alias.replacement.AliasCenterKey;
import io.gourd.demand.alias.replacement.ClientPool;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 支持别名替换生产者接口.
 *
 * @author wei.Li by 2018/10/23
 */
public interface AliasProducer {

    /**
     * 计算过程依赖各种客户端
     *
     * @return ClientPool
     */
    default ClientPool clientPool() {
        return ClientPool.CLIENT_POOL;
    }

    /**
     * 生产者指定 KEY.
     *
     * @return the alias producer enum
     */
    AliasProducerKey aliasProducerKey();

    /**
     * 载入别名替换数据.
     */
    void load();

    /**
     * 重新载入别名替换数据.
     */
    void reLoad();

    /**
     * 别名替换.
     *
     * @param aliasCenterKey 别名替换指定替换内容
     * @param original       待替换原始内容
     * @return 替换结果 string
     */
    String alias(AliasCenterKey aliasCenterKey, String original);

    /**
     * 别名替换.
     *
     * @param aliasCenterKey 别名替换指定替换内容
     * @param originals      待替换原始内容
     * @return 替换结果 list
     */
    default List<String> alias(AliasCenterKey aliasCenterKey, List<String> originals) {
        return originals.stream().map(original -> alias(aliasCenterKey, original)).collect(Collectors.toList());
    }

    /**
     * 关闭资源.
     */
    void close();

}
