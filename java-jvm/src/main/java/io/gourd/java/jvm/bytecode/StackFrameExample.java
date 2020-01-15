package io.gourd.java.jvm.bytecode;

/**
 * 字节码分析
 *
 * @author Li.Wei by 2020/1/14
 */
public class StackFrameExample {

    private int threshold;

    public StackFrameExample(int threshold) {
        this.threshold = threshold;
    }

    public boolean calculation(int a, int b) {
        int c = 300;
        int sum = a + b;
        if (sum == 0) {
            throw new IllegalArgumentException("zero ...");
        }
        return (c / sum) > threshold;
    }

}
/*
javap -c -p -v StackFrameExample.class

public class io.gourd.java.jvm.bytecode.StackFrameExample
  minor version: 0
  major version: 55
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool: => 常量池
   #1 = Methodref          #7.#26         // java/lang/Object."<init>":()V
   #2 = Fieldref           #6.#27         // io/gourd/java/jvm/bytecode/StackFrameExample.threshold:I
   #3 = Class              #28            // java/lang/IllegalArgumentException
   #4 = String             #29            // zero ...
   #5 = Methodref          #3.#30         // java/lang/IllegalArgumentException."<init>":(Ljava/lang/String;)V
   #6 = Class              #31            // io/gourd/java/jvm/bytecode/StackFrameExample
   #7 = Class              #32            // java/lang/Object
   #8 = Utf8               threshold
   #9 = Utf8               I
  #10 = Utf8               <init>
  #11 = Utf8               (I)V
  #12 = Utf8               Code
  #13 = Utf8               LineNumberTable
  #14 = Utf8               LocalVariableTable
  #15 = Utf8               this
  #16 = Utf8               Lio/gourd/java/jvm/bytecode/StackFrameExample;
  #17 = Utf8               calculation
  #18 = Utf8               (II)Z
  #19 = Utf8               a
  #20 = Utf8               b
  #21 = Utf8               c
  #22 = Utf8               sum
  #23 = Utf8               StackMapTable
  #24 = Utf8               SourceFile
  #25 = Utf8               StackFrameExample.java
  #26 = NameAndType        #10:#33        // "<init>":()V
  #27 = NameAndType        #8:#9          // threshold:I
  #28 = Utf8               java/lang/IllegalArgumentException
  #29 = Utf8               zero ...
  #30 = NameAndType        #10:#34        // "<init>":(Ljava/lang/String;)V
  #31 = Utf8               io/gourd/java/jvm/bytecode/StackFrameExample
  #32 = Utf8               java/lang/Object
  #33 = Utf8               ()V
  #34 = Utf8               (Ljava/lang/String;)V
{
  private int threshold;
    descriptor: I
    flags: ACC_PRIVATE

  public io.gourd.java.jvm.bytecode.StackFrameExample(int);
    descriptor: (I)V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=2, args_size=2
       0: aload_0          =>this 引用本地变量推送至栈顶
       1: invokespecial #1 // Method java/lang/Object."<init>":()V 调用父类构造函数 this.super()
       4: aload_0          =>this 引用本地变量推送至栈顶
       5: iload_1          => 构造函数的 threshold 本地变量推送至栈顶
       6: putfield      #2  // Field threshold:I  为指定类的实例域 threshold 赋值
       9: return           => 从当前方法返回 void
      LineNumberTable:
        line 12: 0
        line 13: 4
        line 14: 9
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      10     0  this   Lio/gourd/java/jvm/bytecode/StackFrameExample;
            0      10     1 threshold   I

  public boolean calculation(int, int);
    descriptor: (II)Z
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=5, args_size=3
       0: sipush        300 => 将一个短整型常量 300 推送至栈顶
       3: istore_3          => 将栈顶 c = 300 数值存入第四个本地变量（this,a,b）已经占了 3 个槽位
       4: iload_1           => 将第二个 int 型本地变量 a 推送至栈顶
       5: iload_2           => 将第二个 int 型本地变量 b 推送至栈顶
       6: iadd              => 将栈顶两 int 型数值 a、b 相加并将结果压入栈顶
       7: istore        4   => 将栈顶 int 型数值存入指定本地变量 sum （第五个本地变量，槽位为 4）
       9: iload         4   => 将指定的 int 型本地变量（槽位为 4 的 sum）推送至栈顶
      11: ifne          24  => 当栈顶 int 型数值 sum 不等于 0 时跳转到[24] 字节码
      14: new           #3  // class java/lang/IllegalArgumentException 开始创建异常对象
      17: dup               => 复制栈顶数值并将复制值压入栈顶
      18: ldc           #4  // String zero ... 将 String 型常量值（zero ...）从常量池中推送至栈顶
      20: invokespecial #5  // Method java/lang/IllegalArgumentException."<init>":(Ljava/lang/String;)V 调用超类构建方法, 实例初始化方法
      23: athrow            => 将栈顶的异常抛出
      24: iload_3           => 将第四个 int 型本地变量 c（槽位为 3）推送至栈顶
      25: iload         4   => 将第五个 int 型本地变量 sum（槽位为 4）推送至栈顶
      27: idiv              => 将栈顶两 int 型数值相除（c/sum）并将结果压入栈顶
      28: aload_0           =>this 引用本地变量推送至栈顶
      29: getfield      #2   // Field threshold:I  获取类的实例域 threshold
      32: if_icmple     39  => 比较栈顶两 int 型数值大小（(c / sum) > threshold）, 当结果小于等于 0 时跳转[39] 字节码
      35: iconst_1          => 将 int 型 1 推送至栈顶（true）
      36: goto          40  => 无条件跳转
      39: iconst_0          => 将 int 型 0 推送至栈顶（false）
      40: ireturn           => 从当前方法返回 int（因为为了减少指令集，使用 int 替代了 byte、short、char 和 boolean 的算数指令）
      LineNumberTable:=> 表存放方法的行号信息
        line 17: 0
        line 18: 4
        line 19: 9
        line 20: 14
        line 22: 24
      LocalVariableTable: => 存放方法的局部变量信息，与上面指令操作一一对应，重点是第一个槽位是 this 引用
        Start  Length  Slot  Name   Signature
            0      41     0  this   Lio/gourd/java/jvm/bytecode/StackFrameExample;
            0      41     1     a   I
            0      41     2     b   I
            4      37     3     c   I
            9      32     4   sum   I
      StackMapTable: number_of_entries = 3 => 栈图 https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.7.4
        frame_type = 253
          offset_delta = 24
              locals = [ int, int ]
              frame_type = 14
              frame_type = 64
              stack = [ int ]
}
 */
