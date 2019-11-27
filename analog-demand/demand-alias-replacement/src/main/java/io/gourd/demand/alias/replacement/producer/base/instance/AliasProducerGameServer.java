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

import io.gourd.demand.alias.replacement.producer.base.AbstractAliasProducer;
import io.gourd.demand.alias.replacement.producer.base.AliasProducerKey;

import java.util.HashMap;
import java.util.Map;

/**
 * 游戏服列表操作别名替换.
 *
 * @author wei.Li by 2018/10/23
 */
public class AliasProducerGameServer extends AbstractAliasProducer {

    @Override
    public AliasProducerKey aliasProducerKey() {
        return AliasProducerKeyEnum.GAME_SERVER;
    }

    @Override
    public Map<String, Map<String, String>> loadData() {

        final Map<String, Map<String, String>> data = new HashMap<>(10);

        final Map<String, String> game01Data = new HashMap<>(10);
        game01Data.put("101", "game01-101服");
        game01Data.put("102", "game01-102服");
        game01Data.put("103", "game01-103服");
        game01Data.put("104", "game01-104服");
        data.put("game01", game01Data);

        final Map<String, String> game02Data = new HashMap<>(10);
        game02Data.put("101", "game02-101服");
        game02Data.put("102", "game02-102服");
        game02Data.put("103", "game02-103服");
        game02Data.put("104", "game02-104服");
        data.put("game02", game02Data);

        return data;
    }

}
