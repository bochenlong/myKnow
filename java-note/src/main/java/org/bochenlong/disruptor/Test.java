package org.bochenlong.disruptor;

import org.bochenlong.disruptor.base.QueueData;
import org.bochenlong.disruptor.base.WorkConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by bochenlong on 16-12-14.
 */
public class Test {
    private static volatile long t = 0;
    private static long total = 1000000L;

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Object> linkedBlockingDeque =
                new LinkedBlockingDeque<>();
        DisruptorHelper.makeWorkQueue("a", (int) Math.pow(2, 20), new WorkConsumerImpl());

        ExecutorService service = Executors.newFixedThreadPool(1000);
        t = System.currentTimeMillis();
        service.execute(new QueueRunable(linkedBlockingDeque));

        for (int i = 0; i < total; i++) {
            int finalI = i;
            service.execute(() -> {
//                linkedBlockingDeque.add(finalI + 1);
                DisruptorHelper.pushData("a", finalI+1);
            });
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

    private static class QueueRunable implements Runnable {
        private LinkedBlockingDeque<Object> linkedBlockingDeque;

        public QueueRunable(LinkedBlockingDeque<Object> linkedBlockingDeque) {
            this.linkedBlockingDeque = linkedBlockingDeque;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if ((int) linkedBlockingDeque.take() == total) {
                        System.out.println("---" + (System.currentTimeMillis() - t));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
