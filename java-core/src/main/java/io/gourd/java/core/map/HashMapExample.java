package io.gourd.java.core.map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟 HashMap hash 冲突后 HashMap 内部 table 数据结构及其他变量实时变化情况 .
 * - table 的扩容情况
 * - table 中节点，由链表转换为红黑树的变换条件
 * - loadFactor、size、threshold 实时变化情况
 *
 * <p>
 * 具体请参考 main 函数，执行打印结果为主
 *
 * @author Li.Wei by 2020/1/1
 */
@Slf4j
public class HashMapExample {

    /**
     * The Map. 默认容量为 16 ，负载因子为 1 进行测试。
     * 我们可以根据实际情况修改初始化参数和 {@link ConflictingHash#hashCode()} 方法进行碰撞测试
     */
    public final Map<ConflictingHash, Integer> map = new HashMap<>(16, 1f);

    /* ---------------- 反射 HashMap 内部私有变量字段 -------------- */
    private final Class<? extends Map> mapClass = map.getClass();
    private final Field tableField = mapClass.getDeclaredField("table");
    private final Field loadFactorField = mapClass.getDeclaredField("loadFactor");
    private final Field thresholdField = mapClass.getDeclaredField("threshold");
    private final Field sizeField = mapClass.getDeclaredField("size");

    {
        tableField.setAccessible(true);
        loadFactorField.setAccessible(true);
        thresholdField.setAccessible(true);
        sizeField.setAccessible(true);
    }

    private HashMapExample()
        throws ReflectiveOperationException {
        log.info("loadFactor = {}", loadFactorField.get(map));
    }


    /**
     * 日志形式输出 map 中的 table、threshold、size 当前数值情况.
     *
     * @return the hash map example
     * @throws IllegalAccessException the illegal access exception
     */
    public HashMapExample logFieldValue() throws IllegalAccessException {
        log.info("---------------------------------");
        Object arg = tableField.get(map);
        Object[] tableArray = arg == null ? new Object[]{} : ((Object[]) arg);
        log.info("table      = {}", tableArrayToString(tableArray));

        log.info("threshold  = {}", thresholdField.get(map));
        log.info("size       = {}", sizeField.get(map));
        return this;
    }

    private HashMapExample put(ConflictingHash k, Integer v) {
        map.put(k, v);
        return this;
    }

    /**
     * 将数组打印，方便查看数据对象的对象名称.
     *
     * @param a the a
     * @return the string
     */
    public static String tableArrayToString(Object[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "[]";

        final StringBuilder b = new StringBuilder("length=" + a.length);
        for (int i = 0; i < a.length; i++) {
            final Object o = a[i];
            if (o != null) {
                b.append("，[").append(i).append("]=")
                    .append(o.getClass().getSimpleName())
                    .append(" ");
            }
        }

        return b.toString();
    }


    /**
     * 模拟一个 hash 碰撞的对象
     * 重写 hashCode 为取模运算，取模的数值决定碰撞的几率.
     * 重写 equals 保证对象信息 hashCode 相等时，equals 比较不一定相等
     */
    @AllArgsConstructor
    @Getter
    static class ConflictingHash {

        private final int i;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ConflictingHash that = (ConflictingHash) o;
            return i == that.i;
        }

        @Override
        public int hashCode() {
            return i % 3;
        }
    }

    public static void main(String[] args)
        throws ReflectiveOperationException {

        final HashMapExample example = new HashMapExample()
            .logFieldValue();

        for (int i = 0; i < 30; i++) {
            example.put(new ConflictingHash(i), i).logFieldValue();
        }
    }
}
