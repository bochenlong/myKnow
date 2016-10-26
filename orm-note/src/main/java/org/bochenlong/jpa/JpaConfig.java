package org.bochenlong.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by bochenlong on 16-10-26.
 */
public class JpaConfig {
    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
}
