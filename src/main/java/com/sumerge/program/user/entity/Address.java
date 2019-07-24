package com.sumerge.program.user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESS", schema = "STAFF")
@NamedQueries({
        @NamedQuery(name = "Address.findAll", query = "SELECT e FROM Address e")
})
public class Address implements Serializable {

    @Id
    @Column(name = "ADDRESSID", nullable = false)
    private int addressID;

    @Column(name = "ADDLINE1")
    private String addressLine1;

    @Column(name = "ADDLINE2")
    private String addressLine2;

    @Column(name = "CITY")
    private String city;

    @Column(name = "REGION")
    private String region;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "POSTCODE")
    private String postCode;

    @ManyToOne
    @JoinColumn(name = "EMPID", nullable = false)
    private Employee employee;

    public Address() {
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
