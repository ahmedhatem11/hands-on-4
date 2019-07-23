package com.sumerge.program.rest;

import com.sumerge.program.rest.EmployeeResource;
import com.sumerge.program.user.entity.Department;
import com.sumerge.program.user.entity.DepartmentRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/departments")
@RequestScoped
public class DepartmentResource {
    private static final Logger LOGGER = Logger.getLogger(DepartmentResource.class.getName());

    @EJB
    private DepartmentRepository repo;


    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getDepartment(@PathParam("id") String id) {
        try {
            return Response.ok().
                    entity(repo.getDepartment(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllDepartments() {
        try {
            return Response.ok().
                    entity(repo.getAllDepartments()).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response insertDepartment(Department department) {
        try {
            if(repo.getDepartment(department.getDeptCode()) == null){
                repo.addDepartment(department);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("There is already a department available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updateDepartment(Department department) {
        try {
            Department originalDepartment = repo.getDepartment(department.getDeptCode());
            if(originalDepartment != null){


                if(department.getDeptName() == null)
                    department.setDeptName(originalDepartment.getDeptName());

                if(department.getManager() == null)
                    department.setManager(originalDepartment.getManager());

                repo.updateDepartment(department);
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

    @DELETE
    @Path("{id}")
    public Response deleteDepartment(@PathParam("id") String id) {
        try {
            Department department = repo.getDepartment(id);
            if(department != null){
                repo.deleteDepartment(department);
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
