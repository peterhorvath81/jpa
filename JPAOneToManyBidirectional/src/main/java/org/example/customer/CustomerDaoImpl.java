package org.example.customer;

import org.example.util.AbstractGenericDao;

public class CustomerDaoImpl extends AbstractGenericDao<Customer> {

    private static CustomerDaoImpl instance;

    public static synchronized CustomerDaoImpl getInstance() {
        if (instance == null) {
            instance = new CustomerDaoImpl(Customer.class);
        }
        return instance;
    }

    private CustomerDaoImpl(Class<Customer> entityClass) {
        super(entityClass);
    }
}
