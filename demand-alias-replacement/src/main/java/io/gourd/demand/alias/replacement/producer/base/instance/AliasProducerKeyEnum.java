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

package io.gourd.demand.alias.replacement.producer.base.instance;

import io.gourd.demand.alias.replacement.producer.base.AliasProducer;
import io.gourd.demand.alias.replacement.producer.base.AliasProducerKey;

/**
 * 支持别名生产者类型定义. {@linkplain AliasProducer#aliasProducerKey()}
 *
 * @author wei.Li by 2018/10/23
 */
public enum AliasProducerKeyEnum implements AliasProducerKey {

    /**
     * 游戏道具别名替换.
     */
    GAME_ITEM,

    /**
     * 游戏道具操作别名替换.
     */
    GAME_ITEM_OPERATE,

    /**
     * 游戏服列表操作别名替换.
     */
    GAME_SERVER;

    @Override
    public String key() {
        return this.name();
    }
}
