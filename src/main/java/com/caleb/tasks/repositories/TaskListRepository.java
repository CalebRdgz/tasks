package com.caleb.tasks.repositories;

import com.caleb.tasks.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//For this interface, Spring Data JPA provides the implementation for us. Makes available methods: save(), findByID(), findAll(), deleteByID()...
@Repository //@Repository annotation because this interface is a repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> { //extending the parent JPARepository gives us CRUD behavior (CREATE, READ, UPDATE, DELETE)
}
