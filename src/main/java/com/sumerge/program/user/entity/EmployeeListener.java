package com.sumerge.program.user.entity;


import javax.persistence.PrePersist;

public class EmployeeListener {

    @PrePersist
    public void prePersist(Employee employee){
        System.out.println("Adding new Employee " + employee.getEmpId());

    }
}
