package org.bochenlong.netty.code.impl;

import org.bochenlong.netty.code.ICode;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * Created by bcl on 2016/8/29.
 */
public class MessagePackCode implements ICode {

    @Override
    public byte[] code(Object o) {
        MessagePack messagePack = new MessagePack();
        try {
            return messagePack.write(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object decode(byte[] bytes) {
        MessagePack messagePack = new MessagePack();
        try {
            return messagePack.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
