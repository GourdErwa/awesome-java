package io.gourd.java.jvm.jol;

import org.openjdk.jol.info.ClassLayout;

import static java.lang.System.out;

/**
 * @author Li.Wei by 2020/1/4
 */
public class JolObjectHeard {

    public static void main(String[] args) {
        final Obj obj = new Obj();
        final ClassLayout classLayout = ClassLayout.parseInstance(obj);

        // 初始化后打印
        out.println(classLayout.toPrintable());

        synchronized (obj) {
            // 加锁后后打印
            out.println(classLayout.toPrintable());
        }
        // 解锁后打印
        out.println(classLayout.toPrintable());

        // 打印 obj 内部数组
        out.println(ClassLayout.parseInstance(obj.arrayVal0).toPrintable());
    }
}

class Obj {
    long longVal0 = 10;
    int intVal0;
    long longVal1;
    byte byteVal0;
    short shortVal0;
    String strVal0 = "hello world";
    String[] arrayVal0 = new String[]{strVal0, strVal0};
}
