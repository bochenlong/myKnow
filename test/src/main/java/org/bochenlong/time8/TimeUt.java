package org.bochenlong.time8;

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

    public static long currTPT() {
        long t = System.currentTimeMillis();
        System.out.println("当前时间：" + t);
        return t;
    }

    public static void useTP(long t) {
        System.out.println("耗时时间：" + (System.currentTimeMillis() - t));
    }

    public static long useTPT(long t) {
        long t1 = System.currentTimeMillis();
        System.out.println("耗时时间：" + (System.currentTimeMillis() - t));
        return t1;
    }
}
