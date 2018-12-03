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

/**
 * The type Application.
 *
 * @author wei.Li
 */
public final class Application {

    private Application() {
        // not called
    }

    /**
     * Program entry point
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        // 富豪访问者
        final VisitorRegal visitorRegal = new VisitorRegal();
        // 学生访问者
        final VisitorStudent visitorStudent = new VisitorStudent();

        // 奢侈品
        final Element longPriceElement = new ElementLongPrice("奢侈品", 1000000d);
        // 低价小物品
        final Element lowPriceElement = new ElementLowPrice("小物品", 10d);

        // 结构对象：元素产生者，一般容纳在多个不同类、不同接口的容器，如List、Set、Map等
        final ObjectStructure os = new ObjectStructure().add(longPriceElement).add(lowPriceElement);

        os.action(visitorRegal);
        os.action(visitorStudent);
    }

}
