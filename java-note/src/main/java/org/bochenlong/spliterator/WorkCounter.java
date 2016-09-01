package org.bochenlong.spliterator;

/**
 * Created by bcl on 2016/9/1.
 */
public class WorkCounter {
    private final int counter;
    private final boolean lastSpace;

    public WorkCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WorkCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WorkCounter(counter, true);
        } else {
            return lastSpace ? new WorkCounter(counter + 1, false) : this;
        }
    }

    public WorkCounter combine(WorkCounter workCounter) {
        return new WorkCounter(counter + workCounter.counter, workCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}
