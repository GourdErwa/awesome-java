package io.gourd.java.core.inner_class;

import lombok.extern.slf4j.Slf4j;

/**
 * 局部内部类：定义在一个方法或者一个作用域里面的类
 * <p>
 * 特点：作用域发生了变化，只能在自身所在方法和属性中被使用
 *
 * @author Li.Wei by 2020/1/13
 */
@Slf4j
public class LocalOuter {

    private String name = "1";

    {
        class LocalInner {

            public void showName() {
                log.info("name={}", name);
                log.info("LocalOuter.this.name={}", LocalOuter.this.name);
            }
        }
        final LocalInner oi = new LocalInner();
        oi.showName();
    }

    public void showName() {
        String name = "2";
        int age = 3;

        class LocalInner {
            public String name = "4";

            public void showName() {
                String name = "5";
                log.info("age={}", age);
                log.info("name={}", name);
                log.info("this.name={}", this.name);
                log.info("LocalOuter.this.name={}", LocalOuter.this.name);
            }
        }

        final LocalInner oi = new LocalInner();
        oi.showName();
    }


    public static void main(String[] ages) {

        new LocalOuter().showName();
    }
}

