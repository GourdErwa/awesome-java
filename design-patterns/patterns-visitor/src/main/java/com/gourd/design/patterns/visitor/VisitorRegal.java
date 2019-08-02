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

package com.gourd.design.patterns.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 富豪类型的访问者
 *
 * @author wei.Li by 2018-11-24
 */
public class VisitorRegal implements Visitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorRegal.class);

    @Override
    public void visit(ElementLongPrice element) {
        final double realPrice = element.getRealPrice();
        final double result = realPrice * 1.2D;
        LOGGER.info("土豪 看到[{}]价格真实价[{}],显示价格[{}]", element.getName(), realPrice, result);
    }

    @Override
    public void visit(ElementLowPrice element) {
        final double realPrice = element.getRealPrice();
        final double result = realPrice * 1.1D;
        LOGGER.info("土豪 看到[{}]价格真实价[{}],显示价格[{}]", element.getName(), realPrice, result);
    }

}
