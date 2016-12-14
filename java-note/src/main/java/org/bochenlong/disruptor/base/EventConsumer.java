package org.bochenlong.disruptor.base;

import com.lmax.disruptor.EventHandler;

/**
 * Created by bochenlong on 16-12-14.
 */
public class EventConsumer implements EventHandler<QueueData> {
    private int index;

    public EventConsumer(int index) {
        this.index = index;
    }

    @Override
    public void onEvent(QueueData queueData, long l, boolean b) throws Exception {
        if (index == 1) {
            Thread.sleep(1000);
        } else {
            Thread.sleep(5000);
        }
        System.out.println(index + "-----" + queueData.getValue());
    }
}
