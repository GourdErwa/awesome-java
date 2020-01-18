package io.gourd.java.core.base;

/**
 * 自动装箱拆箱
 */
public class Box {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;

        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;

        System.out.println(c == d);
        System.out.println(c.equals(d));

        System.out.println(e == f);
        System.out.println(e.equals(f));

        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));
        System.out.println(g.equals(a + b));

        Integer a1 = 3;
        long b1 = 3;

        System.out.println(a1 == b1);
    }
}
class Box2{
    public static void main(String[] args) {
        Integer a = 321;
        Integer b = 321;
        int c = 321;

        System.out.println(a == b);

        System.out.println(a.equals(b));


        System.out.println(a * b);

        System.out.println(a == c);

        System.out.println(a * c);

        int d = a;

        System.out.println(a.equals(c));
    }
}
