package com.sumerge.program.rest;

import com.sumerge.program.user.entity.Employee;
import com.sumerge.program.user.entity.EmployeeRepository;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/employees")
@RequestScoped
public class EmployeeResource
{
    private static final Logger LOGGER = Logger.getLogger(EmployeeResource.class.getName());

    @Context
    private SecurityContext securityContext;

    @EJB
    private EmployeeRepository repo;

    @GET
    @Path("id/{id}")
    @Produces(APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") String id) {
        try {
            return Response.ok().
                    entity(repo.getEmployee(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Path("name/{name}")
    @Produces(APPLICATION_JSON)
    public Response getEmployeeByName(@PathParam("name") String name) {
        try {
            return Response.ok().
                    entity(repo.getEmployeeByName(name)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Path("{id}/projects")
    @Produces(APPLICATION_JSON)
    public Response getEmployeeProjects(@PathParam("id") String id) {
        try {
            return Response.ok().
                    entity(repo.getProjects(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllEmployees() {
		try {
			return Response.ok().
					entity(repo.getAllEmployees()).
					build();
		} catch (Exception e) {
			return Response.serverError().
					entity(e).
					build();
		}
    }



    @POST
    @Consumes(APPLICATION_JSON)
    public Response insertEmployee(Employee employee) {
		try {
            if(repo.getEmployee(employee.getEmpId()) == null){
                repo.addEmployee(employee);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("There is already an employee available with such ID").build();
            }

		} catch (Exception e) {
			return Response.serverError().
					entity(e.getMessage()).
					build();
		}
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updateEmployee(Employee employee) {
        try {
            Employee originalEmployee = repo.getEmployee(employee.getEmpId());
            if(originalEmployee != null){

                if(employee.getDeptCode() == null)
                    employee.setDeptCode(originalEmployee.getDeptCode());

                if(employee.getJobTitle() == null)
                    employee.setJobTitle(originalEmployee.getJobTitle());

                if(employee.getGivenName() == null)
                    employee.setGivenName(originalEmployee.getGivenName());

                if(employee.getFamilyName() == null)
                    employee.setFamilyName(originalEmployee.getFamilyName());

                if(employee.getCommonName() == null)
                    employee.setCommonName(originalEmployee.getCommonName());

                if(employee.getNameTitle() == null)
                    employee.setNameTitle(originalEmployee.getNameTitle());

                if(employee.getProjects() == null)
                    employee.setProjects(originalEmployee.getProjects());

                repo.updateEmployee(employee);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No employee available with such ID").build();
            }
        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") String id) {
        try {
            Employee employee = repo.getEmployee(id);
            if(employee != null){
                repo.deleteEmployee(employee);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No employee available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

}
