package org.bochenlong.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.bochenlong.disruptor.base.*;

import java.util.concurrent.Executors;

/**
 * Created by bochenlong on 16-12-14.
 */
public class Test {
    public static void main(String[] args) {
        QueueDataFactory factory = new QueueDataFactory();
        int bufferSize = 16;
        Disruptor<QueueData> disruptor = new Disruptor<>(
                factory, bufferSize, Executors.defaultThreadFactory(), ProducerType.MULTI, new BlockingWaitStrategy()
        );
        disruptor.handleEventsWithWorkerPool(
                new WorkConsumer());
//        disruptor.handleEventsWith(new EventConsumer(1),new EventConsumer(2));
        disruptor.start();
        Producer producer = new Producer(disruptor.getRingBuffer());
        for (int i = 0; i < 10000; i++) {
            producer.pushData(i);
        }
    }
}
