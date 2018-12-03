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
package com.gourd.design.patterns.mediator;

import com.gourd.design.patterns.mediator.colleague.Traveler;
import com.gourd.design.patterns.mediator.colleague.Aboriginal;
import com.gourd.design.patterns.mediator.colleague.CollegeStudents;
import com.gourd.design.patterns.mediator.colleague.Capitalist;

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

        // 房产中间创建
        final Mediator estateAgentsMediator = new EstateAgentsMediator();

        // 旅行者
        final Traveler traveler = new Traveler();
        // 资本家
        final Capitalist capitalist = new Capitalist();
        // 大学生
        final CollegeStudents collegeStudents = new CollegeStudents();
        // 土著
        final Aboriginal aboriginal = new Aboriginal();

        // 需求用户连接 中介者
        estateAgentsMediator.addColleague(traveler).addColleague(capitalist).addColleague(collegeStudents)
            .addColleague(aboriginal);

        // 需求用户发出动作信息
        traveler.act(Action.RENT);
        capitalist.act(Action.BUY);
        collegeStudents.act(Action.LOOK);
        aboriginal.act(Action.SELLING);
    }
}
