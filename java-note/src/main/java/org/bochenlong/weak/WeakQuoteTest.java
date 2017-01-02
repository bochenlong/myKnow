package org.bochenlong.weak;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bochenlong on 16-12-27.
 */
public class WeakQuoteTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger();
        WeakHashMap<Integer, Integer> weakHashMap = new WeakHashMap<>();
        while (true) {
            if (i.get() == 4000000) {
                System.out.println(weakHashMap.size());
                break;
            }
            weakHashMap.put(i.get(), i.getAndIncrement());
        }
//        HashMap<Integer, Integer> hashMap = new HashMap<>();
//        while (true) {
//            System.out.println(i);
//            hashMap.put(i.get(), i.getAndIncrement());
//        }
    }
}
