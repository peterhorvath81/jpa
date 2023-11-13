package org.example.customer;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CustomerTest {

    public static final Integer ID = 7;
    public static final String PERSISTENCE_UNIT_NAME = "dvdrental";

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

        Customer customerB = entityManager.find(Customer.class, ID);

        System.out.println("First name: " + customerB.getFirstName());
        System.out.println("Last_name: " + customerB.getLastName());
        System.out.println("Address: " + customerB.getAddress().getAddress());
    }

    @Test
    public void shouldFindCustomerFirstnameStartsWithM() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);

        criteriaQuery.where(criteriaBuilder.like(customer.get("firstName"), "M%"));
        TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery.select(customer));
        List<Customer> list = typedQuery.getResultList();

        list.forEach(cust -> {
            System.out.println("-----------");
            System.out.println("First name: " + cust.getFirstName());
            System.out.println("Last name: " + cust.getLastName());
            System.out.println("Address: " + cust.getAddress().getAddress());
            System.out.println("-----------");
        });
    }
}
