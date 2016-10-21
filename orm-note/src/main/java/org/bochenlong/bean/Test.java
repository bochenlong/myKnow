package org.bochenlong.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bochenlong on 16-10-17.
 */
public class Test {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        LeTransaction u = new LeTransaction();
        u.setTxid("146");
        u.setBody("1111");
        LeMeta leMeta = new LeMeta();
        leMeta.setKeyq("a");
        leMeta.setValueq("b".getBytes());
        List<LeMeta> leMetaList = new ArrayList<LeMeta>();
        leMetaList.add(leMeta);
        u.setLeMeta(leMetaList);


        em.persist(u);
        em.getTransaction().commit();
        em.close();
        factory.close();
    }
}
