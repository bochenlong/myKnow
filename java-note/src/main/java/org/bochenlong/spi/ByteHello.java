package org.bochenlong.spi;

/**
 * Created by bochenlong on 17-1-9.
 */
public class ByteHello implements IHello {
    @Override
    public void whoSayHello() {
        System.out.println("ByteHello say hello");
    }
}
