package io.gourd.java.core.collection;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Li.Wei by 2020/2/25
 */
public class DequeExample {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>(10);
        for (int i = 0; i < 20; i++) {
            deque.add(i);
        }
        System.out.println(deque);

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        for (int i = 0; i < 20; i++) {
            blockingQueue.add(i);
        }
        System.out.println(blockingQueue);
    }
}
