package io.gourd.java.core.invoke;

import java.lang.reflect.Method;

/**
 * @author Li.Wei by 2020/1/16
 */
public class ReflectExample {

    public String stringMethod(String s) {
        return s + "|" + s;
    }

    public static String stringStaticMethod(String s) {
        return s + "|" + s;
    }

    public static void main(String[] args) throws Throwable {
        ReflectExample example = new ReflectExample();

        Method stringMethod = ReflectExample.class.getMethod("stringMethod", String.class);

        Object reflect = stringMethod.invoke(example, "reflect");// reflect|reflect
        System.out.println(reflect);

        Method stringStaticMethod = ReflectExample.class.
            getDeclaredMethod("stringStaticMethod", String.class);

        Object invoke = stringStaticMethod.invoke(null, "invoke");
        System.out.println(invoke);
    }
}
