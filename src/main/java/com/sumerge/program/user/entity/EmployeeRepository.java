package com.sumerge.program.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static java.util.logging.Level.SEVERE;

@Stateless
public class EmployeeRepository {

	private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

	@PersistenceContext
	private EntityManager em;

	public Employee getEmployee(String empId) {
		LOGGER.info("Fetching employees list");
		try {
			return em.find(Employee.class, empId);
		} catch (Exception e) {
			LOGGER.log(SEVERE, e.getMessage(), e);
			throw e;
		}
	}

	public List<Employee> getAllEmployees() {
		LOGGER.info("Fetching employees list");
		try {
			return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
		} catch (Exception e) {
			LOGGER.log(SEVERE, e.getMessage(), e);
			throw e;
		}
	}

	public void addEmployee(Employee employee) {
		LOGGER.info("Saving new employee " + employee);
		try {
			em.persist(employee);
		} catch (Exception e) {
			LOGGER.log(SEVERE, e.getMessage(), e);
			throw e;
		}
	}

	public void updateEmployee(Employee employee) {
		LOGGER.info("Updating employee " + employee);
		try {
			em.merge(employee);
		} catch (Exception e) {
			LOGGER.log(SEVERE, e.getMessage(), e);

		}
	}

	public void deleteEmployee(Employee employee) {
		try {
			if (!em.contains(employee)) {
				employee = em.merge(employee);
			}
			em.remove(employee);
		} catch (Exception e) {
			LOGGER.log(SEVERE, e.getMessage(), e);

		}
	}

	public List<Project> getProjects(String id) {
		try {
			List<Project> projects = em.createNamedQuery("Project.findAll", Project.class).getResultList();
			List<Project> resultProjects = new ArrayList<>();

			for (Project project : projects) {
				List<Employee> employees = project.getEmployees();
				for (Employee employee : employees) {
					if (id.equals(employee.getEmpId())) {
						resultProjects.add(project);
					}
				}

			}
			return resultProjects;

		} catch (Exception e) {
			LOGGER.log(SEVERE, e.getMessage(), e);
			throw e;
		}
	}


}
