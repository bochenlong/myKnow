package org.bochenlong.netty.message.queue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * Created by bochenlong on 16-12-27.
 */
public class QueueFactory {
    private Supplier<LinkedBlockingQueue> boundedLBQ = () -> new LinkedBlockingQueue<>();
    private IntFunction<LinkedBlockingQueue> unBoundedLBQ = (int max) -> new LinkedBlockingQueue<>(max);
}
