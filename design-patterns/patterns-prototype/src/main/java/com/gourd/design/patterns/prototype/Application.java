/*
 *
 * The MIT License Copyright © 2018-2018 GourdErwa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.gourd.design.patterns.prototype;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Application.
 *
 * @author wei.Li
 */
public final class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private Application() {
        // not called
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws CloneNotSupportedException the clone not supported exception
     */
    public static void main(String[] args) throws CloneNotSupportedException {

        final Setting fileSetting = FileSetting.builder().path("/data/tmp.txt").build();
        final Setting jdbcSetting = JdbcSetting.builder().url("/ip:port/url").userName("demo").passWd("demo").build();

        LOGGER.info("原始对象:fileSetting hashcode {} , {}", fileSetting.hashCode(), fileSetting);
        LOGGER.info("原始对象:jdbcSetting hashcode {} , {}", jdbcSetting.hashCode(), jdbcSetting);

        final Object cloneFileSetting = fileSetting.clone();
        LOGGER.info("clone对象:fileSetting hashcode {} , {}", cloneFileSetting.hashCode(), cloneFileSetting);

        final Object cloneJdbcSetting = jdbcSetting.clone();
        LOGGER.info("clone对象:jdbcSetting hashcode {} , {}", cloneJdbcSetting.hashCode(), cloneJdbcSetting);

    }

}
