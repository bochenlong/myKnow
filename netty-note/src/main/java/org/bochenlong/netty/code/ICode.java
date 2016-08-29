package org.bochenlong.netty.code;

/**
 * Created by bcl on 2016/8/29.
 */
public interface ICode {
    byte[] code(Object o);
    Object decode(byte[] bytes);
}
