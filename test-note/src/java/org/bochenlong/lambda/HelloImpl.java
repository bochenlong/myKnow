package org.bochenlong.lambda;

/**
 * Created by bochenlong on 16-9-21.
 */
public class HelloImpl implements Hello {
    @Override
    public boolean world(String str) {
        if (str.equals("world")) return true;
        return false;
    }
}
