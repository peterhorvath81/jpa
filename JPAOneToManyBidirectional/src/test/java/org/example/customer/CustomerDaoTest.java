package org.example.customer;

import java.util.List;
import org.junit.jupiter.api.Test;

public class CustomerDaoTest {

  public static final Integer ID = 7;

  private CustomerDaoImpl customerDao = new CustomerDaoImpl();


  @Test
  public void shouldFindCustomerById() {
    Customer customer = customerDao.getById(ID);
    System.out.println(customer.getFirstName());
  }

  @Test
  public void shouldGetAll() {
    List<Customer> customerList = customerDao.getAll();
  }


}
