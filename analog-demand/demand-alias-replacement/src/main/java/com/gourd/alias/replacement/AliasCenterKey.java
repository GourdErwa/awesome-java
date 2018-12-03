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

package com.gourd.alias.replacement;

import com.gourd.alias.replacement.producer.base.AliasProducerKey;

import java.util.Objects;

/**
 * 别名替换注册查询替换使用唯一 KEY 值. 该方法必须重写对应 equals&hashCode 保证数据作为KEY去重逻辑无误
 *
 * @author wei.Li by 2018/10/23
 */
public final class AliasCenterKey {

    /**
     * 应用名称
     */
    private String app;
    /**
     * 别名生产者 KEY
     */
    private AliasProducerKey aliasProducerKey;

    private AliasCenterKey(String app, AliasProducerKey aliasProducerKey) {
        this.app = app;
        this.aliasProducerKey = aliasProducerKey;
    }

    /**
     * Create alias center key.
     *
     * @param app              the app
     * @param aliasProducerKey the alias producer key
     * @return the alias center key
     */
    static AliasCenterKey create(String app, AliasProducerKey aliasProducerKey) {
        return new AliasCenterKey(app, aliasProducerKey);
    }

    /**
     * Gets app.
     *
     * @return the app
     */
    public String getApp() {
        return app;
    }

    /**
     * Gets alias producer key.
     *
     * @return the alias producer key
     */
    public AliasProducerKey getAliasProducerKey() {
        return aliasProducerKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AliasCenterKey)) {
            return false;
        }
        AliasCenterKey that = (AliasCenterKey) o;
        return Objects.equals(getApp(), that.getApp())
            && Objects.equals(getAliasProducerKey(), that.getAliasProducerKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApp(), getAliasProducerKey());
    }
}
