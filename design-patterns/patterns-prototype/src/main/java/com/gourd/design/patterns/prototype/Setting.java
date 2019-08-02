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

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * The interface Setting.
 *
 * @author wei.Li by 2018-11-21
 */
interface Setting extends Prototype {

}

/**
 * The type File setting.
 */
@Builder
@Getter
@ToString
class FileSetting implements Setting {

    private String path;

    @Override
    public FileSetting clone() throws CloneNotSupportedException {
        return (FileSetting) super.clone();
    }
}

/**
 * The type Jdbc setting.
 */
@Builder
@Getter
@ToString
class JdbcSetting implements Setting {

    private String url;
    private String userName;
    private String passWd;

    @Override
    public JdbcSetting clone() throws CloneNotSupportedException {
        return (JdbcSetting) super.clone();
    }
}
