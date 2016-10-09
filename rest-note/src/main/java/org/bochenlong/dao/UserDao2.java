package org.bochenlong.dao;

import org.bochenlong.util.JedisUt;

/**
 * Created by bochenlong on 16-10-9.
 */
public class UserDao2 {
    public void save(String name, String password) {
        JedisUt.set(name, password);
    }
}
