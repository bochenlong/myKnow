package org.bochenlong.time;

/**
 * Created by bcl on 2016/8/31.
 */
public class TimeUt {
    public static long currT() {
        return System.currentTimeMillis();
    }

    public static long useT(long t) {
        return System.currentTimeMillis() - t;
    }

    public static void currTP() {
        System.out.println("当前时间：" + System.currentTimeMillis());
    }

    public static void useTP(long t) {
        System.out.println("耗时时间：" + (System.currentTimeMillis() - t));
    }
}
