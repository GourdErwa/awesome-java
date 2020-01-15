package io.gourd.java.jvm.bytecode;

import lombok.Getter;

/**
 * 字节码分析
 *
 * @author Li.Wei by 2020/1/15
 */
public class MemoryAllocation {

    /* 类静态成员变量 */
    private static final int DEFAULT_A = 2147483647;
    private static final String DEFAULT_B = "static-variable";
    private static final byte[] DEFAULT_BYTE = new byte[100 * 1024];
    private static final MemoryAllocation MEMORY_ALLOCATION = new MemoryAllocation();

    /* 类非静态成员变量 */
    private int a;
    private String b;
    private byte[] bytes;

    public MemoryAllocation() {
    }

    public MemoryAllocation(int a, String b, byte[] bytes) {
        this.a = a;
        this.b = b;
        this.bytes = bytes;
    }

    /* 类静态成员方法 */
    public static MemoryAllocation defaultNoneInstance() {
        return MEMORY_ALLOCATION;
    }

    /* 类静态成员方法 */
    public static MemoryAllocation defaultInstance() {
        return new MemoryAllocation(DEFAULT_A, DEFAULT_B, DEFAULT_BYTE);
    }

//    // 无法逃逸
//    public MemoryAllocation noEscape(MemoryAllocation allocation) {
//        allocation.a = ++allocation.a;
//        return allocation;
//    }
//
//    // 逃逸 ，方法内创建了对象，但是对象没有离开方法，可直接优化为 b + 1
//    public String escape() {
//        return defaultInstance().b + 1;
//    }
}
/*
Classfile /Users/liwei/Development/workspace/intellij/java-advanced/java-jvm/build/classes/java/main/io/gourd/java/jvm/bytecode/MemoryAllocation.class
  Last modified 2020-1-15; size 2010 bytes
  MD5 checksum 4ff0b35b878b328ce7f5a0a73ede84b3
  Compiled from "MemoryAllocation.java"
public class io.gourd.java.jvm.bytecode.MemoryAllocation
  minor version: 0
  major version: 55
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #15.#51        // java/lang/Object."<init>":()V
   #2 = Fieldref           #6.#52         // io/gourd/java/jvm/bytecode/MemoryAllocation.a:I
   #3 = Fieldref           #6.#53         // io/gourd/java/jvm/bytecode/MemoryAllocation.b:Ljava/lang/String;
   #4 = Fieldref           #6.#54         // io/gourd/java/jvm/bytecode/MemoryAllocation.bytes:[B
   #5 = Fieldref           #6.#55         // io/gourd/java/jvm/bytecode/MemoryAllocation.MEMORY_ALLOCATION:Lio/gourd/java/jvm/bytecode/MemoryAllocation;
   #6 = Class              #56            // io/gourd/java/jvm/bytecode/MemoryAllocation
   #7 = Integer            2147483647
   #8 = String             #57            // static-variable
   #9 = Fieldref           #6.#58         // io/gourd/java/jvm/bytecode/MemoryAllocation.DEFAULT_BYTE:[B
  #10 = Methodref          #6.#59         // io/gourd/java/jvm/bytecode/MemoryAllocation."<init>":(ILjava/lang/String;[B)V
  #11 = Methodref          #6.#60         // io/gourd/java/jvm/bytecode/MemoryAllocation.defaultInstance:()Lio/gourd/java/jvm/bytecode/MemoryAllocation;
  #12 = InvokeDynamic      #0:#64         // #0:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
  #13 = Integer            102400
  #14 = Methodref          #6.#51         // io/gourd/java/jvm/bytecode/MemoryAllocation."<init>":()V
  #15 = Class              #65            // java/lang/Object
  #16 = Utf8               DEFAULT_A
  #17 = Utf8               I
  #18 = Utf8               ConstantValue
  #19 = Utf8               DEFAULT_B
  #20 = Utf8               Ljava/lang/String;
  #21 = Utf8               DEFAULT_BYTE
  #22 = Utf8               [B
  #23 = Utf8               MEMORY_ALLOCATION
  #24 = Utf8               Lio/gourd/java/jvm/bytecode/MemoryAllocation;
  #25 = Utf8               a
  #26 = Utf8               b
  #27 = Utf8               bytes
  #28 = Utf8               <init>
  #29 = Utf8               ()V
  #30 = Utf8               Code
  #31 = Utf8               LineNumberTable
  #32 = Utf8               LocalVariableTable
  #33 = Utf8               this
  #34 = Utf8               (ILjava/lang/String;[B)V
  #35 = Utf8               defaultNoneInstance
  #36 = Utf8               ()Lio/gourd/java/jvm/bytecode/MemoryAllocation;
  #37 = Utf8               defaultInstance
  #38 = Utf8               noEscape
  #39 = Utf8               (Lio/gourd/java/jvm/bytecode/MemoryAllocation;)Lio/gourd/java/jvm/bytecode/MemoryAllocation;
  #40 = Utf8               allocation
  #41 = Utf8               escape
  #42 = Utf8               ()Ljava/lang/String;
  #43 = Utf8               getA
  #44 = Utf8               ()I
  #45 = Utf8               getB
  #46 = Utf8               getBytes
  #47 = Utf8               ()[B
  #48 = Utf8               <clinit>
  #49 = Utf8               SourceFile
  #50 = Utf8               MemoryAllocation.java
  #51 = NameAndType        #28:#29        // "<init>":()V
  #52 = NameAndType        #25:#17        // a:I
  #53 = NameAndType        #26:#20        // b:Ljava/lang/String;
  #54 = NameAndType        #27:#22        // bytes:[B
  #55 = NameAndType        #23:#24        // MEMORY_ALLOCATION:Lio/gourd/java/jvm/bytecode/MemoryAllocation;
  #56 = Utf8               io/gourd/java/jvm/bytecode/MemoryAllocation
  #57 = Utf8               static-variable
  #58 = NameAndType        #21:#22        // DEFAULT_BYTE:[B
  #59 = NameAndType        #28:#34        // "<init>":(ILjava/lang/String;[B)V
  #60 = NameAndType        #37:#36        // defaultInstance:()Lio/gourd/java/jvm/bytecode/MemoryAllocation;
  #61 = Utf8               BootstrapMethods
  #62 = MethodHandle       #6:#66         // invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #63 = String             #67            // 1
  #64 = NameAndType        #68:#69        // makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
  #65 = Utf8               java/lang/Object
  #66 = Methodref          #70.#71        // java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #67 = Utf8               1
  #68 = Utf8               makeConcatWithConstants
  #69 = Utf8               (Ljava/lang/String;)Ljava/lang/String;
  #70 = Class              #72            // java/lang/invoke/StringConcatFactory
  #71 = NameAndType        #68:#76        // makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #72 = Utf8               java/lang/invoke/StringConcatFactory
  #73 = Class              #78            // java/lang/invoke/MethodHandles$Lookup
  #74 = Utf8               Lookup
  #75 = Utf8               InnerClasses
  #76 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
  #77 = Class              #79            // java/lang/invoke/MethodHandles
  #78 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #79 = Utf8               java/lang/invoke/MethodHandles
{
  public io.gourd.java.jvm.bytecode.MemoryAllocation();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 25: 0
        line 26: 4
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lio/gourd/java/jvm/bytecode/MemoryAllocation;

  public io.gourd.java.jvm.bytecode.MemoryAllocation(int, java.lang.String, byte[]);
    descriptor: (ILjava/lang/String;[B)V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=4, args_size=4
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iload_1
         6: putfield      #2                  // Field a:I
         9: aload_0
        10: aload_2
        11: putfield      #3                  // Field b:Ljava/lang/String;
        14: aload_0
        15: aload_3
        16: putfield      #4                  // Field bytes:[B
        19: return
      LineNumberTable:
        line 28: 0
        line 29: 4
        line 30: 9
        line 31: 14
        line 32: 19
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      20     0  this   Lio/gourd/java/jvm/bytecode/MemoryAllocation;
            0      20     1     a   I
            0      20     2     b   Ljava/lang/String;
            0      20     3 bytes   [B

  public static io.gourd.java.jvm.bytecode.MemoryAllocation defaultNoneInstance();
    descriptor: ()Lio/gourd/java/jvm/bytecode/MemoryAllocation;
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=1, locals=0, args_size=0
         0: getstatic     #5                  // Field MEMORY_ALLOCATION:Lio/gourd/java/jvm/bytecode/MemoryAllocation;
         3: areturn
      LineNumberTable:
        line 35: 0

  public static io.gourd.java.jvm.bytecode.MemoryAllocation defaultInstance();
    descriptor: ()Lio/gourd/java/jvm/bytecode/MemoryAllocation;
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=5, locals=0, args_size=0
         0: new           #6                  // class io/gourd/java/jvm/bytecode/MemoryAllocation
         3: dup
         4: ldc           #7                  // int 2147483647
         6: ldc           #8                  // String static-variable
         8: getstatic     #9                  // Field DEFAULT_BYTE:[B
        11: invokespecial #10                 // Method "<init>":(ILjava/lang/String;[B)V
        14: areturn
      LineNumberTable:
        line 39: 0

  public io.gourd.java.jvm.bytecode.MemoryAllocation noEscape(io.gourd.java.jvm.bytecode.MemoryAllocation);
    descriptor: (Lio/gourd/java/jvm/bytecode/MemoryAllocation;)Lio/gourd/java/jvm/bytecode/MemoryAllocation;
    flags: ACC_PUBLIC
    Code:
      stack=4, locals=2, args_size=2
         0: aload_1
         1: aload_1
         2: dup
         3: getfield      #2                  // Field a:I
         6: iconst_1
         7: iadd
         8: dup_x1
         9: putfield      #2                  // Field a:I
        12: putfield      #2                  // Field a:I
        15: aload_1
        16: areturn
      LineNumberTable:
        line 44: 0
        line 45: 15
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      17     0  this   Lio/gourd/java/jvm/bytecode/MemoryAllocation;
            0      17     1 allocation   Lio/gourd/java/jvm/bytecode/MemoryAllocation;

  public java.lang.String escape();
    descriptor: ()Ljava/lang/String;
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: invokestatic  #11                 // Method defaultInstance:()Lio/gourd/java/jvm/bytecode/MemoryAllocation;
         3: getfield      #3                  // Field b:Ljava/lang/String;
         6: invokedynamic #12,  0             // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
        11: areturn
      LineNumberTable:
        line 50: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      12     0  this   Lio/gourd/java/jvm/bytecode/MemoryAllocation;

  public int getA();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: getfield      #2                  // Field a:I
         4: ireturn
      LineNumberTable:
        line 21: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lio/gourd/java/jvm/bytecode/MemoryAllocation;

  public java.lang.String getB();
    descriptor: ()Ljava/lang/String;
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: getfield      #3                  // Field b:Ljava/lang/String;
         4: areturn
      LineNumberTable:
        line 22: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lio/gourd/java/jvm/bytecode/MemoryAllocation;

  public byte[] getBytes();
    descriptor: ()[B
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: getfield      #4                  // Field bytes:[B
         4: areturn
      LineNumberTable:
        line 23: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lio/gourd/java/jvm/bytecode/MemoryAllocation;

  static {};
    descriptor: ()V
    flags: ACC_STATIC
    Code:
      stack=2, locals=0, args_size=0
         0: ldc           #13                 // int 102400
         2: newarray       byte
         4: putstatic     #9                  // Field DEFAULT_BYTE:[B
         7: new           #6                  // class io/gourd/java/jvm/bytecode/MemoryAllocation
        10: dup
        11: invokespecial #14                 // Method "<init>":()V
        14: putstatic     #5                  // Field MEMORY_ALLOCATION:Lio/gourd/java/jvm/bytecode/MemoryAllocation;
        17: return
      LineNumberTable:
        line 17: 0
        line 19: 7
}
SourceFile: "MemoryAllocation.java"
InnerClasses:
     public static final #74= #73 of #77; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #62 invokestatic java/lang/invoke/StringConcatFactory.makeConcatWithConstants:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #63 1

*/
