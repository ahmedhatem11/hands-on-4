package com.sumerge.program.user.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "PROJECT", schema = "STAFF")
@NamedQueries({
        @NamedQuery(name = "Project.findAll", query = "SELECT e FROM Project e")
})
public class Project implements Serializable {

    @Id
    @Column(name = "PROJID", nullable = false)
    private String projId;

    @Column(name = "PROJNAME", nullable = false)
    private String projName;

    @Column(name = "STARTDATE")
    private Date startDate;

    @Column(name = "TARGETDATE")
    private Date targetDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "PROJECTMEMBER", joinColumns = @JoinColumn(name = "PROJID"), inverseJoinColumns = @JoinColumn(name = "EMPID"))
    private List<Employee> employees;

    public Project() {
    }

    public String getProjId() {
        return projId;
    }

    public void setProjId(String projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
