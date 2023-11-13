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
    public void shouldFindCustomerAWithId() {
        Customer customer = entityManager.getReference(Customer.class, ID);
        System.out.println(customer.getCustomerId());

        System.out.println("First name: " + customer.getFirstName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Email: " + customer.getEmail());
    }

    @Test
    public void shouldFindCustomerAWithFirstNameStartsWithM() {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customer = criteriaQuery.from(Customer.class);

        criteriaQuery.where(criteriaBuilder.like(customer.get("firstName"), "M%"));
        TypedQuery<Customer> typedQuery = entityManager.createQuery(criteriaQuery.select(customer));
        List<Customer> list = typedQuery.getResultList();

        list.forEach(customerA -> {
            System.out.println("-----------");
            System.out.println("First Name: " + customerA.getFirstName());
            System.out.println("Last Name: " + customerA.getLastName());
            System.out.println("-----------");
        });
    }
}
