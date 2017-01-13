package org.bochenlong.spi;

/**
 * Created by bochenlong on 17-1-9.
 */
public class TextHello implements IHello {
    @Override
    public void whoSayHello() {
        System.out.println("TextHello say hello");
    }
}
