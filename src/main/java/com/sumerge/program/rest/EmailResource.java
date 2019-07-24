package com.sumerge.program.rest;

import com.sumerge.program.user.entity.Email;
import com.sumerge.program.user.entity.EmailRepository;
import com.sumerge.program.user.entity.EmployeeRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/emails")
@RequestScoped
public class EmailResource {
    private static final Logger LOGGER = Logger.getLogger(EmailResource.class.getName());

    @EJB
    EmailRepository repo;

    @EJB
    private EmployeeRepository emrepo;

    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getEmail(@PathParam("id") int id) {
        try {
            return Response.ok().
                    entity(repo.getEmail(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllEmails() {
        try {
            return Response.ok().
                    entity(repo.getAllEmails()).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response addAddress(Email email) {
        try {
            if(repo.getEmail(email.getEmailId()) == null){
                if (emrepo.getEmployee(email.getEmployee().getEmpId()) !=null){
                    email.setEmployee(emrepo.getEmployee(email.getEmployee().getEmpId()));
                }
                repo.addEmail(email);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("There is already an email available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updateEmail(Email email) {
        try {
            Email originalEmail = repo.getEmail(email.getEmailId());
            if(originalEmail != null){


                if(email.getEmailAdress() == null)
                    email.setEmailAdress(originalEmail.getEmailAdress());

                if(email.getEmailType() == null)
                    email.setEmailType(originalEmail.getEmailType());

                if(email.getEmployee() == null)
                    email.setEmployee(originalEmail.getEmployee());

                repo.updateEmail(email);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No address available with such ID").build();
            }
        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmail(@PathParam("id") int id) {
        try {
            Email email = repo.getEmail(id);
            if(email != null){
                repo.deleteEmail(email);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No Email available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }
}
