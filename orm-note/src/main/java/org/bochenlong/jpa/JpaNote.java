package org.bochenlong.jpa;

import org.bochenlong.jpa.bean.Address;
import org.bochenlong.jpa.bean.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by bochenlong on 16-10-17.
 */
public class JpaNote {
    public static void main(String[] args) {
        Person p = new Person();
        p.setFullname("abc");
        p.setId(2);
        Address address = new Address();
        address.setPerson(p);
        address.setAddress("abc");
        p.addAddress(address);
        insert(p);
    }

    public static void insert(Person p) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
