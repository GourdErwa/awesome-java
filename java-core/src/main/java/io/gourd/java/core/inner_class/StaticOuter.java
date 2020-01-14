package io.gourd.java.core.inner_class;

import lombok.extern.slf4j.Slf4j;

/**
 * 静态内部类：static 修饰的内部类我们称作静态内部类
 * <p>
 * 特点：不能使用外部类的非 static 成员变量和成员方法
 * <p>
 * 非静态内部类编译后会默认的保存一个指向外部类的引用，而静态类却没有
 *
 * @author Li.Wei by 2020/1/13
 */
@Slf4j
public class StaticOuter {

    private static String name = "1";

    public static void showName1() {
        String name = "3";

        log.info("name={}", name);
        log.info("Outer.this.name={}", StaticOuter.name);
    }

    static class StaticInner {
        public String name = "2";

        public void showName() {
            String name = "3";

            showName1();
            log.info("name={}", name);
            log.info("this.name={}", this.name);
            log.info("Outer.this.name={}", StaticOuter.name);
        }
    }

    public static void main(String[] ages) {
        final StaticInner oi = new StaticInner();
        oi.showName();
    }
}
