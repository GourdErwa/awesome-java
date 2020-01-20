package io.gourd.java.jvm.bytecode;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Li.Wei by 2020/1/20
 */
public class Null {

    private Object o1 = null;

    private Object o2 = null;

    private Object[] nullObj = new Object[]{null, null};

    public boolean isNull(Object o) {
        boolean r = o == null;
        return r;
    }

    public static void main(String[] args) {
        Null obj = new Null();

        System.out.println(ClassLayout.parseClass(Null.class).toPrintable());
        System.out.println();

        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        System.out.println();

        //查看对象外部信息
        System.out.println(GraphLayout.parseInstance(obj).toPrintable());
        System.out.println();

        //获取对象总大小
        System.out.println("size : " + GraphLayout.parseInstance(obj).totalSize());


        Map map = new HashMap();
        map.put(null, null);
        Object o = map.get(null);
        System.out.println(o);
    }

}
