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

/**
 * @author wei.Li by 2018-11-24
 */
package com.gourd.design.patterns.visitor;

/*
 * 目前电商对于高消费&低消费用户群存在同一商品展示不同价格 作为商品的购买者，我们认为是访问者 {@link com.gourd.design.patterns.visitor.Visitor} 作为被浏览的商品，我们认为是元素
 * {@link com.gourd.design.patterns.visitor.Element} <p> 不同消费能力的访问者看到的商品价格不同 {@linkplain
 * com.gourd.design.patterns.visitor.Visitor#visit(com.gourd.design.patterns.visitor.ElementLowPrice) 访问者看到低价商品的行为处理}
 * {@linkplain com.gourd.design.patterns.visitor.Visitor#visit(com.gourd.design.patterns.visitor.ElementLongPrice)
 * 访问者看到高价商品的行为处理}
 */
