package io.gourd.java.jvm.oom;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串常量池溢出
 * <p>
 * VM Args：-Xmx2m
 * <p>
 * 自 JDK 7 起，原本存放在永久代的字符串常量池被移至 Java 堆之中
 * <p>
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * at java.util.HashMap.resize(HashMap.java:704)
 * at java.util.HashMap.putVal(HashMap.java:663)
 * at java.util.HashMap.put(HashMap.java:612)
 * at java.util.HashSet.add(HashSet.java:220)
 * at io.gourd.java.jvm.oom.RuntimeConstantPoolOOM.main(RuntimeConstantPoolOOM.java:19)
 * <p>
 * <p>
 * 方法区出现内存溢出异常
 * Caused by: java.lang.OutOfMemoryError: PermGen space
 * at java.lang.ClassLoader.defineClass1(Native Method)
 * at java.lang.ClassLoader.defineClassCond(ClassLoader.java:632)
 * at java.lang.ClassLoader.defineClass(ClassLoader.java:616)
 *
 * @author Li.Wei by 2020/1/5
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用 Set 保持着常量池引用，避免 Full GC 回收常量池行为
        final Set<String> set = new HashSet<>();
        // 在 short 范围内足以让 6MB 的 PermSize 产生 OOM 了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
