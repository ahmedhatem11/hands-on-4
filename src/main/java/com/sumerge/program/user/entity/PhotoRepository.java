package com.sumerge.program.user.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

@Stateless
public class PhotoRepository {
    private static final Logger LOGGER = Logger.getLogger(PhotoRepository.class.getName());

    @PersistenceContext
    private EntityManager em;

    public Photo getPhoto(int id){
        try {
            return em.find(Photo.class, id);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public List<Photo> getAllPhotos() {
        try {
            return em.createNamedQuery("Photo.findAll", Photo.class).getResultList();
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void addPhoto(Photo photo) {
        try {
            em.persist(photo);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);
            throw e;
        }
    }

    public void updatePhoto(Photo photo) {
        try {
            em.merge(photo);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

    public void deletePhoto(Photo photo){
        try {
            if (!em.contains(photo)) {
                photo = em.merge(photo);
            }
            em.remove(photo);
        } catch (Exception e) {
            LOGGER.log(SEVERE, e.getMessage(), e);

        }
    }

}
