package io.gourd.java.jvm.ref;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Li.Wei by 2020/1/4
 */
@Slf4j
public class PhantomReferenceExample {

    private static final ReferenceQueue<byte[]> RQ = new ReferenceQueue<>();

    public static void main(String[] args) {
        final Map<PhantomReference<byte[]>, Object> map = new HashMap<>();

        final Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                PhantomReference<byte[]> k;
                while ((k = (PhantomReference<byte[]>) RQ.remove()) != null) {
                    log.info("第 {} 个回收对象，对象打印为：{}", cnt++, k);
                }
            } catch (InterruptedException ignored) {
            }
        });
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 1000; i++) {
            map.put(new PhantomReference<>(new byte[1024 * 1024], RQ), new Object());
        }

        log.info("map.size ：{}", map.size());
    }
    /* 部分输出如下：
     * 第 789 个回收对象，对象打印为：java.lang.ref.PhantomReference@26653222
     * 第 790 个回收对象，对象打印为：java.lang.ref.PhantomReference@553f17c
     * 第 791 个回收对象，对象打印为：java.lang.ref.PhantomReference@56ac3a89
     * 第 792 个回收对象，对象打印为：java.lang.ref.PhantomReference@6fd02e5
     * 第 793 个回收对象，对象打印为：java.lang.ref.PhantomReference@2b98378d
     * 第 794 个回收对象，对象打印为：java.lang.ref.PhantomReference@26be92ad
     * 第 795 个回收对象，对象打印为：java.lang.ref.PhantomReference@6d00a15d
     * map.size ：1000
     */
}
