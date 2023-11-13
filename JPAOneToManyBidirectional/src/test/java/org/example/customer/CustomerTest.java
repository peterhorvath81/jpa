package org.example.customer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerTest {

    public static final Integer ID = 7;
    public static final String PERSISTENCE_UNIT_NAME = "dvdrental";
    public static final String JPQL_FIRSTNAME_STARTS_WITH_M = "select a.address, c.firstName, c.lastName, c.email from Address a, Customer c  WHERE c.firstName LIKE 'M%'";

    public static final String SQL_CUSTOMER_FIRST_NAME = "select address, first_name, last_name, email from address a join customer c ON a.address_id=c.address_id " +
            "WHERE c.first_name LIKE ?1";

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    @BeforeAll
    public static void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void tearDown() {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void shouldFindCustomerWithId() {

        Customer customer = entityManager.find(Customer.class, ID);

        System.out.println("-----------");
        System.out.println("First name: " + customer.getFirstName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Address: " + customer.getAddress().getAddress());
        System.out.println("-----------");

    }

    @Test
    public void shouldFindCustomerFirstNameStartsWithM() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);

        criteriaQuery.where(criteriaBuilder.like(customer.get("firstName"), "M%"));
        TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery.select(customer));
        List<Customer> list = typedQuery.getResultList();

        list.forEach(cust -> {
            System.out.println("-----------");
            System.out.println("First Name: " + cust.getFirstName());
            System.out.println("Last Name: " + cust.getLastName());
            System.out.println("Address: " + cust.getAddress().getAddress());
            System.out.println("-----------");
        });
    }

    @Test
    public void shouldFindCustomerWithFirstNameMUsingJPQL() {

        Query query = entityManager.createQuery(JPQL_FIRSTNAME_STARTS_WITH_M);

        List<Object[]> resultList = query.getResultList();

        resultList.forEach(object -> {
            System.out.println("-------------");
            System.out.println("Address: " + object[0]);
            System.out.println("First name: " + object[1]);
            System.out.println("Last name: " + object[2]);
            System.out.println("Email: " + object[3]);
            System.out.println("-------------");
        });
    }

    @Test
    public void shouldFindCustomerWithFirstnameMUsingSQL() {

        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(SQL_CUSTOMER_FIRST_NAME, Customer.class);

        String queryParam = "N";
        List<Object[]> resultList = query.setParameter(1, queryParam).getResultList();

        resultList.forEach(object -> {
            System.out.println("-------------");
            System.out.println("Address: " + object[0]);
            System.out.println("First name: " + object[1]);
            System.out.println("Last name: " + object[2]);
            System.out.println("Email: " + object[3]);
            System.out.println("-------------");
        });
    }
}
