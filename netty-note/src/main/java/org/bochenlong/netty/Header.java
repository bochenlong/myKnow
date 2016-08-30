package org.bochenlong.netty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bcl on 2016/8/30.
 */
public final class Header {
    private int crcCode = 0xabef0101;// 0xabef表示netty协议 01 主版本号 01 次版本号
    private int length;// 消息长度（包括消息头、消息体）
    private long sessionId;// 会话id（集群节点内全局唯一）
    private byte type;// 消息类型（0，业务请求消息；1，业务相应消息；2，业务ONE WAY消息（即是请求又是响应）
    // 3，握手请求消息 4，握手应答消息 5，心跳请求消息 6，心跳应答消息
    private byte priority;// 消息优先级 0-255

    private Map<String, Object> attacment = new HashMap<>();// 附件用于消息头扩展

    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionId=" + sessionId +
                ", type=" + type +
                ", priority=" + priority +
                ", attacment=" + attacment +
                '}';
    }

    public final int getCrcCode() {
        return crcCode;
    }

    public final void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public final int getLength() {
        return length;
    }

    public final void setLength(int length) {
        this.length = length;
    }

    public final long getSessionId() {
        return sessionId;
    }

    public final void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public final byte getType() {
        return type;
    }

    public final void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public final void setPriority(byte priority) {
        this.priority = priority;
    }

    public final Map<String, Object> getAttacment() {
        return attacment;
    }

    public final void setAttacment(Map<String, Object> attacment) {
        this.attacment = attacment;
    }
}
