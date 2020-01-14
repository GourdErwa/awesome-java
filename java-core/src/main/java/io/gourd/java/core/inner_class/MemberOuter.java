package io.gourd.java.core.inner_class;

import lombok.extern.slf4j.Slf4j;

/**
 * 成员内部类：位于外部类成员位置的类
 * <p>
 * 特点：可以使用外部类中所有的成员变量和成员方法（包括 private ）
 *
 * @author Li.Wei by 2020/1/13
 */
@Slf4j
public class MemberOuter {

    public String name = "1";

    class MemberInner {
        public String name = "2";

        public void showName() {
            String name = "3";

            log.info("name={}", name);
            log.info("this.name={}", this.name);
            log.info("Outer.this.name={}", MemberOuter.this.name);
        }
    }

    public static void main(String[] ages) {

        final MemberOuter memberOuter = new MemberOuter();
        final MemberOuter.MemberInner oi = memberOuter.new MemberInner();

        oi.showName();
    }
}

