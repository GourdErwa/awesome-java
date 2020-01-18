package io.gourd.java.jvm.oom;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Oom {

    public static void main(String[] args) {

        final Map<OomKey, OomKey> map = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            map.put(new OomKey(i + "", i), new OomKey(i + "", i));
        }

        try {
            Thread.sleep(2000200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("{}", map);
    }

    @AllArgsConstructor
    static
    class OomKey {
        private String name;
        private int age;
    }
}
