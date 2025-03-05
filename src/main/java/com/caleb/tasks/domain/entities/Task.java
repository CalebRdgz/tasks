package com.caleb.tasks.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
//To mark this class as a JPA entity, use @Entity
//Because we're using AutoDDL to setup database schema for us, use @Table to create name for table
@Entity
@Table(name = "com/caleb/tasks")
public class Task {
    //Create the instance variables we'll map to the columns in the database for this class:
    @Id //in an entity, mark the ID of that entity with @Id
    @GeneratedValue(strategy = GenerationType.UUID) //JPA auto generates UUID with this annotation
    @Column (name = "id", updatable = false, nullable = false)//specify the name of the column in the table
    private UUID id; //UUID type, calling it "id"

    //title:
    @Column(name = "title", nullable = false) //always want task with a "title" and can't be null (updatable = true by default)
    private String title; //String type

    //description:
    @Column(name = "description") //description is optional (nullable = true by default)
    private String description; //String type

    //due date:
    @Column(name = "due_date")
    private LocalDateTime dueDate;

    //use those enums we created:
    @Column(name = "status", nullable = false) //nullable = false cus we always want a status
    private TaskStatus status;

    //instance variable for task priority enum:
    @Column(name = "priority", nullable = false) //always want a task to have a priority
    private TaskPriority priority;

    //create a new instance variable of type TaskList named "taskList":
    @ManyToOne(fetch = FetchType.LAZY) //taskList wont be loaded in database until it's needed
    @JoinColumn(name = "task_list_id") //specifies the foreign key column name in the Tasks table. It will be the column in the Tasks table that contains the id of the TaskList to which the Task belongs.
    private TaskList taskList;

    //multiple instance variables for order information:
    @Column(name = "created", nullable = false)
    private LocalDateTime created; //when the entity is created in our database
    //LocalDateTime is the modern Java way to handle dates and times. Supported by JPA and JSON serialization

    //the last time this entity was updated:
    @Column(name = "updated", nullable = false)
    private LocalDateTime updated; //when a task is created, created amd updated will be the same

    public Task() {
    }

    public Task(UUID id, String title, String description, LocalDateTime dueDate, TaskStatus status, TaskPriority priority, TaskList taskList, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.priority = priority;
        this.taskList = taskList;
        this.created = created;
        this.updated = updated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate) && status == task.status && priority == task.priority && Objects.equals(taskList, task.taskList) && Objects.equals(created, task.created) && Objects.equals(updated, task.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, dueDate, status, priority, taskList, created, updated);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", status=" + status +
                ", priority=" + priority +
                ", taskList=" + taskList +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
