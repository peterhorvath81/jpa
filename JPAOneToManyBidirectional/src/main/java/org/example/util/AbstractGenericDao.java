package org.example.util;

import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public abstract class AbstractGenericDao<T>  implements GenericDao<T> {

  private Class<T> entityClass;

  protected AbstractGenericDao(Class<T> entityClass) {
    this.entityClass = entityClass;
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
    EntityManagerUtil.closeEntityManager();
  }

  public void delete(T entity) {
    EntityManagerUtil.beginTransaction();
    EntityManagerUtil.remove(entity);
    EntityManagerUtil.closeEntityManager();
  }

  @Transactional
  public void update(Integer id, String propertyName, Object propertyValue) {
    EntityManager entityManager = EntityManagerUtil.getEntityManager();
    entityManager.getTransaction().begin();
    Object entity = entityManager.createNativeQuery("update address set " + propertyName + " = " + propertyValue.getClass() + " where address_id = " + id);
    entityManager.merge(entity);
    entityManager.close();
  }

  public List<T> findByProperty(String propertyName, Object propertyValue) {
      return EntityManagerUtil.getEntityManager().createQuery("from " + entityClass.getName() + " where " + propertyName + " = " + propertyValue).getResultList();
  }
}
