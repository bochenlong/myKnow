package org.bochenlong.orm;

import org.apache.ibatis.session.SqlSession;
import org.bochenlong.jpa.bean.Address;
import org.bochenlong.jpa.bean.Person;
import org.bochenlong.mybatis.bean.Location;
import org.bochenlong.mybatis.bean.Useru;
import org.bochenlong.jpa.JpaConfig;
import org.bochenlong.mybatis.config.MybatisConfig;
import org.bochenlong.mybatis.PersonMapper;
import org.bochenlong.parallel.PExec;

import javax.persistence.EntityManager;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created by bochenlong on 16-10-11.
 */
public class OrmTest {
    public static void main(String[] args) {
        Callable callable = () -> {
            mybatisTest();
//            jpaTest();
            return 1;
        };

        PExec.exec(1000, 100000, callable);
    }


    private static void jpaTest() {
        EntityManager em = JpaConfig.factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(generatePerson(generateId()));
        em.getTransaction().commit();
        em.close();
    }


    private static void mybatisTest() {
        SqlSession sqlSession = MybatisConfig.sqlSessionFactory.openSession();
        try {
            PersonMapper userMapper = sqlSession.getMapper(PersonMapper.class);
            int id = generateId();
            userMapper.insertUser(generateUser(id));
            userMapper.insertLocation(generateLocation(id));
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        }
        sqlSession.commit();
        sqlSession.close();
    }

    private static Person generatePerson(int id) {
        Person p = new Person();
        p.setFullname("abc");
        p.setId(id);
        Address address = new Address();
        address.setPerson(p);
        address.setAddress("abc");
        p.addAddress(address);
        return p;
    }

    private static LongAdder ider = new LongAdder();

    private static Useru generateUser(int id) {
        Useru user = new Useru();
        user.setId(id);
        user.setFullname("abc");
        return user;
    }

    private static Location generateLocation(int id) {
        Location location = new Location();
        location.setId(id);
        location.setLocation("abc");
        location.setUserId(id);
        return location;
    }

    private synchronized static int generateId() {
        ider.increment();
        int id = ider.intValue();
        return id;
    }


}
