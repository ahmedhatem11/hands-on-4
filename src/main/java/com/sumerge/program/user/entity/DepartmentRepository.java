package com.sumerge.program.user.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@Stateless
public class DepartmentRepository {
    private static final Logger LOGGER = Logger.getLogger(DepartmentRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public Department getDepartment(String deptCode){
        try {
            return em.find(Department.class, deptCode);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public List<Department> getAllDepartments() {
        LOGGER.info("Fetching departments list");
        try {
            return em.createNamedQuery("Department.findAll", Department.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void addDepartment(Department department) {
        LOGGER.info("Saving new department " + department);
        try {
            em.persist(department);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void updateDepartment(Department department) {
        try {
            em.merge(department);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

    public void deleteDepartment(Department department){
        try {
            if (!em.contains(department)) {
                department = em.merge(department);
            }
            em.remove(department);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }
}
