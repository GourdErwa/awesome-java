package io.gourd.java.jvm.bytecode;

/**
 * 为什么对象被 new 以后在执行 dup 操作?
 *
 * @author Li.Wei by 2020/1/15
 */
public class NewDupCode {

    private int a;

    public NewDupCode(int a) {
        this.a = a;
    }

    public NewDupCode newDupCode() {
        return new NewDupCode(1);
    }

    public void voidNewDupCode() {
        new NewDupCode(1);
    }

    public int voidUseNewDupCode() {
        final NewDupCode o = new NewDupCode(1);
        return o.a + o.hashCode();
    }

    public int voidUseNonRefNewDupCode() {
        return new NewDupCode(1).a;
    }
}
/*
Classfile /Users/liwei/Development/workspace/intellij/java-advanced/java-jvm/build/classes/java/main/io/gourd/java/jvm/bytecode/NewDupCode.class
  Last modified 2020-1-15; size 913 bytes
  MD5 checksum a5ffd43b579d647f86b5dd35d64b3aec
  Compiled from "NewDupCode.java"
public class io.gourd.java.jvm.bytecode.NewDupCode
  minor version: 0
  major version: 55
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #8.#30         // java/lang/Object."<init>":()V
   #2 = Fieldref           #4.#31         // io/gourd/java/jvm/bytecode/NewDupCode.a:I
   #3 = Fieldref           #4.#32         // io/gourd/java/jvm/bytecode/NewDupCode.b:Ljava/lang/String;
   #4 = Class              #33            // io/gourd/java/jvm/bytecode/NewDupCode
   #5 = String             #34            // 2
   #6 = Methodref          #4.#35         // io/gourd/java/jvm/bytecode/NewDupCode."<init>":(ILjava/lang/String;)V
   #7 = Methodref          #8.#36         // java/lang/Object.hashCode:()I
   #8 = Class              #37            // java/lang/Object
   #9 = Utf8               a
  #10 = Utf8               I
  #11 = Utf8               b
  #12 = Utf8               Ljava/lang/String;
  #13 = Utf8               <init>
  #14 = Utf8               (ILjava/lang/String;)V
  #15 = Utf8               Code
  #16 = Utf8               LineNumberTable
  #17 = Utf8               LocalVariableTable
  #18 = Utf8               this
  #19 = Utf8               Lio/gourd/java/jvm/bytecode/NewDupCode;
  #20 = Utf8               newDupCode
  #21 = Utf8               ()Lio/gourd/java/jvm/bytecode/NewDupCode;
  #22 = Utf8               voidNewDupCode
  #23 = Utf8               ()V
  #24 = Utf8               voidUseNewDupCode
  #25 = Utf8               ()I
  #26 = Utf8               o
  #27 = Utf8               voidUseNonRefNewDupCode
  #28 = Utf8               SourceFile
  #29 = Utf8               NewDupCode.java
  #30 = NameAndType        #13:#23        // "<init>":()V
  #31 = NameAndType        #9:#10         // a:I
  #32 = NameAndType        #11:#12        // b:Ljava/lang/String;
  #33 = Utf8               io/gourd/java/jvm/bytecode/NewDupCode
  #34 = Utf8               2
  #35 = NameAndType        #13:#14        // "<init>":(ILjava/lang/String;)V
  #36 = NameAndType        #38:#25        // hashCode:()I
  #37 = Utf8               java/lang/Object
  #38 = Utf8               hashCode
{
  public io.gourd.java.jvm.bytecode.NewDupCode(int, java.lang.String);
    descriptor: (ILjava/lang/String;)V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=3
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iload_1
         6: putfield      #2                  // Field a:I
         9: aload_0
        10: aload_2
        11: putfield      #3                  // Field b:Ljava/lang/String;
        14: return
      LineNumberTable:
        line 13: 0
        line 14: 4
        line 15: 9
        line 16: 14
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      15     0  this   Lio/gourd/java/jvm/bytecode/NewDupCode;
            0      15     1     a   I
            0      15     2     b   Ljava/lang/String;

  public io.gourd.java.jvm.bytecode.NewDupCode newDupCode();
    descriptor: ()Lio/gourd/java/jvm/bytecode/NewDupCode;
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=1, args_size=1
         0: new           #4                  // class io/gourd/java/jvm/bytecode/NewDupCode
         3: dup
         4: iconst_1
         5: ldc           #5                  // String 2
         7: invokespecial #6                  // Method "<init>":(ILjava/lang/String;)V
        10: areturn
      LineNumberTable:
        line 19: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      11     0  this   Lio/gourd/java/jvm/bytecode/NewDupCode;

  public void voidNewDupCode();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=1, args_size=1
         0: new           #4                  // class io/gourd/java/jvm/bytecode/NewDupCode
         3: dup
         4: iconst_1
         5: ldc           #5                  // String 2
         7: invokespecial #6                  // Method "<init>":(ILjava/lang/String;)V
        10: pop
        11: return
      LineNumberTable:
        line 23: 0
        line 24: 11
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      12     0  this   Lio/gourd/java/jvm/bytecode/NewDupCode;

  public int voidUseNewDupCode();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=2, args_size=1
         0: new           #4                  // class io/gourd/java/jvm/bytecode/NewDupCode
         3: dup
         4: iconst_1
         5: ldc           #5                  // String 2
         7: invokespecial #6                  // Method "<init>":(ILjava/lang/String;)V
        10: astore_1
        11: aload_1
        12: getfield      #2                  // Field a:I
        15: aload_1
        16: invokevirtual #7                  // Method java/lang/Object.hashCode:()I
        19: iadd
        20: ireturn
      LineNumberTable:
        line 27: 0
        line 28: 11
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      21     0  this   Lio/gourd/java/jvm/bytecode/NewDupCode;
           11      10     1     o   Lio/gourd/java/jvm/bytecode/NewDupCode;

  public int voidUseNonRefNewDupCode();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=1, args_size=1
         0: new           #4                  // class io/gourd/java/jvm/bytecode/NewDupCode
         3: dup
         4: iconst_1
         5: ldc           #5                  // String 2
         7: invokespecial #6                  // Method "<init>":(ILjava/lang/String;)V
        10: getfield      #2                  // Field a:I
        13: ireturn
      LineNumberTable:
        line 32: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      14     0  this   Lio/gourd/java/jvm/bytecode/NewDupCode;
}
SourceFile: "NewDupCode.java"
 */
