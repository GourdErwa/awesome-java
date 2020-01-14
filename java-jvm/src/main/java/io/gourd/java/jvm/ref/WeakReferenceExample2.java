package io.gourd.java.jvm.ref;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * -Xmx200m -XX:+PrintGC
 *
 * @author Li.Wei by 2020/1/4
 */
@Slf4j
public class WeakReferenceExample2 {

    public static void main(String[] args) throws InterruptedException {
        // 100M 的缓存数据

        Data data = new Data(new byte[10 * 1024 * 1024]);

        final Entry entry = new Entry(data);

        log.info("第一次 GC 前 {}", data == null);
        log.info("第一次 GC 前 {}", entry == null);

        // 进行一次 GC 后查看对象的回收情况
        System.gc();
        //等待 GC
        Thread.sleep(1000);
        log.info("第一次 GC 后 {}", data == null);
        log.info("第一次 GC 后 {}", entry == null);

        // 将缓存数据的强引用去除
        data = null;
        System.gc();
        // 等待 GC
        Thread.sleep(1000);
        log.info("第二次 GC 后 {}", data == null);
        log.info("第二次 GC 后 {}", entry.data);

    }

    @AllArgsConstructor
    static class Data {
        byte[] value;
    }

    @AllArgsConstructor
    static class Entry {
        Data data;
    }

//    static class Entry extends WeakReference<Data> {
//
//        public Entry(Data referent) {
//            super(referent);
//        }
//    }
}
