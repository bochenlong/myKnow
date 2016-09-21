package org.bochenlong.lamda;

import org.bochenlong.time8.TimeUt;

/**
 * Created by bochenlong on 16-9-21.
 */
public class Test {
    public static void main(String[] args) {
        long t = TimeUt.currT();
        for (int i = 0; i < 100000000; i++) {
            Hello h = new HelloImpl();
            boolean f = h.world("shit");
        }
        TimeUt.useTP(t);
        t = TimeUt.currT();
        for (int i = 0; i < 100000000; i++) {
            Hello h = (a) -> a.equals("world");
            boolean a = h.world("shit");
        }
        TimeUt.useTP(t);
    }
}
