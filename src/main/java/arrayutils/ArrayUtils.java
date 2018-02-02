package arrayutils;

import java.util.Arrays;

public class ArrayUtils {

    /**
     * 将数据扩容到指定长度
     *
     * @param elementData 目标数组
     * @param length      扩容长度
     * @param <T>         占位符，表明返回结果为泛型
     * @return 扩容后数组
     */
    public static <T> T[] expandCapacity(T[] elementData, int length) {
        length = length < 0 ? elementData.length : elementData.length + length;
        return Arrays.copyOf(elementData, length);
    }

    /**
     * 默认扩容1.5倍
     *
     * @param elementData 目标数组
     * @param <T>         占位符
     * @return 扩容后数组
     */
    public static <T> T[] expandCapacity(T[] elementData) {
        int length = elementData.length + (elementData.length >> 1);
        return Arrays.copyOf(elementData, length);
    }

    /**
     * 扩容指定倍数
     *
     * @param elementData 目标数组
     * @param multi       指定备注
     * @param <T>         占位符
     * @return 扩容后数组
     */
    public static <T> T[] expandMultiCapacity(T[] elementData, int multi) {
        int length = multi <= 0 ? 1 : elementData.length * multi;
        return Arrays.copyOf(elementData, length);
    }
}
