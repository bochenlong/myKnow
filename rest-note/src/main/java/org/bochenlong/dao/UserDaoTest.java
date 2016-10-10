package org.bochenlong.dao;

import org.bochenlong.parallel.PExec;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by bochenlong on 16-10-8.
 */
public class UserDaoTest {
    public static void main(String[] args) {
//        UserDao userDao = new UserDao();
//        userDao.deleteAll();
        Callable callable = () -> {
            UserDao userDao = new UserDao();
            userDao.save("bochenlong", "bochenlong");
            userDao.close();
            return 1;
//            UserDao2 userDao2 = new UserDao2();
//            userDao2.save(UUID.randomUUID().toString().replace("-", ""), "bochenlong");
//            return 1;
        };
        PExec.exec(1000, 10000, callable);
    }
}
