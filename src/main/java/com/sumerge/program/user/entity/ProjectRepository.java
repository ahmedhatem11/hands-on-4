package com.sumerge.program.user.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@Stateless
public class ProjectRepository {
    private static final Logger LOGGER = Logger.getLogger(ProjectRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public Project getProject(String id){
        try {
            return em.find(Project.class, id);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public List<Project> getAllProjects() {
        try {
            return em.createNamedQuery("Project.findAll", Project.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void addProject(Project project) {
        try {
            em.persist(project);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void updateProject(Project project) {
        try {
            em.merge(project);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

    public void deleteProject(Project project){
        try {
            if (!em.contains(project)) {
                project = em.merge(project);
            }
            em.remove(project);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }
}
