package io.gourd.java.core.base;

public class Init {

    public static void main(String[] args) {
        Son son1 = new Son("11");
        Son son2 = new Son("11");
    }
}



class Father {
    public static final String STATIC_FINAL = "static-final-Father";
    public static String STATIC = "static-Father";
    public static final String STATIC_METHOD = staticMethod();
//    static {
//        System.out.println("static{}-Father");
//        System.out.println(STATIC_FINAL);
//        System.out.println(STATIC);
//        System.out.println(STATIC_METHOD);
//    }

    public static String staticMethod() {
        return "staticMethod";
    }
    private String name = "name-Father";
    private Object o = new Object();

    public Father(String name) {
        this.name = name;
        System.out.println("Constructor-Father");
    }

    {
        System.out.println(name);
    }
}

class Son extends Father {
    public static final String STATIC = "static-Son";

    static {
        System.out.println("static{}-Son");
        System.out.println(STATIC);
    }

    public static final String STATIC1 = "static-Son";
    public Son(String name) {
        super(name);
        System.out.println("Constructor-Son");
    }
}

class ConstantInit {
    public static final String STATIC_FINAL = "static-final-ConstantInit";
    static {
        System.out.println(STATIC_FINAL);
    }
    public static String STATIC = "static-ConstantInit";
    static {
        System.out.println(STATIC);
    }
}

class RefObjInit {
    public final String STATIC_FINAL = "ref-final-ConstantInit";
    public String STATIC = "ref-ConstantInit";
    public Object o = new Object();
}

class RefInit {
    private int i = 10;

    public RefInit o = new RefInit(); // o 里面的 i 字段在堆里
}
