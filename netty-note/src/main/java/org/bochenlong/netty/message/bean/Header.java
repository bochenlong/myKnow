package org.bochenlong.netty.message.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bochenlong on 16-11-4.
 */
public class Header {
    private int crcCode = 0xabef0101;
    private int length;
    private long sessionId;
    private short type; /** see {@link org.bochenlong.netty.message.type}*/
    private byte priority;
    private Map<String, Object> attachment = new HashMap<>();
    
    public int getCrcCode() {
        return crcCode;
    }
    
    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }
    
    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }
    
    public long getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
    
    public short getType() {
        return type;
    }
    
    public void setType(short type) {
        this.type = type;
    }
    
    public byte getPriority() {
        return priority;
    }
    
    public void setPriority(byte priority) {
        this.priority = priority;
    }
    
    public Map<String, Object> getAttachment() {
        return attachment;
    }
    
    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }
}
