package io.gourd.java.jvm.oom;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 本地内存跟踪
 * https://docs.oracle.com/en/java/javase/13/troubleshoot/diagnostic-tools.html#GUID-1F53A50E-86FF-491D-A023-8EC4F1D1AC77
 * <p>
 * 1.使用命令行选项或启动具有摘要或详细信息跟踪的JVM
 * -XX:NativeMemoryTracking=summary or -XX:NativeMemoryTracking=detail
 * <p>
 * 2.建立早期基线。使用NMT基线功能
 * jcmd <pid> VM.native_memory baseline
 * <p>
 * 3.使用以下命令监视内存更改
 * jcmd <pid> VM.native_memory detail.diff
 * <p>
 * 如果应用程序泄漏少量内存，则可能需要一段时间才能显示出来
 *
 * @author Li.Wei by 2020/1/19
 */
public class NativeMemoryTracking {

    static class Mem {
        private final byte[] bytes = new byte[100 * 1024 * 1024];
    }

    public static void main(String[] args) throws InterruptedException {
        final List<Mem> v = new ArrayList<>(10);

        for (int m = 0; m < 10000; m++) {
            new Thread(() -> v.add(new Mem()));

            Thread.sleep(1000);
        }
    }
}
