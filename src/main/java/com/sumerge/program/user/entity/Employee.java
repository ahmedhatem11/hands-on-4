package com.sumerge.program.user.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE", schema = "STAFF")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
})
@XmlRootElement
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "EMPID", nullable = false)
    private String empId;

    @ManyToOne
    @JoinColumn(name = "DEPTCODE", nullable = false)
    private Department deptCode;

    @Column(name = "JOBTITLE")
    private String jobTitle;

    @Column(name = "GIVENNAME", nullable = false)
    private String givenName;

    @Column(name = "FAMILYNAME", nullable = false)
    private String familyName;

    @Column(name = "COMMONNAME")
    private String commonName;

    @Column(name = "NAMETITLE")
    private String NameTitle;

    @ManyToMany
    @JoinTable(name = "PROJECTMEMBER", joinColumns = @JoinColumn(name = "PROJID"), inverseJoinColumns = @JoinColumn(name = "EMPID"))
    private Set<Project> projects = new HashSet<Project>();


    public Employee() {
    }

    public String getEmpId() {
        return empId;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Department getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(Department deptCode) {
        this.deptCode = deptCode;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getNameTitle() {
        return NameTitle;
    }

    public void setNameTitle(String nameTitle) {
        NameTitle = nameTitle;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.empId, this.givenName);
    }

}
