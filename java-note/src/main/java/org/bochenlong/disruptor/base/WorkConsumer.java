package org.bochenlong.disruptor.base;

import com.lmax.disruptor.WorkHandler;

/**
 * Created by bochenlong on 16-12-14.
 */
public class WorkConsumer implements WorkHandler<QueueData> {
    @Override
    public void onEvent(QueueData queueData) throws Exception {
        Thread.sleep(2000);
        System.out.println("@@@@@@" + queueData.getValue());
    }
}
