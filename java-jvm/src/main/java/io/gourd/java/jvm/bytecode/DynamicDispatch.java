package io.gourd.java.jvm.bytecode;

/**
 * 动态分派
 *
 * @author Li.Wei by 2020/1/15
 */
public class DynamicDispatch {
    interface Human {
        default void sayHello() {
            System.out.println("Human sayHello");
        }
    }

    static class Man implements Human {
        @Override
        public void sayHello() {
            System.out.println("Man sayHello");
        }
    }

    static class Woman implements Human {
        @Override
        public void sayHello() {
            System.out.println("Woman sayHello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        final Human woman = new Woman();

        man.sayHello();//Man sayHello
        woman.sayHello();//Woman sayHello

        man = new Woman();
        man.sayHello();//Woman sayHello
    }
}
