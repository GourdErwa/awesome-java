package io.gourd.java.jvm.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出
 * <p>
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 * <p>
 * 直接内存（Direct Memory）的容量大小可通过-XX：MaxDirectMemorySize 参数来指定，
 * 如果不去指定，则默认与 Java 堆最大值（由-Xmx 指定）一致
 * <p>
 * Exception in thread "main" java.lang.OutOfMemoryError
 * at sun.misc.Unsafe.allocateMemory(Native Method)
 * at io.gourd.java.jvm.oom.DirectMemoryOOM.main(DirectMemoryOOM.java:25)
 *
 * @author Li.Wei by 2020/1/5
 */
public class DirectMemoryOOM {
    private static final int _5MB = 5 * 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_5MB);
        }
    }
}
