package io.gourd.java.jvm.oom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Li.Wei by 2020/1/13
 */
@Slf4j
public class OOMExample {

    final Map<OomKey, Integer> map = new HashMap<>();

    public void invoke() {
        for (int i = 0; i < 10000000; i++) {
            map.put(new OomKey(i + "", i), i);
        }
        log.info("map size {}", map.size());
    }

    @AllArgsConstructor
    @Getter
    static class OomKey {
        private String name;
        private int age;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OomKey oomKey = (OomKey) o;
            return age == oomKey.age &&
                Objects.equals(name, oomKey.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash((age % 4) * 60);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new OOMExample().invoke();

        Thread.sleep(500 * 1000); // 等待 dump
    }
}
