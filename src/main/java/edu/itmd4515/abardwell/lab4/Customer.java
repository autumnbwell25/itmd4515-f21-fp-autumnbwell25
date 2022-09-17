package edu.itmd4515.abardwell.lab4;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

import java.time.LocalDate;

@Entity
public class Customer {


    @Id
    @NotNull
    @Positive
    private Integer id;
    private Integer storeId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    // this is not a required field in the database, but I am doing it anyway for illustration purposes
    @Email
    private String email;

    private Integer addressId;
    private Boolean active;

    @PastOrPresent
    private LocalDate createDate;

    public Customer() {
    }

    public Customer(Integer id, Integer storeId, String firstName, String lastName, String email, Integer addressId, Boolean active, LocalDate createDate) {
        this.id = id;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
        this.active = active;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", storeId=" + storeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", addressId=" + addressId +
                ", active=" + active +
                ", createDate=" + createDate +
                '}';
    }
}