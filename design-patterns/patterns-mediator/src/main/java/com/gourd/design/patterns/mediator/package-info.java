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
 * 中介者模式示例
 * <p>
 * 房产中介接收社会人信息，包括房屋求购、出售、出租、找房等信息 {@link com.gourd.design.patterns.mediator.Mediator} 作为生活需求用户，我们向中介发布一条信息后，中介会通知所有有需求用户
 * {@link com.gourd.design.patterns.mediator.Colleague} 需求用户无需私下互相交互，由中介者统一管理信息。
 * <p>
 * 我们可以为每个需求用户绑定需求，例如目前是求购者、卖房者，这样{@link com.gourd.design.patterns.mediator.Mediator#act(Colleague, Action)} 通知用户时可以按需通知
 * <p>
 * WIKI官方示例更倾向于-状态模式 https://en.wikipedia.org/wiki/Mediator_pattern#Java
 *
 * @author wei.Li by 2018/11/14
 */
package com.gourd.design.patterns.mediator;

