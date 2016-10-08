package org.bochenlong.dao;

import org.bochenlong.parallel.PExec;

import java.util.concurrent.Callable;

/**
 * Created by bochenlong on 16-10-8.
 */
public class UserDaoTest {
    public static void main(String[] args) {
        Callable callable = () -> {
            UserDao userDao = new UserDao();
            userDao.save("bochenlong", "bochenlong");
            userDao.close();
            return 1;
        };
        PExec.exec(10000, 10000, callable);
    }
}
