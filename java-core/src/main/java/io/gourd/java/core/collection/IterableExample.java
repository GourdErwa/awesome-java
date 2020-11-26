package io.gourd.java.core.collection;

import java.util.List;

/**
 * @author Li.Wei by 2020/2/25
 */
public class IterableExample {

    /*
    Code:
      stack=1, locals=4, args_size=2
         0: aload_1
         1: invokeinterface #2,  1 //  InterfaceMethod java/lang/Iterable.iterator:()Ljava/util/Iterator;
         6: astore_2
         7: aload_2
         8: invokeinterface #3,  1 //  InterfaceMethod java/util/Iterator.hasNext:()Z
        13: ifeq          26
        16: aload_2
        17: invokeinterface #4,  1 //  InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
        22: astore_3
        23: goto          7
        26: return
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      27     0  this   Lio/gourd/java/core/collection/IterableExample;
            0      27     1 iterable   Ljava/lang/Iterable;
     */
    public void forEach(Iterable<Object> iterable) {
        for (Object o : iterable) {
            //  code ...
        }
    }

    public void randomAccess(List<Object> list) {
        for (Object o : list) {
            //  code ...
        }

        for (int i = 0; i < list.size(); i++) {
            //  code ...
        }
    }

}
