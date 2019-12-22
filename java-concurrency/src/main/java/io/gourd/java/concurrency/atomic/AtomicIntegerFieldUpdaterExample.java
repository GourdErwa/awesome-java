package io.gourd.java.concurrency.atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Li.Wei by 2019/12/22
 */
@Slf4j
public class AtomicIntegerFieldUpdaterExample {

    public static void main(String[] args) {
        final AtomicIntegerFieldUpdater<User> updater =
            AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        final User user = new User("Li", 28);
        updater.set(user, 30);
        int increment = updater.incrementAndGet(user);

        log.info("increment return = {}", increment); // 31
    }
    @AllArgsConstructor
    @Getter
    private static class User {
        private String name;
        public volatile int age;
    }
}

