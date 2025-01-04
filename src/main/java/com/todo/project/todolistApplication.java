package com.todo.project;

import com.todo.project.core.Task;
import com.todo.project.db.TaskDAO;
import com.todo.project.health.DatabaseHealthCheck;
import com.todo.project.resources.TaskResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.SessionFactory;

public class todolistApplication extends Application<todolistConfiguration>{

    public static void main(final String[] args) throws Exception {
        new todolistApplication().run(args);
    }

    @Override
    public String getName() {
        return "todolist";
    }

    @Override
    public void run(final todolistConfiguration configuration,
                    final Environment environment) throws Exception{
        //hibernateBundle.run(configuration, environment);
        final SessionFactory sessionFactory = hibernateBundle.getSessionFactory();
        if (sessionFactory == null) {
            throw new IllegalStateException("SessionFactory is null!");
        }
        final TaskDAO taskDAO = new TaskDAO(hibernateBundle.getSessionFactory());
        environment.jersey().register(new TaskResources(taskDAO));
        System.out.println("TaskResource registered successfully!");
        // Register the database health check
        environment.healthChecks().register("database", new DatabaseHealthCheck(sessionFactory));

    }
    HibernateBundle<todolistConfiguration> hibernateBundle = new HibernateBundle<todolistConfiguration>(Task.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(todolistConfiguration configuration) {
                    System.out.println("Database");
                    return configuration.getDatabase();
                }
            };

    @Override
    public void initialize(final Bootstrap<todolistConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernateBundle);
    }

}

