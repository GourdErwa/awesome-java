package io.gourd.java.jvm.bytecode;

/**
 * 静态分派
 *
 * @author Li.Wei by 2020/1/15
 */
public class StaticDispatch {
    interface Human {
    }

    static class Man implements Human {
    }

    static class Woman implements Human {
    }

    public void sayHello(Human guy) {
        System.out.println("Human sayHello");
    }

    public void sayHello(Man guy) {
        System.out.println("Man sayHello");
    }

    public void sayHello(Woman guy) {
        System.out.println("Woman sayHello");
    }

    public static void main(String[] args) {
        final Human human = new Man();

        final StaticDispatch dispatch = new StaticDispatch();

        dispatch.sayHello(human);
        dispatch.sayHello((Woman) human);
    }
}
