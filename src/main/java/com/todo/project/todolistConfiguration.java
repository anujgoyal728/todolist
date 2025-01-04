package com.todo.project;
import io.dropwizard.db.DataSourceFactory;

import io.dropwizard.Configuration;
import io.dropwizard.hibernate.HibernateBundle;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import java.util.Objects;

public class   todolistConfiguration extends Configuration {

    private DataSourceFactory database;

    @JsonProperty("database")
    public DataSourceFactory getDatabase() {
        if(Objects.isNull(database)){
            database = new DataSourceFactory();
        }
        return database;
    }

    @JsonProperty("database")
    public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }
}
