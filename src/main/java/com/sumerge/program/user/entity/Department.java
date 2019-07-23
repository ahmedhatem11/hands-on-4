package com.sumerge.program.user.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "DEPARTMENT", schema = "STAFF")
@NamedQueries({
        @NamedQuery(name = "Department.findAll", query = "SELECT e FROM Department e")
})
@XmlRootElement
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DEPTCODE", nullable = false)
    private String deptCode;

    @Column(name = "DEPTNAME", nullable = false)
    private String deptName;

    @Column(name = "MANAGER")
    private String manager;

    public Department() {
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deptCode, this.deptName);
    }
}
