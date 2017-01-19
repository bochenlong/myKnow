package org.bochenlong.park;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by bochenlong on 17-1-17.
 */
public class ParkTest {
    private static LinkedBlockingQueue queue = new LinkedBlockingQueue();
    
    public static void main(String[] args) throws InterruptedException {
        Thread t;
        t = new Thread(() -> {
            while (true) {
                take();
            }
        });
        t.start();
        Thread.sleep(2000L);
        t.interrupt();
        System.out.println("hello world");
    }
    
    private static void take() {
        try {
            queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
