package io.gourd.java.jvm.bytecode;

/**
 * 逃逸分析
 *
 * @author Li.Wei by 2020/1/15
 */
public class EscapeAnalysis {

    private int age;

    public EscapeAnalysis(int age) {
        this.age = age;
    }

    public int escape() {
        // 优化为：age 直接在栈上分配，return age + 8;
        return new EscapeAnalysis(10).age + 8;
    }

}
