package org.bochenlong.disruptor.base;

import com.lmax.disruptor.RingBuffer;

/**
 * Created by bochenlong on 16-12-14.
 */
public class Producer {
    private final RingBuffer<QueueData> ringBuffer;

    public Producer(RingBuffer<QueueData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void pushData(long value) {
        long sequence = ringBuffer.next();
        try {
            QueueData event = ringBuffer.get(sequence);
            event.setValue(value);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
