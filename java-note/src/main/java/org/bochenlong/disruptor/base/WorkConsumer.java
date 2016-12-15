package org.bochenlong.disruptor.base;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by bochenlong on 16-12-14.
 * <p>
 * 当前Sequence的数据只能被一个WorkConsumer消费
 */
@FunctionalInterface
public interface WorkConsumer extends WorkHandler<QueueData> {
}
