package com.sumerge.program.user.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "EMAIL", schema = "STAFF")
@NamedQueries({
        @NamedQuery(name = "Email.findAll", query = "SELECT e FROM Email e")
})
public class Email implements Serializable {

    @Id
    @Column(name = "EMAILID", nullable = false)
    private int emailId;

    @Column(name = "EMAILADDRESS", nullable = false)
    private String emailAdress;

    @Column(name = "EMAILTYPE", nullable = false)
    private String emailType;

    @ManyToOne
    @JoinColumn(name = "EMPID", nullable = false)
    private Employee employee;

    public Email() {
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
