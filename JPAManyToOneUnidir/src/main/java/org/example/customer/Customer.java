package org.example.customer;

import org.example.address.Address;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @NotNull
    @Size(max=45)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(max=45)
    @Column(name = "last_name")
    private String lastName;

    @Size(max=50)
    @Email
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer() {

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customerB = (Customer) o;
        return Objects.equals(customerId, customerB.customerId) && Objects.equals(firstName, customerB.firstName) && Objects.equals(lastName, customerB.lastName) && Objects.equals(email, customerB.email) && Objects.equals(address, customerB.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, email, address);
    }

    @Override
    public String toString() {
        return "CustomerB{" +
                "customer_id=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", addressB=" + address +
                '}';
    }
}
