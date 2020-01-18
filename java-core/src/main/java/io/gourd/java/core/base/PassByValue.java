package io.gourd.java.core.base;

/**
 * 字节码角度分析
 * 值传递引用传递的本质
 */
public class PassByValue {
    private  int i = 10; // 基础数据
    // 不改变方法参数基础数据的值
    public int intOriginal(int o) {
        return o;
    }

    // 改变方法参数基础数据的值
    public int intUpdate(int o) {
        o = o + 10;
        return o;
    }

    public static void main(String[] args) {
        PassByValue pass = new PassByValue();
        // 模拟调用过程
        int intOriginal =pass.intOriginal(pass.i);
        int intUpdate =pass.intUpdate(pass.i);
    }
}
/*
Classfile /C:/Users/Administrator/IdeaProjects/java-advanced/java-core/build/classes/java/main/io/gourd/java/core/base/PassByValue.class
  Last modified 2020年1月18日; size 742 bytes
  MD5 checksum 02931091f8dc1b622614c3febe0f7a7f
  Compiled from "PassByValue.java"
public class io.gourd.java.core.base.PassByValue
  minor version: 0
  major version: 55
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #3                          // io/gourd/java/core/base/PassByValue
  super_class: #7                         // java/lang/Object
  interfaces: 0, fields: 1, methods: 4, attributes: 1
Constant pool:
   #1 = Methodref          #7.#28         // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#29         // io/gourd/java/core/base/PassByValue.i:I
   #3 = Class              #30            // io/gourd/java/core/base/PassByValue
   #4 = Methodref          #3.#28         // io/gourd/java/core/base/PassByValue."<init>":()V
   #5 = Methodref          #3.#31         // io/gourd/java/core/base/PassByValue.intOriginal:(I)I
   #6 = Methodref          #3.#32         // io/gourd/java/core/base/PassByValue.intUpdate:(I)I
   #7 = Class              #33            // java/lang/Object
   #8 = Utf8               i
   #9 = Utf8               I
  #10 = Utf8               <init>
  #11 = Utf8               ()V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               LocalVariableTable
  #15 = Utf8               this
  #16 = Utf8               Lio/gourd/java/core/base/PassByValue;
  #17 = Utf8               intOriginal
  #18 = Utf8               (I)I
  #19 = Utf8               o
  #20 = Utf8               intUpdate
  #21 = Utf8               main
  #22 = Utf8               ([Ljava/lang/String;)V
  #23 = Utf8               args
  #24 = Utf8               [Ljava/lang/String;
  #25 = Utf8               pass
  #26 = Utf8               SourceFile
  #27 = Utf8               PassByValue.java
  #28 = NameAndType        #10:#11        // "<init>":()V
  #29 = NameAndType        #8:#9          // i:I
  #30 = Utf8               io/gourd/java/core/base/PassByValue
  #31 = NameAndType        #17:#18        // intOriginal:(I)I
  #32 = NameAndType        #20:#18        // intUpdate:(I)I
  #33 = Utf8               java/lang/Object
{
  private int i;
    descriptor: I
    flags: (0x0002) ACC_PRIVATE

  public io.gourd.java.core.base.PassByValue();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: bipush        10
         7: putfield      #2                  // Field i:I
        10: return
      LineNumberTable:
        line 7: 0
        line 8: 4
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      11     0  this   Lio/gourd/java/core/base/PassByValue;

  public int intOriginal(int);
    descriptor: (I)I
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=2, args_size=2
         0: iload_1
         1: ireturn
      LineNumberTable:
        line 11: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       2     0  this   Lio/gourd/java/core/base/PassByValue;
            0       2     1     o   I

  public int intUpdate(int);
    descriptor: (I)I
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=2
         0: iload_1
         1: bipush        10
         3: iadd
         4: istore_1
         5: iload_1
         6: ireturn
      LineNumberTable:
        line 16: 0
        line 17: 5
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       7     0  this   Lio/gourd/java/core/base/PassByValue;
            0       7     1     o   I

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=4, args_size=1
         0: new           #3                  // class io/gourd/java/core/base/PassByValue
         3: dup
         4: invokespecial #4                  // Method "<init>":()V
         7: astore_1
         8: aload_1
         9: aload_1
        10: getfield      #2                  // Field i:I
        13: invokevirtual #5                  // Method intOriginal:(I)I
        16: istore_2
        17: aload_1
        18: aload_1
        19: getfield      #2                  // Field i:I
        22: invokevirtual #6                  // Method intUpdate:(I)I
        25: istore_3
        26: return
      LineNumberTable:
        line 21: 0
        line 23: 8
        line 24: 17
        line 25: 26
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      27     0  args   [Ljava/lang/String;
            8      19     1  pass   Lio/gourd/java/core/base/PassByValue;
           17      10     2 intOriginal   I
           26       1     3 intUpdate   I
}
SourceFile: "PassByValue.java"


 */
