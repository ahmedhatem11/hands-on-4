package com.sumerge.program.rest;

import com.sumerge.program.user.entity.EmployeeRepository;
import com.sumerge.program.user.entity.Photo;
import com.sumerge.program.user.entity.PhotoRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/photos")
@RequestScoped
public class PhotoResource {
    private static final Logger LOGGER = Logger.getLogger(PhotoResource.class.getName());

    @EJB
    PhotoRepository repo;

    @EJB
    private EmployeeRepository emrepo;

    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getPhoto(@PathParam("id") int id) {
        try {
            return Response.ok().
                    entity(repo.getPhoto(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllPhotos() {
        try {
            return Response.ok().
                    entity(repo.getAllPhotos()).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response addPhoto(Photo photo) {
        try {
            if(repo.getPhoto(photo.getPhotoId()) == null){
                if (emrepo.getEmployee(photo.getEmployee().getEmpId()) !=null){
                    photo.setEmployee(emrepo.getEmployee(photo.getEmployee().getEmpId()));
                }
                repo.addPhoto(photo);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("There is already a photo available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updatePhoto(Photo photo) {
        try {
            Photo originalPhoto = repo.getPhoto(photo.getPhotoId());
            if(originalPhoto != null){


                if(photo.getImageName() == null)
                    photo.setImageName(originalPhoto.getImageName());

                if(photo.getEmployee() == null)
                    photo.setEmployee(originalPhoto.getEmployee());

                repo.updatePhoto(photo);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No photo available with such ID").build();
            }
        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePhoto(@PathParam("id") int id) {
        try {
            Photo photo = repo.getPhoto(id);
            if(photo != null){
                repo.deletePhoto(photo);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No photo available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }
}
