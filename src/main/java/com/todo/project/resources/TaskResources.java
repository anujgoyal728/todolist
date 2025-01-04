package com.todo.project.resources;

import com.todo.project.core.Task;
import com.todo.project.db.TaskDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.* ;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResources {

    private final TaskDAO taskDAO;

    public TaskResources(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @POST
    @UnitOfWork
    public Task createTask(Task task) {
        return taskDAO.create(task);
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Task getTask(@PathParam("id") Long id) {
        return taskDAO.findById(id).orElseThrow(() -> new NotFoundException("Task not found"));
    }

    @GET
    @UnitOfWork
    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Task updateTask(@PathParam("id") Long id, Task updatedTask) {
        Optional<Task> task = taskDAO.findById(id);
        if (task.isEmpty()) {
            throw new NotFoundException("Task not found");
        }
        Task existingTask = task.get();
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStartDate(updatedTask.getStartDate());
        existingTask.setTargetDate(updatedTask.getTargetDate());
        existingTask.setStatus(updatedTask.getStatus());
        return taskDAO.create(existingTask);
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response deleteTask(@PathParam("id") Long id) {
        Optional<Task> task = taskDAO.findById(id);
        if (task.isEmpty()) {
            throw new NotFoundException("Task not found");
        }
        taskDAO.delete(task.get());
        return Response.noContent().build();
    }
}
