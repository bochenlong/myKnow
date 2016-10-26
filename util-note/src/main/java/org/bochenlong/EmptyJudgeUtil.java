package org.bochenlong;

import java.util.List;
import java.util.Map;

/**
 * Created by bochenlong on 16-10-18.
 */
public class EmptyJudgeUtil {
    public static boolean isNotNullAndEmpty(byte[] bytes) {
        return bytes != null && bytes.length != 0;
    }

    public static boolean isNotNullAndEmpty(Map<?, ?> map) {
        return map != null && map.size() != 0;
    }

    public static boolean isNotNullAndEmpty(List<?> list) {
        return list != null && list.size() != 0;
    }

    public static boolean isNotNullAndEmpty(String str) {
        return str != null && str.length() != 0;
    }

    public static void main(String[] args) {
        System.out.println("".length());
    }
}
