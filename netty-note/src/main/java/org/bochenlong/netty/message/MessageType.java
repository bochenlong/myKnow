package org.bochenlong.netty.message;

/**
 * Created by bochenlong on 16-12-22.
 */
public enum MessageType {
    BUSINESS((byte) 1),
    HEART((byte) 2),
    AUTH((byte) 3);
    
    private byte type;
    
    MessageType(byte type) {
        this.type = type;
    }
    
    public byte getType() {
        return type;
    }
}
