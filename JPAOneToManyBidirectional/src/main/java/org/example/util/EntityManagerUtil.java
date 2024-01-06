package org.example.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private static final String PERSISTENCE_UNIT_NAME = "dvdrental";

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);;

    private static final ThreadLocal<EntityManager> threadlocal = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        EntityManager entityManager = threadlocal.get();
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
            threadlocal.set(entityManager);
        }
        return entityManager;
    }

    public static void closeEntityManager() {
        EntityManager entityManager = threadlocal.get();
        if (entityManager != null) {
            entityManager.close();
            threadlocal.remove();
        }
    }

    public static void beginTransaction() {
        getEntityManager().getTransaction().begin();
    }

    public static void persist(Object object) {
        getEntityManager().persist(object);
    }

    public static void remove(Object object) {
        getEntityManager().remove(object);
    }




}
