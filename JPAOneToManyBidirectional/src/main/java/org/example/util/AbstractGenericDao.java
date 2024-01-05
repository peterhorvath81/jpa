package org.example.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

public abstract class AbstractGenericDao<T>  implements GenericDao<T> {

  private static final String PERSISTENCE_UNIT_NAME = "dvdrental";

  private static final EntityManagerFactory entityManagerFactory;

  private static EntityManager entityManager;

  private Class<T> entityClass;

  protected AbstractGenericDao(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  static {
    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    entityManager = entityManagerFactory.createEntityManager();
  }


  public T getById(Integer id) {
    checkEntityManager();
    return entityManager.find(entityClass, id);
  }

  public List<T> getAll() {
      return entityManager.createQuery("from " + entityClass.getName()).getResultList();
  }

  public void insert(T entity) {
    checkEntityManager();
    entityManager.getTransaction().begin();
    entityManager.persist(entity);
    entityManager.close();
  }

  public void delete(T entity) {
    checkEntityManager();
    entityManager.getTransaction().begin();
    entityManager.remove(entity);
    entityManager.close();
  }

  @Transactional
  public void update(Integer id, String propertyName, Object propertyValue) {
    checkEntityManager();
    entityManager.getTransaction().begin();
    Object entity = entityManager.createNativeQuery("update address set " + propertyName + " = " + propertyValue.getClass() + " where address_id = " + id);
    entityManager.merge(entity);
    entityManager.close();
  }

  public List<T> findByProperty(String propertyName, Object propertyValue) {
    checkEntityManager();
      return entityManager.createQuery("from " + entityClass.getName() + " where " + propertyName + " = " + propertyValue).getResultList();
  }

  private static void checkEntityManager() {
    if (!entityManager.isOpen()) {
      entityManager = entityManagerFactory.createEntityManager();
    }
  }
}
