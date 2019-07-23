package com.sumerge.program.rest;


import com.sumerge.program.user.entity.PhoneNumber;
import com.sumerge.program.user.entity.PhoneNumberRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/phones")
@RequestScoped
public class PhoneNumberResource {

    private static final Logger LOGGER = Logger.getLogger(PhoneNumberResource.class.getName());

    @EJB
    private PhoneNumberRepository repo;


    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getPhoneNumber(@PathParam("id") int id) {
        try {
            return Response.ok().
                    entity(repo.getPhoneNumber(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllPhones() {
        try {
            return Response.ok().
                    entity(repo.getAllPhones()).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response insertPhoneNumber(PhoneNumber phoneNumber) {
        try {
            if(repo.getPhoneNumber(phoneNumber.getPhoneID()) == null){
                repo.addPhoneNumber(phoneNumber);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("There is already a phone number available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updatePhoneNumber(PhoneNumber phoneNumber) {
        try {
            PhoneNumber originalPhoneNumber = repo.getPhoneNumber(phoneNumber.getPhoneID());
            if(originalPhoneNumber != null){


                if(phoneNumber.getIntlPrefix() == null)
                    phoneNumber.setIntlPrefix(originalPhoneNumber.getIntlPrefix());

                if(phoneNumber.getLocalNum() == null)
                    phoneNumber.setLocalNum(originalPhoneNumber.getLocalNum());

                if(phoneNumber.getPhoneType() == null)
                    phoneNumber.setPhoneType(originalPhoneNumber.getPhoneType());

                if(phoneNumber.getEmployee() == null)
                    phoneNumber.setEmployee(originalPhoneNumber.getEmployee());

                repo.updatePhoneNumber(phoneNumber);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No Phone Number available with such ID").build();
            }
        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePhoneNumber(@PathParam("id") int id) {
        try {
            PhoneNumber phoneNumber = repo.getPhoneNumber(id);
            if(phoneNumber != null){
                repo.deletePhoneNumber(phoneNumber);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No department available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }


}
