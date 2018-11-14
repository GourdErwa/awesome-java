/*
 *
 * The MIT License
 * Copyright © 2018-2018 GourdErwa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */
package com.gourd.design.patterns.mediator;

import com.gourd.design.patterns.mediator.member.Hobbit;
import com.gourd.design.patterns.mediator.member.Hunter;
import com.gourd.design.patterns.mediator.member.Rogue;
import com.gourd.design.patterns.mediator.member.Wizard;

/**
 * 中介者模式，定义了一个中介对象来封装一系列对象之间的交互关系。
 * 中介者使各个对象之间不需要显式地相互引用，从而使耦合性降低，而且可以独立地改变它们之间的交互行为。
 *
 * @author wei.Li
 */
public final class App {

    private App() {
        //not called
    }

    /**
     * Program entry point
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        // create party and members
        Party party = new PartyImpl();

        Hobbit hobbit = new Hobbit();
        Wizard wizard = new Wizard();
        Rogue rogue = new Rogue();
        Hunter hunter = new Hunter();

        // add party members
        party.addMember(hobbit);
        party.addMember(wizard);
        party.addMember(rogue);
        party.addMember(hunter);

        // perform actions -> the other party members
        // are notified by the party
        hobbit.act(Action.ENEMY);
        wizard.act(Action.TALE);
        rogue.act(Action.GOLD);
        hunter.act(Action.HUNT);
    }
}
