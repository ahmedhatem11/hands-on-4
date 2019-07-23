package com.sumerge.program.user.entity;

import com.sumerge.program.rest.PhoneNumberResource;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@Stateless
public class PhoneNumberRepository {


    private static final Logger LOGGER = Logger.getLogger(PhoneNumberRepository.class.getName());

    @PersistenceContext
    private EntityManager em;


    public PhoneNumber getPhoneNumber(int phoneID){
        try {
            return em.find(PhoneNumber.class, phoneID);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public List<PhoneNumber> getAllPhones() {
        LOGGER.info("Fetching phones list");
        try {
            return em.createNamedQuery("PhoneNumber.findAll", PhoneNumber.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        LOGGER.info("Saving new phoneNumber " + phoneNumber);
        try {
            em.persist(phoneNumber);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        try {
            em.merge(phoneNumber);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

    public void deletePhoneNumber(PhoneNumber phoneNumber){
        try {
            if (!em.contains(phoneNumber)) {
                phoneNumber = em.merge(phoneNumber);
            }
            em.remove(phoneNumber);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }
}
