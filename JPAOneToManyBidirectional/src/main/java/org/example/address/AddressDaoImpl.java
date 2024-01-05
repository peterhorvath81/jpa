package org.example.address;

import org.example.util.AbstractGenericDao;

public class AddressDaoImpl extends AbstractGenericDao<Address> {

    private static AddressDaoImpl instance;

    public static synchronized AddressDaoImpl getInstance() {
        if (instance == null) {
            instance = new AddressDaoImpl(Address.class);
        }
        return instance;
    }
    private AddressDaoImpl(Class<Address> entityClass) {
        super(entityClass);
    }
}
