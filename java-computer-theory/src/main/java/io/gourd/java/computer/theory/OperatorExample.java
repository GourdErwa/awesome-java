package io.gourd.java.computer.theory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Li.Wei by 2019/12/29
 */
@Slf4j
public class OperatorExample {

    public static void main(String[] args) {
        log.info("x = {}", 0b000100111);
        log.info("Integer.toBinaryString = {}", Integer.toBinaryString(-2));

        log.info("x = {}", Integer.toBinaryString(0b100111 << 2));
        log.info("x = {}", 0b0011);
        log.info("x = {}", Integer.MAX_VALUE);
    }
}
