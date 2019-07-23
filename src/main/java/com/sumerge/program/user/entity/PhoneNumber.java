package com.sumerge.program.user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PHONENUMBER", schema = "STAFF")
@NamedQueries({
        @NamedQuery(name = "PhoneNumber.findAll", query = "SELECT e FROM PhoneNumber e")
})
public class PhoneNumber  implements Serializable {

    @Id
    @Column(name = "PHONEID", nullable = false)
    private int phoneID;

    @Column(name = "INTLPREFIX")
    private String intlPrefix;

    @Column(name = "LOCALNUM", nullable = false)
    private String localNum;

    @Column(name = "PHONETYPE", nullable = false)
    private String phoneType;

    @ManyToOne
    @JoinColumn(name = "EMPID", nullable = false)
    private Employee employee;

    public PhoneNumber() {
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
    }

    public String getIntlPrefix() {
        return intlPrefix;
    }

    public void setIntlPrefix(String intlPrefix) {
        this.intlPrefix = intlPrefix;
    }

    public String getLocalNum() {
        return localNum;
    }

    public void setLocalNum(String localNum) {
        this.localNum = localNum;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
