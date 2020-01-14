package io.gourd.java.core.inner_class;

import lombok.extern.slf4j.Slf4j;

/**
 * 匿名内部类：没有名字的类，是内部类的简化写法
 * <p>
 * 特点：作用域发生了变化，只能在自身所在方法和属性中被使用
 *
 * @author Li.Wei by 2020/1/13
 */
@Slf4j
public class AnonymousOuter {
    interface AnonymousInner {
        void showName();
    }

    private String name1 = "1";

    public void showName1() {
        String name = "2";
        int age = 3;

        new AnonymousInner() {
            String name = "4";

            @Override
            public void showName() {
                String name = "5";
                log.info("age={}", age);
                log.info("name={}", name);
                log.info("this.name={}", this.name);
                log.info("this.name={}", name1);
            }
        }.showName();

    }

    public void showName2() {
        int age = 3;

        new Thread() {
            String name = "4";

            @Override
            public void run() {
                String name = "5";
                log.info("age={}", age);
                log.info("name={}", name);
                log.info("this.name={}", this.name);
                log.info("this.name={}", name1);
            }
        };

    }
}


