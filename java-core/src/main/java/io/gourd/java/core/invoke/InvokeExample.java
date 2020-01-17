package io.gourd.java.core.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * @author Li.Wei by 2020/1/16
 */
public class InvokeExample {

    public String stringMethod(String s) {
        return s + "|" + s;
    }

    public static String stringStaticMethod(String s) {
        return s + "|" + s;
    }
    public static void main(String[] args) throws Throwable {
        InvokeExample example = new InvokeExample();
        // 获取方法类型
        MethodType methodType = MethodType.methodType(String.class, String.class);
        // 获取方法的句柄
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        Object r = lookup.findVirtual(InvokeExample.class,
            "stringMethod",
            methodType)
            .bindTo(example)
            .invoke("invoke stringMethod");

        System.out.println(r);


        MethodHandle replaceStatic = lookup.findStatic(InvokeExample.class, "stringStaticMethod", methodType);
        Object invoke1 = replaceStatic.invoke("invoke stringStaticMethod");

        System.out.println(invoke1);
        String staticMethod = ((String) replaceStatic.invokeExact("invoke stringStaticMethod"));
        System.out.println(staticMethod);


        Method stringMethod = Class.forName(InvokeExample.class.getName()).getMethod("stringMethod", String.class);
        Object reflect = stringMethod.invoke(example, "reflect");
        System.out.println(reflect);

    }
}
