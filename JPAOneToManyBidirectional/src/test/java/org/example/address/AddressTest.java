package org.example.address;

import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.example.customer.Customer;
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
    public static final int ADDRESS_ID = 11;

    private static EntityManagerFactory entityManagerFactory;

    private static EntityManager entityManager;

    private AddressDaoImpl addressDao;

//    @BeforeAll
//    public static void setUp() {
//        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//        entityManager = entityManagerFactory.createEntityManager();
//    }
//
//    @AfterAll
//    public static void tearDown() {
//        entityManager.close();
//        entityManagerFactory.close();
//    }

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

    @Test
    public void shouldUpdateCustomerForAddress() {

        Address address = entityManager.find(Address.class, ADDRESS_ID);

        System.out.println("-----Address with original customer-----");
        System.out.println("Address: " + address.getAddress());
        System.out.println("Phone number: " + address.getPhone());
        System.out.println("-- Customers for the address: --");
        address.getCustomerList().forEach(customer -> {
            System.out.println("First name: " + customer.getFirstName());
            System.out.println("Last name: " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
        });

        entityManager.getTransaction().begin();

        address.getCustomerList().get(0).setFirstName("Mary");
        address.getCustomerList().get(0).setLastName("Smith");
        address.getCustomerList().get(0).setEmail("Mary@gmail.com");

        entityManager.getTransaction().commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();

        Address addressWithUpdatedCustomer = entityManager.find(Address.class, ADDRESS_ID);

        System.out.println("-----Address with original customer-----");
        System.out.println("Address: " + addressWithUpdatedCustomer.getAddress());
        System.out.println("Phone number: " + addressWithUpdatedCustomer.getPhone());
        System.out.println("-- Customers for the address: --");
        addressWithUpdatedCustomer.getCustomerList().forEach(customer -> {
            System.out.println("First name: " + customer.getFirstName());
            System.out.println("Last name: " + customer.getLastName());
            System.out.println("Email: " + customer.getEmail());
        });
    }

    @Test
    public void shouldSaveCustomerToAddress() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Address address = entityManager.find(Address.class, ADDRESS_ID);
        address.getCustomerList().forEach(System.out::println);

        Customer customer = createCustomer(address);

        entityManager.persist(customer);

        address.getCustomerList().add(customer);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void shouldDeleteCustomerFromAddress() {
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Address address = entityManager.find(Address.class, 184);

        address.getCustomerList().remove(0);


        entityManager.flush();
        entityManager.clear();

        entityManager.getTransaction().commit();

        entityManager.close();

    }

    private Customer createCustomer(Address address) {
        Customer customer = new Customer();
        customer.setFirstName("John OneToMany");
        customer.setLastName("Doe Bidirectional");
        customer.setEmail("johndoe@gmail.com");
        customer.setAddress(address);
        customer.setStoreId(1);
        return customer;
    }
}
