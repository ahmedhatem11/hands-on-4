package com.sumerge.program.rest;

import com.sumerge.program.user.entity.Address;
import com.sumerge.program.user.entity.AddressRepository;
import com.sumerge.program.user.entity.EmployeeRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/address")
@RequestScoped
public class AddressResource {
    private static final Logger LOGGER = Logger.getLogger(AddressResource.class.getName());

    @EJB
    private AddressRepository repo;
    @EJB
    private EmployeeRepository emrepo;

    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getAddress(@PathParam("id") int id) {
        try {
            return Response.ok().
                    entity(repo.getAddress(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllAddresses() {
        try {
            return Response.ok().
                    entity(repo.getAllAddresses()).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response addAddress(Address address) {
        try {
            if(repo.getAddress(address.getAdressID()) == null){
                if (emrepo.getEmployee(address.getEmployee().getEmpId()) !=null){
                    address.setEmployee(emrepo.getEmployee(address.getEmployee().getEmpId()));
                }
                repo.addAddress(address);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("There is already an address available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updateAddress(Address address) {
        try {
            Address originalAddress = repo.getAddress(address.getAdressID());
            if(originalAddress != null){


                if(address.getAddressLine1() == null)
                    address.setAddressLine1(originalAddress.getAddressLine1());

                if(address.getAddressLine2() == null)
                    address.setAddressLine2(originalAddress.getAddressLine2());

                if(address.getCity() == null)
                    address.setCity(originalAddress.getCity());

                if(address.getRegion() == null)
                    address.setRegion(originalAddress.getRegion());

                if(address.getCountry() == null)
                    address.setCountry(originalAddress.getCountry());

                if(address.getPostCode() == null)
                    address.setPostCode(originalAddress.getPostCode());

                if(address.getEmployee() == null)
                    address.setEmployee(originalAddress.getEmployee());

                repo.updateAddress(address);
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
    public Response deleteAddress(@PathParam("id") int id) {
        try {
            Address address = repo.getAddress(id);
            if(address != null){
                repo.deleteAddress(address);
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


}
