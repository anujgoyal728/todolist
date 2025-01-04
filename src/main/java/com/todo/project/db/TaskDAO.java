package com.todo.project.db;

import com.todo.project.core.Task;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class TaskDAO extends AbstractDAO<Task> {

    public TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Task create(Task task) {
        return persist(task);
    }

    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public List<Task> findAll() {
        return list(
                currentSession().createQuery("FROM Task", Task.class));
    }

    public void delete(Task task) {
        currentSession().delete(task);
    }
}
