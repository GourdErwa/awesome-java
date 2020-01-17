package io.gourd.java.jvm.bytecode;

/**
 * @author Li.Wei by 2020/1/16
 */
public class InvokeDynamic {

    public void lambda1() {
        Runnable runnable = () -> {
        };
    }

    public void lambda2() {
        Runnable runnable = () -> runImpl();
    }

    private static void runImpl() {
    }

    public void lambda3() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
    }

}

class InvokeDynamicExample {
    public void lambda1() {
        Runnable runnable = () -> {
            int i = 1;
        };
        runnable.run();
    }
}
class InvokeDynamicExample2 {

    private final Object o = new Object();
    public void lambda1() {
        Runnable runnable = () -> {
            int i = 1;
            int s = o.hashCode();
        };
        runnable.run();
    }

    public static void main(String[] args) {
        System.out.println("");
        InvokeDynamicExample2 invokeDynamicExample2 = new InvokeDynamicExample2();
        invokeDynamicExample2.lambda1();
    }
}
