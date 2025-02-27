package com.caleb.tasks.repositories;

import com.caleb.tasks.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository //@Repository annotation because this is a Repository
public interface TaskRepository extends JpaRepository<Task, UUID> { //extend the parent repository JpaRepository
    //custom queries for our task by defining two methods in this interface:
    List<Task> findByTaskListId(UUID taskListId); //Returns a list of Task, named "findByTaskListId", specify single argument of UUID called "taskListId".

    //If it finds a Task that matches, return it. otherwise get an optional entity
    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID id); //looks for a single task where the tasklistid and the taskid matches.
    //By adding methods with a particular naming structure inside a repository, Spring will work out what the query should be for us.
}
