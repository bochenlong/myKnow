package org.bochenlong.netty.code.impl;

import org.bochenlong.netty.code.ICode;
import org.bochenlong.netty.code.test.UserInfoDemo;

import java.nio.ByteBuffer;

/**
 * Created by bcl on 2016/8/29.
 */
public class ByteCode implements ICode {
    @Override
    public byte[] code(Object o) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        UserInfoDemo u = (UserInfoDemo) o;
        byte[] value = u.getUserName().getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(u.getUserId());
        buffer.flip();
        byte[] result = new byte[buffer.remaining()];
        return result;
    }

    @Override
    public Object decode(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        return null;
    }
}
