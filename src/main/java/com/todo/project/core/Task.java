package com.todo.project.core;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
@NamedQueries({
        @NamedQuery(
                name = "Task.findAll",
                query = "SELECT t FROM Task t"
        )
})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "target_date", nullable = false)
    private Date targetDate;

    @Column(nullable = false)
    private String status = "TODO";

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getTargetDate() { return targetDate; }
    public void setTargetDate(Date targetDate) { this.targetDate = targetDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
