package io.gourd.java.jvm;

/**
 * @author Li.Wei by 2020/1/14
 */
public class ByteCode {

    private String name;
    private int age;

    public ByteCode(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean age(int add) {
        synchronized (this) {
            return age > 18;
        }
    }

    public void lam() {
        Comparable<Integer> comparable = o -> age;

        comparable.compareTo(age);
    }

    String forNullVal = "1111111111111111111111111111111111111111111111111111111";
    public void forNull() {
        Integer integer = null;
        if (integer == null) {
            return;
        }
    }
    public static void main(String[] args) {
        final ByteCode code = new ByteCode("Lw", 20);
        final boolean gt = code.age(10);
        System.out.println(gt);
    }
}
