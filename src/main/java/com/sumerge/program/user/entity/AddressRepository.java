package com.sumerge.program.user.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@Stateless
public class AddressRepository {
    private static final Logger LOGGER = Logger.getLogger(AddressRepository.class.getName());


    @PersistenceContext
    private EntityManager em;

    public Address getAddress(int id){
        try {
            return em.find(Address.class, id);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public List<Address> getAllAddresses() {
        try {
            return em.createNamedQuery("Address.findAll", Address.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void addAddress(Address address) {
        try {
            em.persist(address);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void updateAddress(Address address) {
        try {
            em.merge(address);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

    public void deleteAddress(Address address){
        try {
            if (!em.contains(address)) {
                address = em.merge(address);
            }
            em.remove(address);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }
}
