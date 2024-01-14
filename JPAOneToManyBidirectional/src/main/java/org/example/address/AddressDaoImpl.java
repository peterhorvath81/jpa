package org.example.address;

import org.example.util.AbstractGenericDao;

public class AddressDaoImpl extends AbstractGenericDao<Address> {

    private static AddressDaoImpl instance;

    public static AddressDaoImpl getInstance() {
        if (instance == null) {
            synchronized (AddressDaoImpl.class) {
                if (instance == null) {
                    instance = new AddressDaoImpl();
                }
            }
        }
        return instance;
    }
    private AddressDaoImpl() {
        super();
    }

}
