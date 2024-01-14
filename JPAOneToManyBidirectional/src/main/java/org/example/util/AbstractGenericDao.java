package org.example.util;

import java.lang.reflect.Field;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public abstract class AbstractGenericDao<T>  implements GenericDao<T> {  //L11: empty konstruktor, reflectionnel entityclass

  private Class<T> entityClass;

  protected AbstractGenericDao() {  ///TBD!!
    try {
      Field f = Class.forName("org.example.util.AbstractGenericDao").getDeclaredField("entityClass");
      f.setAccessible(true);
      this.entityClass = (Class<T>) f.getDeclaringClass().getClass();
      f.set(entityClass, entityClass.getName());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public T getById(Integer id) {
    return EntityManagerUtil.getEntityManager().find(entityClass, id);
  }

  public List<T> getAll() {
      return EntityManagerUtil.getEntityManager().createQuery("from " + entityClass.getName()).getResultList();
  }

  public void insert(T entity) {
    EntityManagerUtil.beginTransaction();
    EntityManagerUtil.persist(entity);
    EntityManagerUtil.commit();
    EntityManagerUtil.closeEntityManager();
  }

  public void delete(T entity) {
    EntityManagerUtil.beginTransaction();
    EntityManagerUtil.remove(entity);
    EntityManagerUtil.commit();
    EntityManagerUtil.closeEntityManager();
  }

  @Transactional
  public void update(Integer id, String propertyName, Object propertyValue) {
    EntityManager entityManager = EntityManagerUtil.getEntityManager();
    entityManager.getTransaction().begin();
    Object entity = entityManager.createNativeQuery("update " + entityClass.getName() + " set " + propertyName + " = " + propertyValue.getClass() + " where " + entityClass.getName() + "_id = " + id);
    entityManager.merge(entity);
    EntityManagerUtil.commit();
    entityManager.close();
  }

  public List<T> findByProperty(String propertyName, Object propertyValue) {
      return EntityManagerUtil.getEntityManager().createQuery("from " + entityClass.getName() + " where " + propertyName + " = " + propertyValue).getResultList();
  }
}
