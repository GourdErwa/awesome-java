package io.gourd.java.concurrency.atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Li.Wei by 2019/12/22
 */
@Slf4j
public class AtomicRefExample {

    public static void main(String[] args) {
        final AtomicReference<User> reference = new AtomicReference<>(new User("liw", 28));
        final User user = reference.getAndSet(new User("Li.W", 28));
        log.info("user.getName() = {}", user.getName()); // liw
        log.info("reference.get().getName() = {}", reference.get().getName()); // Li.W


        final AtomicMarkableReference<User> markableReference =
            new AtomicMarkableReference<>(user, false);
        markableReference.compareAndSet(user, user, false, true);
        log.info("markableReference {},{}", markableReference.getReference(), markableReference.isMarked()); // true
    }

    @AllArgsConstructor
    @Getter
    private static class User {
        private String name;
        private int age;
    }
}
