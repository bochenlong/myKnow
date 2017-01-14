package org.bochenlong.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by bochenlong on 16-12-14.
 */
public class QueueDataFactory implements EventFactory<QueueData> {
    @Override
    public QueueData newInstance() {
        return new QueueData();
    }
}
