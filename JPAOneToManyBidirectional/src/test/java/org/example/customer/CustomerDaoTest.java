package org.example.customer;

import java.util.Collections;
import java.util.List;

import org.example.address.Address;
import org.junit.jupiter.api.Test;

public class CustomerDaoTest {

  public static final Integer ID = 7;

  private CustomerDaoImpl customerDao = CustomerDaoImpl.getInstance();




  @Test
  public void shouldFindCustomerById() {
    Customer customer = customerDao.getById(ID);
    System.out.println(customer.getFirstName());
  }

  @Test
  public void shouldGetAll() {
    List<Customer> customerList = customerDao.getAll();
    customerList.forEach(System.out::println);
  }

  @Test
  public void shouldInsert() {
    Customer customer = new Customer();
    customer.setStoreId(1);
    customer.setFirstName("asd");
    customer.setLastName("asd");
    customer.setAddress(new Address(1,"asd", "asd", "asd", "asd", "123", Collections.emptyList(), 1));
    customerDao.insert(customer);
  }

  @Test
  public void shouldDelete() {
    Customer customer = customerDao.getById(ID);
    customer.setAddress(null);
    customerDao.delete(customer);
  }

  @Test
  public void shouldUpdate() {
    customerDao.update(1, "first_name", "asd");
  }

  @Test
  public void shouldFindBiyProperty() {
    List<Customer> customerList = customerDao.findByProperty("store_id", 1);
    customerList.forEach(System.out::println);
  }
}
