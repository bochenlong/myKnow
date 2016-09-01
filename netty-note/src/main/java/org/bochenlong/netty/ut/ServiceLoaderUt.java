package org.bochenlong.netty.ut;

import java.util.Iterator;
import java.util.Optional;
import java.util.ServiceLoader;

/**
 * Created by bcl on 2016/9/1.
 */
public class ServiceLoaderUt {

    public static <T> Optional<T> load(Class<T> t) {
        ServiceLoader<T> ts = ServiceLoader.load(t);
        Iterator<T> tIterator = ts.iterator();
        Optional<T> optional = Optional.ofNullable(tIterator.next());
        return optional;
    }

}
