package org.bochenlong.collector;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by bcl on 2016/8/19.

 public interface Collector<T, A, R> {// T是要收集的项目的泛型A是累加器的类型，累加器是在收集过程中用于累积部分结果的对象，R是收集操作得到的对象的类型
 Supplier<A> supplier();// 建立新的结果容器

 BiConsumer<A, T> accumulator();// 将元素添加到容器

 Function<A, R> finisher();// 对结果应用最终转换

 BinaryOperator<A> combiner();// combiner方法会返回一个供归约操作使用的函数，它定义了对流的各个字部分进行并行处理时，各个子部分归约所得的累加器要如何合并

 Set<Characteristics> characteristics();// 返回一个不可变的集合，定义了收集器的行为-尤其是关于流是否可以并行归约
 // 1，UNORDERED 归约结果不受流中项目的遍历和累积顺序的影响
 // 2，CONCURRENT accumulator函数可以从多个线程同时调用，且该收集器可以并行归约流。如果收集器没有标为UNORDERED，那它们尽在用于无序数据源时才可以并行归约
 // 3，IDENTITY_FINISH 这表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下，累加器对象将会直接用作归约过程的最终结果。

 */
public class ToMyCollector implements Collector<Dish, Container, Container> {
    @Override
    public Supplier<Container> supplier() {
        return Container::new;
    }

    @Override
    public BiConsumer<Container, Dish> accumulator() {
        return (c, t) -> {
            if (t.isVegetarian()) c.addOk(t);
            c.addNo(t);
        };
    }

    @Override
    public BinaryOperator<Container> combiner() {
        return (container1, container2) -> {
            container1.getNo().addAll(container2.getNo());
            container1.getOk().addAll(container2.getOk());
            return container1;
        };
    }

    @Override
    public Function<Container, Container> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(
                Characteristics.IDENTITY_FINISH, Characteristics.CONCURRENT
        ));
    }
}
