package org.bochenlong.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by bochenlong on 17-1-14.
 */
public class DisruptorQueue {
    protected Disruptor<QueueData> disruptor;
    protected Producer producer;
    
    protected DisruptorQueue(int bufferSize, EventConsumer... consumers) {
        QueueDataFactory factory = new QueueDataFactory();
        this.disruptor = new Disruptor<>(
                factory, bufferSize, Executors.defaultThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy()
        );
        this.disruptor.handleEventsWith(consumers);
        this.disruptor.start();
        this.producer = new Producer(disruptor.getRingBuffer());
    }
    
    protected DisruptorQueue(int bufferSize, WorkConsumer... consumers) {
        QueueDataFactory factory = new QueueDataFactory();
        this.disruptor = new Disruptor<>(
                factory, bufferSize, Executors.defaultThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy()
        );
        this.disruptor.handleEventsWithWorkerPool(consumers);
        this.disruptor.start();
        this.producer = new Producer(disruptor.getRingBuffer());
    }
    
    protected DisruptorQueue(int bufferSize, ThreadFactory threadFactory, ProducerType producerType, WaitStrategy waitStrategy, EventConsumer... consumers) {
        QueueDataFactory factory = new QueueDataFactory();
        this.disruptor = new Disruptor<>(
                factory, bufferSize, threadFactory, producerType, waitStrategy
        );
        this.disruptor.handleEventsWith(consumers);
        this.disruptor.start();
        this.producer = new Producer(disruptor.getRingBuffer());
    }
    
    protected DisruptorQueue(int bufferSize, ThreadFactory threadFactory, ProducerType producerType, WaitStrategy waitStrategy, WorkConsumer... consumers) {
        QueueDataFactory factory = new QueueDataFactory();
        this.disruptor = new Disruptor<>(
                factory, bufferSize, threadFactory, producerType, waitStrategy
        );
        this.disruptor.handleEventsWithWorkerPool(consumers);
        this.disruptor.start();
        this.producer = new Producer(disruptor.getRingBuffer());
    }
}
