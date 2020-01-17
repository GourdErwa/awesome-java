package io.gourd.java.core.invoke;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * @author Li.Wei by 2020/1/16
 */
public class InvokeGrandFather {

    public static class GrandFather {
        public void say() throws Throwable {
            System.out.println("GrandFather.say");
        }
    }

    public static class Father extends GrandFather {
        @Override
        public void say() throws Throwable {
            System.out.println("Father.say");
        }
    }

    public static class Son extends Father {
        @Override
        public void say() throws Throwable {
            System.out.println("Son.say");

            // 必须遵守跟 Java 字节码里的 invokespecial 指令相同的限制
            // 它只能调用到传给 findSpecial() 方法的最后一个参数（“specialCaller”）的直接父类的版本。
            MethodHandles.lookup()
                .findSpecial(GrandFather.class,
                    "say",
                    MethodType.methodType(void.class),
                    Son.class)
                .bindTo(this)
                .invoke();
        }
    }

    public static class SonAccess extends Father {
        @Override
        public void say() throws Throwable {
            System.out.println("Son.say");

            // 必须遵守跟 Java 字节码里的 invokespecial 指令相同的限制
            // 它只能调用到传给 findSpecial() 方法的最后一个参数（“specialCaller”）的直接父类的版本。

            Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            lookupImpl.setAccessible(true); // 修改权限
            ((MethodHandles.Lookup) lookupImpl.get(null))
                .findSpecial(GrandFather.class,
                    "say",
                    MethodType.methodType(void.class),
                    Father.class) // 指定为 Father ，最终调用 GrandFather
                .bindTo(this)
                .invoke();
        }
    }

    public void invoke(GrandFather o) throws Throwable {

        MethodHandles.lookup()
            .findVirtual(o.getClass(), // 指定访问类或接口
                "say",
                MethodType.methodType(void.class))
            .bindTo(o) // 指定真正调用方法的对象（类似于方法第一个隐式的 this）
            .invoke(); // 方法参数
    }

    public static void main(String[] args) throws Throwable {
        InvokeGrandFather in = new InvokeGrandFather();

//        in.invoke(new Son());
//        in.invoke(new Father());
//        in.invoke(new GrandFather());

        new Son().say();
        new SonAccess().say();
    }
}
