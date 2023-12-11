package org.example.util;


import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class AbstractGenericDao<T, K>  implements GenericDao<T, K> {

  private static final String PERSISTENCE_UNIT_NAME = "dvdrental";

  private static final EntityManagerFactory entityManagerFactory;

  private static final EntityManager entityManager;

  static {
    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    entityManager = entityManagerFactory.createEntityManager();
  }

  private T t;

  private K k;

  public K getK() {
    return k;
  }

  public T getT() {
    return t;
  }

  public T getById(K id) {
    Object result= entityManager
      .createQuery("from" + getT() + " where id =:id").setParameter("id", id).getSingleResult();
    return (T) result;
  }

  public List<T> getAll() {
    List<T> entityList = entityManager.createQuery("from" + getT()).getResultList();
    return entityList;
  }

  public void insert(T entity) {
    entityManager.getTransaction().begin();
    entityManager.persist(entity);
    entityManager.close();
  }

  public void delete(T entity) {
    entityManager.getTransaction().begin();
    entityManager.remove(entity);
    entityManager.close();
  }

  public void update(K id, String propertyName, Object propertyValue) {
    entityManager.getTransaction().begin();
    T entity = (T) entityManager.createQuery("from" + getT().getClass().getName() + "where id = :id", getT().getClass()).setParameter("id", id).getSingleResult();
    try{
      entity.getClass().getMethod("set" + propertyName, propertyValue.getClass()).invoke(entity, propertyValue);
    } catch (NoSuchMethodException | IllegalStateException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }
    entityManager.close();
  }

  public List<T> findByProperty(String propertyName, Object propertyValue) {
    List<T> entityList = (List<T>) entityManager
      .createQuery("from" + getT().getClass().getName() + "where" + propertyName + " = :propertyValue", getT().getClass())
      .setParameter("propertyValue", propertyValue).getResultList();
    return entityList;
  }
}
