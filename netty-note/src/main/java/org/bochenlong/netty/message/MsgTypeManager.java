package org.bochenlong.netty.message;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bochenlong on 16-12-26.
 */
public class MsgTypeManager {
    private static Set<Short> set = new HashSet<>();
    
    public static boolean addBussinessType(Short type) {
        return set.add(type);
    }
    
    public static boolean addBussinessTyps(Set<Short> types) {
        return set.addAll(types);
    }
    
}
