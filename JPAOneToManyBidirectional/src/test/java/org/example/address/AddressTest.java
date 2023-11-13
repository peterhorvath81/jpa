package org.example.address;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class AddressTest {

    public static final String SQL_CUSTOMER_WITH_ID = "select address, first_name, last_name, email from address a join customer c ON a.address_id=c.address_id " +
            "WHERE c.customer_id=7";

    public static final String SQL_CUSTOMER_FIRST_NAME = "select address, first_name, last_name, email from address a join customer c ON a.address_id=c.address_id " +
            "WHERE c.first_name LIKE 'M%'";
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

        Query query = entityManager.createNativeQuery(SQL_CUSTOMER_WITH_ID);

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
    public void shouldFindUserWithFirstName() {

        entityManager.getTransaction().begin();

        Query query = entityManager.createNativeQuery(SQL_CUSTOMER_FIRST_NAME);

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
}
