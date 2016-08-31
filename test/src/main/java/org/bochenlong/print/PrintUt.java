package org.bochenlong.print;

/**
 * Created by bcl on 2016/8/31.
 */
public class PrintUt {
    public static void print(Object... args) {
        StringBuffer sb = new StringBuffer();
        for (Object o:args) {
            sb.append(o.toString());
            sb.append("-");
        }
        System.out.println(sb.toString());
    }

}
