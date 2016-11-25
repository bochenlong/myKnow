package org.bochenlong.pdxnetty.message;

/**
 * Created by bochenlong on 16-11-4.
 */
public class Header {
    private byte type;
    private byte back;
    private byte isBackMsg;

    public byte getType() {
        return type;
    }

    public Header setType(byte type) {
        this.type = type;
        return this;
    }

}
