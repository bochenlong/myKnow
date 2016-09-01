package org.bochenlong.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by bcl on 2016/9/1.
 * <p>
 * public interface Spliterator<T> {
 * boolean tryAdvance(Consumer<? super T> action);
 * // 按顺序一个个使用Spliterator中的元素，且如果还有其他元素要遍历就返回true
 * Spliterator<T> trySplit();
 * // 拆分元素到另外一个Spliterator，方便并行处理
 * long estimateSize();
 * // 返回剩余元素个数
 * int characteristics();
 * // spliterator的特性值
 * // ORDER 元素有既定顺序
 * // DISTINCT 对于任意一对遍历过的元素x、y，x.equals(y)返回false
 * // SORTED 遍历元素按照一个预定义的顺序排序
 * // SIZED spliterator由一个已知大小的源建立
 * // NOTNULL 保证遍历的元素不为null
 * // IMMUTABLE 数据源不能修改，遍历时不能添加、删除和修改
 * // CONCURRENT 数据源可以被其他线程同时修改而无需同步
 * // SUBSIZED 子spliterator是SIZED的
 * }
 */
public class MySpliterator implements Spliterator<Character> {
    // 统计字符串中有多少单词
    private final String string;
    private int currentChar = 0;

    public MySpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));// 消费一个字符
        return currentChar < string.length(); // 返回是否还有字符要处理
    }

    @Override
    public Spliterator<Character> trySplit() {// 拆分数据源，直至返回null
        int currentSize = string.length() - currentChar;

        if (currentSize < 10) {// 如果剩余10个元素则不分解，返回null
            return null;
        }

        // 取剩余字符串的一半
        for (int i = currentSize / 2 + currentChar; i < string.length(); i++) {
            if (Character.isWhitespace(string.charAt(i))) {// 如果是空符号，则生成下一个spliteraotr
                Spliterator<Character> s = new MySpliterator(string.substring(currentChar, i));
                currentChar = i;// 记录分割点
                return s;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    public static void main(String[] args) {
        String string = "wo shi yi ming xiao xue sheng " +
                "wo jiao xiao ming " +
                "jin tian kai xue dan shi wo d " +
                "han jia zuo ye bei qiang le ";
        Stream<Character> stream = IntStream.range(0, string.length())
                .mapToObj(string::charAt);
        Spliterator<Character> spliterator = new MySpliterator(string);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
        WorkCounter w = stream1.reduce(new WorkCounter(0, true), WorkCounter::accumulate, WorkCounter::combine);
        System.out.println(w.getCounter());
    }
}
