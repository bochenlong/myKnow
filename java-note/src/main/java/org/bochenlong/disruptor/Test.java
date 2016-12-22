package org.bochenlong.disruptor;

import org.bochenlong.disruptor.base.QueueData;
import org.bochenlong.disruptor.base.WorkConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bochenlong on 16-12-14.
 */
public class Test {
    private static volatile long t = 0;
    private static long total = 10000000L;

    public static void main(String[] args) throws InterruptedException {
        DisruptorHelper.makeWorkQueue("a", (int) Math.pow(2, 20), new WorkConsumerImpl());

        ExecutorService service = Executors.newFixedThreadPool(1000);
        t = System.currentTimeMillis();

        for (int i = 0; i < total; i++) {
            int finalI = i;
            service.execute(() ->
                DisruptorHelper.pushData("a", finalI+1));
        }

    }

    private static class WorkConsumerImpl implements WorkConsumer {

        @Override
        public void onEvent(QueueData queueData) throws Exception {
            if (((int) queueData.getValue()) == total) {
                System.out.println("---" + (System.currentTimeMillis() - t));
            }
        }
    }
}
