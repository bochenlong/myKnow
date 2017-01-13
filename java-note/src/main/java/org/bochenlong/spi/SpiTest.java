package org.bochenlong.spi;

import java.util.ServiceLoader;

/**
 * Created by bochenlong on 17-1-9.
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<IHello> services = ServiceLoader.load(IHello.class);
        for (IHello service : services) {
            service.whoSayHello();
        }
    }
}
