package com.sumerge.program.user.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@Stateless
public class EmailRepository {
    private static final Logger LOGGER = Logger.getLogger(EmailRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public Email getEmail(int id){
        try {
            return em.find(Email.class, id);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public List<Email> getAllEmails() {
        try {
            return em.createNamedQuery("Email.findAll", Email.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void addEmail(Email email) {
        try {
            em.persist(email);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void updateEmail(Email email) {
        try {
            em.merge(email);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

    public void deleteEmail(Email email){
        try {
            if (!em.contains(email)) {
                email = em.merge(email);
            }
            em.remove(email);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

}
