package io.gourd.java.jvm.ref;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.WeakReference;

/**
 * -Xmx200m -XX:+PrintGC
 *
 * @author Li.Wei by 2020/1/4
 */
@Slf4j
public class WeakReferenceExample {

    public static void main(String[] args) throws InterruptedException {
        // 10M 的缓存数据
        byte[] cacheData = new byte[10 * 1024 * 1024];

        // 将缓存数据用软引用持有
        final WeakReference<byte[]> cacheRef = new WeakReference<>(cacheData);

        log.info("第一次 GC 前 {}", cacheData == null);
        log.info("第一次 GC 前 {}", cacheRef.get() == null);

        // 进行一次 GC 后查看对象的回收情况
        System.gc();
        Thread.sleep(1000); // 等待 GC
        log.info("第一次 GC 后 {}", cacheData == null);
        log.info("第一次 GC 后 {}", cacheRef.get() == null);

        // 将缓存数据的强引用去除，你没有用了
        cacheData = null;
        System.gc();
        Thread.sleep(1000); //等待 GC
        log.info("第二次 GC 后 {}", cacheData == null);
        log.info("第二次 GC 后 {}", cacheRef.get() == null);
    }
    /* 打印内容如下：

     第一次 GC 前 false
     第一次 GC 前 false

    [GC (System.gc())  14908K->11560K(125952K), 0.0318128 secs]
    [Full GC (System.gc())  11560K->11425K(125952K), 0.0216147 secs]

     第一次 GC 后 false
     第一次 GC 后 false

    [GC (System.gc())  12090K->11457K(125952K), 0.0016023 secs]
    [Full GC (System.gc())  11457K->818K(125952K), 0.0093186 secs]

     第二次 GC 后 true
     第二次 GC 后 true
     */
}
