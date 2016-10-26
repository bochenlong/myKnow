package org.bochenlong.jpa;

import org.bochenlong.bean.jpa.Address;
import org.bochenlong.bean.jpa.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bochenlong on 16-10-17.
 */
public class JpaNote {
    public static void main(String[] args) {
        User user = new User();
        user.setName("abc");
        user.setId(1);
        Address address = new Address();
        address.setAddressId(1);
        address.setAddress("abc");
        user.addAddress(address);
        insert(user);
    }

    public static void insert(User user) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
