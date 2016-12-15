package org.bochenlong.disruptor.base;

import com.lmax.disruptor.EventHandler;

/**
 * Created by bochenlong on 16-12-14.
 * <p>
 * 当前Sequence的数据会被全部EventConsumer消费，且只有被全部消费才会释放Sequence
 */
@FunctionalInterface
public interface EventConsumer extends EventHandler<QueueData> {
}
