package io.gourd.java.core.base;

/**
 * 可变长参数
 *
 * @author Li.Wei by 2020/1/19
 */
public class VarargsExample {
    // 基类
    static class Base {
        void print(String... args) {
            System.out.println("Base");
        }
    }

    // 子类，覆写父类方法
    static class Sub extends Base {
        @Override
        void print(String... args) {
            System.out.println("Sub");
        }
    }

    public static void method(String first, String second) {
        System.out.println("first,second");
    }

    public static void method(String first, String... args) {
        System.out.println("first,args");
    }

    public static void main(String[] args) {
        VarargsExample.method("a", "b");

        // 向上转型
        Base base = new Sub();
        base.print("hello");

        // 不转型
        Sub sub = new Sub();
        sub.print("hello");
    }
}


