package org.bochenlong.disruptor.base;

/**
 * Created by bochenlong on 16-12-14.
 */
public class QueueData {
    private Object value;

    public QueueData() {

    }

    public QueueData(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
