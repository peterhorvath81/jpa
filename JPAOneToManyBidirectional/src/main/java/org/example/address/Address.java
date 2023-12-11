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

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private List<Customer> customerList;

    @NotNull
    @Column(name = "city_id")
    private Integer cityId;

    public Address(Integer addressId, String address, String address2, String district, String postalCode, String phone, List<Customer> customerList, Integer cityId) {
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.postalCode = postalCode;
        this.phone = phone;
        this.customerList = customerList;
        this.cityId = cityId;
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(addressId, address1.addressId) && Objects.equals(address, address1.address) && Objects.equals(address2, address1.address2) && Objects.equals(district, address1.district) && Objects.equals(postalCode, address1.postalCode) && Objects.equals(phone, address1.phone) && Objects.equals(customerList, address1.customerList) && Objects.equals(cityId, address1.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, address, address2, district, postalCode, phone, customerList, cityId);
    }

    @Override
    public String toString() {
        return "Address{" +
          "addressId=" + addressId +
          ", address='" + address + '\'' +
          ", address2='" + address2 + '\'' +
          ", district='" + district + '\'' +
          ", postalCode='" + postalCode + '\'' +
          ", phone='" + phone + '\'' +
//          ", customerList=" + customerList +
          ", cityId=" + cityId +
          '}';
    }
}
