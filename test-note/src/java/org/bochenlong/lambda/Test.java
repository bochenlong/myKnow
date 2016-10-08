package org.bochenlong.lambda;

import org.bochenlong.time8.TimeUt;

/**
 * Created by bochenlong on 16-9-21.
 * Lambda表达式的性能比实现类的性能慢不少
 */
public class Test {
    public static void main(String[] args) {
        long t = TimeUt.currT();
        for (int i = 0; i < 100000000; i++) {
            Hello h1 = new HelloImpl();
            boolean f = h1.world("shit");
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
