package io.gourd.java.jvm.bytecode;

import java.util.List;
import java.util.RandomAccess;

/**
 * for、foreach 循环底层实现原理，以及如何判断集合支持 foreach 循环
 *
 * @author Li.Wei by 2020/1/20
 */
public class For {

    public void foreachMethod(List<String> list) {
        for (String s : list) {
        }

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
        }
    }

    public void foriMethod() {
        for (int i = 0; i < 10; i++) {
        }
    }


    // 如何判断集合支持 foreach 循环
    public <T extends Object> void randomAccess(List<T> list) {
        if (list instanceof RandomAccess) {
            for (int i = 0; i < list.size(); i++) {
                T t = list.get(i);
            }

        } else {
            for (T t : list) {
                System.out.println(t);
            }
        }
    }
}
