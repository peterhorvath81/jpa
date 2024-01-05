package org.example.address;

import org.junit.jupiter.api.Test;

import java.util.List;

public class AddressDaoTest {

    public static final Integer ADDERSS_ID = 10;

    private AddressDaoImpl addressDao = AddressDaoImpl.getInstance();

    @Test
    public void shouldGetById() {
        Address address = addressDao.getById(ADDERSS_ID);
        System.out.println(address.getAddress());
    }

    @Test
    public void shouldGetAll() {
        List<Address> addressList = addressDao.getAll();
        addressList.forEach(System.out::println);
    }

    @Test
    public void shouldInsert() {
        Address address = new Address();
        address.setAddress("asd");
        address.setDistrict("asd");
        address.setPhone("123");
        address.setCityId(1);
        addressDao.insert(address);
    }

    @Test
    public void shouldDelete() {
        Address address = addressDao.getById(ADDERSS_ID);
        address.setCustomerList(null);
        addressDao.delete(address);
    }

    @Test
    public void shouldUpdate() {
        addressDao.update(1, "address", "asd");
    }

    @Test
    public void shouldFindByProprty() {
        List<Address> addressList = addressDao.findByProperty("city_id", 300);
        addressList.forEach(System.out::println);
    }

    @Test
    public void shouldInsertAndGet() {
        Address address = new Address();
        address.setAddress("asd");
        address.setDistrict("asd");
        address.setPhone("123");
        address.setCityId(1);
        addressDao.insert(address);
        Address address2 = addressDao.getById(ADDERSS_ID);

    }


}
