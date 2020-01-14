package io.gourd.java.jvm.oom;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 栈溢出
 * <p>
 * VM Args: -Xss128k
 * <p>
 * 线程请求的栈深度大于虚拟机所允许的最大深度，将抛出 StackOverflowError 异常
 * java.lang.StackOverflowError
 *
 * @author Li.Wei by 2020/1/5
 */
@Slf4j
@Data
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF sof = new JavaVMStackSOF();
        try {
            sof.stackLeak();
        } catch (Throwable e) {
            // error ,stackLength=[2239],msg={}
            // java.lang.StackOverflowError: null
            //	at io.gourd.java.jvm.oom.JavaVMStackSOF.stackLeak(JavaVMStackSOF.java:19)
            log.error("error ,stackLength=[{}],msg={}", sof.stackLength, e);
        }
    }
}

/**
 * 虚拟机的栈内存允许动态扩展，当扩展栈容量无法申请到足够的内存时，将抛出 OutOfMemoryError 异常
 * <p>
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create native thread
 * <p>
 * 不推荐执行，会导致系统假死
 */
class JavaVMStackOOM {

    private void whileRun() {
        while (true) {

        }
    }

    private void whileRunThread() {
        while (true) {
            new Thread(this::whileRun).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.whileRunThread();
    }
}
