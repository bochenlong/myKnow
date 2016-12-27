package org.bochenlong.netty.message;

/**
 * Created by bochenlong on 16-12-22.
 */
public enum MessageType {
    HEART((short) 0),
    AUTH((short) 1),
    BUSINESS((short) 100);
    
    private short type;
    
    MessageType(short type) {
        this.type = type;
    }
    
    public short getType() {
        return type;
    }
    
    public static boolean isSystemType(MessageType type) {
        return type.getType() >= 0 && type.getType() < 100;
    }
}
