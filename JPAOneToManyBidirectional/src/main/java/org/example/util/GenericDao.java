package org.example.util;

import java.util.List;

public interface GenericDao<T, K>{
  T getById(K id);

  List<T> getAll();

  void insert(T entity);

  void delete(T entity);

  void update(K id, String propertyName, Object propertyValue);

  List<T> findByProperty(String propertyName, Object propertyValue);
}
