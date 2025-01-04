package com.todo.project.health;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.SessionFactory;

public class DatabaseHealthCheck extends HealthCheck {
    private final SessionFactory sessionFactory;

    public DatabaseHealthCheck(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @UnitOfWork
    protected Result check() throws Exception {
        try {
            // Try opening a session and querying the database
            sessionFactory.getCurrentSession().createNativeQuery("SELECT 1").getSingleResult();
            return Result.healthy();
        } catch (Exception e) {
            return Result.unhealthy("Database is not reachable: " + e.getMessage());
        }
    }
}
