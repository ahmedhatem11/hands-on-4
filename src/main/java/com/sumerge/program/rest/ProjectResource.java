package com.sumerge.program.rest;

import com.sumerge.program.user.entity.EmployeeRepository;
import com.sumerge.program.user.entity.Project;
import com.sumerge.program.user.entity.ProjectRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/project")
@RequestScoped
public class ProjectResource {
    private static final Logger LOGGER = Logger.getLogger(ProjectResource.class.getName());

    @EJB
    ProjectRepository repo;

    @EJB
    private EmployeeRepository emrepo;

    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response getProject(@PathParam("id") String id) {
        try {
            return Response.ok().
                    entity(repo.getProject(id)).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllProjects() {
        try {
            return Response.ok().
                    entity(repo.getAllProjects()).
                    build();
        } catch (Exception e) {
            return Response.serverError().
                    entity(e).
                    build();
        }
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response addProject(Project project) {
        try {
                repo.addProject(project);
                return Response.ok().
                        build();

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @PUT
    @Consumes(APPLICATION_JSON)
    public Response updateProject(Project project) {
        try {
            Project originalProject = repo.getProject(project.getProjId());
            if(originalProject != null){


                if(project.getProjName() == null)
                    project.setProjName(originalProject.getProjName());

                if(project.getStartDate() == null)
                    project.setStartDate(originalProject.getStartDate());

                if(project.getTargetDate() == null)
                    project.setTargetDate(originalProject.getTargetDate());

                if(project.getStatus() == null)
                    project.setStatus(originalProject.getStatus());

                if(project.getDescription() == null)
                    project.setDescription(originalProject.getDescription());

                if(project.getEmployees() == null)
                    project.setEmployees(originalProject.getEmployees());

                repo.updateProject(project);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No project available with such ID").build();
            }
        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deletePhoto(@PathParam("id") String id) {
        try {
            Project project = repo.getProject(id);
            if(project != null){
                repo.deleteProject(project);
                return Response.ok().
                        build();
            }
            else{
                return Response.serverError().entity("No project available with such ID").build();
            }

        } catch (Exception e) {
            return Response.serverError().
                    entity(e.getMessage()).
                    build();
        }
    }
}
