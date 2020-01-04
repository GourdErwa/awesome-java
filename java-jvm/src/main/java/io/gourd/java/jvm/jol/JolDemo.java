package io.gourd.java.jvm.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

// org.openjdk.jol 使用示例
public class JolDemo {

    public static void main(String[] args) {
        Object obj = new JolDemo();

        System.out.println(ClassLayout.parseClass(JolDemo.class).toPrintable());

        System.out.println();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println();
        //查看对象外部信息
        System.out.println(GraphLayout.parseInstance(obj).toPrintable());
        System.out.println();
        //获取对象总大小
        System.out.println("size : " + GraphLayout.parseInstance(obj).totalSize());
    }
}
