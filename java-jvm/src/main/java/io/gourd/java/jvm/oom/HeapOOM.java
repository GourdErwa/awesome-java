package io.gourd.java.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 堆内存溢出
 * <p>
 * VM Args: -Xms20m -Xmx20m
 * <p>
 * 异常堆栈信息「java.lang.OutOfMemoryError」会跟随进一步提示「Java heap space」
 * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 * @author Li.Wei by 2020/1/5
 */
public class HeapOOM {

    static class OomObj {
        private final byte[] bytes = new byte[1024 * 1024];
    }

    public static void main(String[] args) {

        final List<OomObj> list = new ArrayList<>();

        while (true) {
            list.add(new OomObj());
        }
    }
}
