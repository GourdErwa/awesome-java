package io.gourd.java.core.base;

/**
 * 字节码角度分析
 * 值传递引用传递的本质
 */
public class PassByRef {
    private Object ref = new Object(); // 对象引用

    // 不改变方法参数的引用
    public Object refOriginal(Object o) {
        return o;
    }

    // 改变方法参数的引用
    public Object refUpdate(Object o) {
        o = new Object();
        int hashCode = o.hashCode();
        return o;
    }

    public static void main(String[] args) {
        PassByRef pass = new PassByRef();
        // 模拟调用过程
        Object refOriginal =pass.refOriginal(pass.ref);
        Object refUpdate =pass.refUpdate(pass.ref);
    }
}
/*
{
  public java.lang.Object refOriginal(java.lang.Object);
    Code:
      stack=1, locals=2, args_size=2
         0: aload_1
         1: areturn
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       2     0  this   Lio/gourd/java/core/base/PassByRef;
            0       2     1     o   Ljava/lang/Object;

  public java.lang.Object refUpdate(java.lang.Object);
    Code:
      stack=2, locals=3, args_size=2
         0: new           #2                  // class java/lang/Object
         3: dup
         4: invokespecial #1                  // Method java/lang/Object."<init>":()V
         7: astore_1
         8: aload_1
         9: invokevirtual #4                  // Method java/lang/Object.hashCode:()I
        12: istore_2
        13: aload_1
        14: areturn
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      15     0  this   Lio/gourd/java/core/base/PassByRef;
            0      15     1     o   Ljava/lang/Object;
           13       2     2 hashCode   I

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=4, args_size=1
         0: new           #5                  // class io/gourd/java/core/base/PassByRef
         3: dup
         4: invokespecial #6                  // Method "<init>":()V
         7: astore_1
         8: aload_1
         9: aload_1
        10: getfield      #3                  // Field ref:Ljava/lang/Object;
        13: invokevirtual #7                  // Method refOriginal:(Ljava/lang/Object;)Ljava/lang/Object;
        16: astore_2
        17: aload_1
        18: aload_1
        19: getfield      #3                  // Field ref:Ljava/lang/Object;
        22: invokevirtual #8                  // Method refUpdate:(Ljava/lang/Object;)Ljava/lang/Object;
        25: astore_3
        26: return
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      27     0  args   [Ljava/lang/String;
            8      19     1  pass   Lio/gourd/java/core/base/PassByRef;
           17      10     2 refOriginal   Ljava/lang/Object;
           26       1     3 refUpdate   Ljava/lang/Object;
}
SourceFile: "PassByRef.java"


 */
