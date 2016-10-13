package org.bochenlong.orm;

import org.apache.ibatis.session.SqlSession;
import org.bochenlong.parallel.PExec;

import java.io.File;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Created by bochenlong on 16-10-11.
 */
public class OrmTest {
    public static void main(String[] args) {
        System.out.println(SqlSession.class.getPackage().getName());
        System.out.println(SqlSession.class.getResource("/"));
        File file = new File(SqlSession.class.getResource("/")+SqlSession.class.getPackage()
                .getName());
        File[] files = file.listFiles();
        for(File f : files) {
            System.out.println(f.getName());
        }
    }

    private static void midaoTest() {
        Callable callable = () -> {
            UserDao userDao = new UserDao();
            userDao.save("bochenlong", "bochenlong");
            userDao.close();
            return 1;
        };
        PExec.exec(1000, 10000, callable);
    }

    private static void mybatisTest() {
        Callable callable = () -> {
            SqlSession sqlSession = DataConfig.sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User(UUID.randomUUID().toString().replace("-", ""),
                    "11", "11", new Timestamp(System.currentTimeMillis()));
            userMapper.insert(user);
            sqlSession.commit();
            sqlSession.close();
            return 1;
        };
        PExec.exec(1000, 10000, callable);
    }
}
