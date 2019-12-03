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

import io.gourd.demand.alias.replacement.producer.base.instance.AliasProducerGameItem;
import io.gourd.demand.alias.replacement.producer.base.instance.AliasProducerGameItemOperate;
import io.gourd.demand.alias.replacement.producer.base.instance.AliasProducerGameServer;
import io.gourd.demand.alias.replacement.producer.base.instance.AliasProducerKeyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 使用示例演示.
 *
 * @author wei.Li by 2018/10/23
 */
public final class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private Application() {
        // not called
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        /*
         * 注册别名替换中心
         */
        final AliasCenter aliasCenter =
            AliasCenter.create().register(new AliasProducerGameItem()).register(new AliasProducerGameItemOperate())
                .register(new AliasProducerGameServer()).start();

        aliasGame1(aliasCenter);
        aliasCenter.reLoadAliasProducer();

        aliasGame2(aliasCenter);
        aliasCenter.reLoadAliasProducer();

        /*
         * 重载所有别名生产方别名数据
         * 应用于缓存更新等操作
         */
        aliasCenter.reLoadAliasProducer();
        /*
         * 系统停止时
         * 关闭资源
         */
        aliasCenter.close();
    }

    /**
     * aliasGame1
     *
     * @param aliasCenter aliasCenter
     */
    private static void aliasGame1(AliasCenter aliasCenter) {
        /*
         * 示例
         * 替换 game01 应用下道具别名, 道具ID分别为 101,102
         */
        final String[] game01Originals = {"101", "102"};
        final String[] game01s =
            aliasCenter.aliasReplace(AliasCenterKey.create("game01", AliasProducerKeyEnum.GAME_ITEM), game01Originals,
                AliasCenter.BI_FUNCTION_PARENTHESES);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("原始数据: {} , 替换别名后 {}", Arrays.toString(game01Originals), Arrays.toString(game01s));
        }
    }

    /**
     * aliasGame2
     *
     * @param aliasCenter aliasCenter
     */
    private static void aliasGame2(AliasCenter aliasCenter) {
        /*
         * 示例
         * 替换 game02 应用下道具别名, 道具ID分别为 101,102
         */
        final String[] game02Originals = {"101", "102"};
        final String[] game02s =
            aliasCenter.aliasReplace(AliasCenterKey.create("game02", AliasProducerKeyEnum.GAME_ITEM), game02Originals,
                AliasCenter.BI_FUNCTION_PARENTHESES);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("原始数据: {} , 替换别名后 {}", Arrays.toString(game02Originals), Arrays.toString(game02s));
        }
    }
}
