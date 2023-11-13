package org.example.address;

import org.example.customer.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @NotNull
    @Size(max=50)
    private String address;

    @Size(max=50)
    private String address2;

    @NotNull
    @Size(max=20)
    private String district;

    @Size(max=10)
    @Column(name = "postal_code")
    private String postalCode;

    @NotNull
    @Size(max=20)
    private String phone;

    @OneToMany
    private List<Customer> customerList;

    public Address(Integer addressId, String address, String address2, String district, String postalCode, String phone, List<Customer> customerList) {
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
        this.customerList = customerList;
    }

    public Address() {

    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address addressC = (Address) o;
        return Objects.equals(addressId, addressC.addressId) && Objects.equals(address, addressC.address) && Objects.equals(address2, addressC.address2) && Objects.equals(district, addressC.district) && Objects.equals(postalCode, addressC.postalCode) && Objects.equals(phone, addressC.phone) && Objects.equals(customerList, addressC.customerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, address2, district, postalCode, phone, customerList);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address_id=" + addressId +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", distinct='" + district + '\'' +
                ", postal_code='" + postalCode + '\'' +
                ", phone='" + phone + '\'' +
                ", customerList=" + customerList +
                '}';
    }
}
