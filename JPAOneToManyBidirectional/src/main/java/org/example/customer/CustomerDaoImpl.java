package org.example.customer;

import org.example.util.AbstractGenericDao;

import java.lang.reflect.Field;

public class CustomerDaoImpl extends AbstractGenericDao<Customer> {

    private static CustomerDaoImpl instance;

    public static CustomerDaoImpl getInstance() {
        if (instance == null) {
            synchronized (CustomerDaoImpl.class) {
                if (instance == null) {
                    instance = new CustomerDaoImpl();
                }
            }
        }
        return instance;
    }

    private CustomerDaoImpl() {
        super();
    }
}
