package com.caleb.tasks.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task_lists")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    //title and description instance variables:
    @Column(name = "title", nullable = false) //always want a title provided
    private String title;

    @Column(name = "description") //optional, nullable = true is default
    private String description;

    //represents one task list to many tasks:
    @OneToMany(mappedBy = "taskList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}) // mappedBy = look for the instance variable "taskList" on the Task class for more info about this relationship
    private List<Task> tasks; //CascadeType.REMOVE = when delete a task list, all tasks will be deleted too. CascadeType.PERSIST = when taskList saved, all tasks saved too.
    //Bi-Directional Relationship: When we load TaskList, we can access its tasks. When we load a Task, we can access its TaskList.

    //specify the created and updated instance variables:
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    //like the Task entity, generate constructors, getters/setters, equals() hashCode() toString()
    //we haven't referenced Task anywhere inside TaskList


    public TaskList() {
    }

    public TaskList(UUID id, String title, String description, List<Task> tasks, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tasks = tasks;
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
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
        TaskList taskList = (TaskList) o;
        return Objects.equals(id, taskList.id) && Objects.equals(title, taskList.title) && Objects.equals(description, taskList.description) && Objects.equals(tasks, taskList.tasks) && Objects.equals(created, taskList.created) && Objects.equals(updated, taskList.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tasks, created, updated);
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tasks=" + tasks +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
