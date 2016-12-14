package org.bochenlong.disruptor.base;

/**
 * Created by bochenlong on 16-12-14.
 */
public class QueueData<T> {
    private T t;

    public T getValue() {
        return t;
    }

    public void setValue(T t) {
        this.t = t;
    }
}
