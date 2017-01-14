package org.bochenlong.disruptor;

import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by bochenlong on 16-12-14.
 */
public class DisruptorHelper {
    private static ConcurrentHashMap<String, DisruptorQueue> queues = new ConcurrentHashMap();

    public final static int DEFAULT_BUFFER_SIZE = 1024;


    public static DisruptorQueue makeDefaultEventQueue(String key, EventConsumer... consumers) {
        return queues.computeIfAbsent(key, a -> new DisruptorQueue(DEFAULT_BUFFER_SIZE, consumers));
    }

    public static DisruptorQueue makeDefaultWorkQueue(String key, WorkConsumer consumers) {
        return queues.computeIfAbsent(key, a -> new DisruptorQueue(DEFAULT_BUFFER_SIZE, consumers));
    }

    public static DisruptorQueue makeEventQueue(String key, int bufferSize, EventConsumer... consumers) {
        return queues.computeIfAbsent(key, a -> new DisruptorQueue(bufferSize, consumers));
    }

    public static DisruptorQueue makeWorkQueue(String key, int bufferSize, WorkConsumer consumers) {
        return queues.computeIfAbsent(key, a -> new DisruptorQueue(bufferSize, consumers));
    }

    public static DisruptorQueue makeEventQueue(String key, int bufferSize, ThreadFactory threadFactory, ProducerType producerType, WaitStrategy waitStrategy, EventConsumer... consumers) {
        return queues.computeIfAbsent(key, a -> new DisruptorQueue(bufferSize, threadFactory, producerType, waitStrategy, consumers));
    }

    public static DisruptorQueue makeWorkQueue(String key, int bufferSize, ThreadFactory threadFactory, ProducerType producerType, WaitStrategy waitStrategy, WorkConsumer... consumers) {
        return queues.computeIfAbsent(key, a -> new DisruptorQueue(bufferSize, threadFactory, producerType, waitStrategy, consumers));
    }

    public static void pushData(String key, Object o) {
        queues.get(key).producer.pushData(o);
    }

    public static void shutDownQueue(String key) {
        queues.get(key).disruptor.shutdown();
    }

    public static void shutDownQueue(String key, long t, TimeUnit unit) throws TimeoutException {
        queues.get(key).disruptor.shutdown(t, unit);
    }

}
