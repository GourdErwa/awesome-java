package io.gourd.java.concurrency;

/**
 * final域的内存语义
 *
 * @author Li.Wei by 2019/12/6
 */
public class FinalExample {
    int i;                        //普通变量
    final int j;                   //final变量
    final int[] intArray;          //final是引用类型
    static FinalExample obj;

    public FinalExample() {       //构造函数
        i = 1;                    //写普通域
        j = 2;                    //写final域
        intArray = new int[2];    //写final引用类型域步骤1
        intArray[0] = 1;          //写final引用类型域步骤2
        intArray[1] = 2;          //写final引用类型域步骤3
    }

    public static void writer() { //写线程A执行
        obj = new FinalExample();
    }

    public static void reader() {  //读线程B执行
        FinalExample object = obj; //读对象引用
        int a = object.i;          //读普通域
        int b = object.j;          //读final域
        int c = object.intArray[0];//读final引用类型域
    }
}

class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample() {
        i = 1;                              //1 写final域
        obj = this;                         //2 this引用在此“逸出”
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
    }

    public static void reader() {
        if (obj != null) {                   //3
            int temp = obj.i;                //4
        }
    }
}
