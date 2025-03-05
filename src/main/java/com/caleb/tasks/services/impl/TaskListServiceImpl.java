package com.caleb.tasks.services.impl;

import com.caleb.tasks.domain.entities.TaskList;
import com.caleb.tasks.repositories.TaskListRepository;
import com.caleb.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service //annotation declaring this class as a bean. Inject any dependencies that we declare.
public class TaskListServiceImpl implements TaskListService {

    //declare a dependency:
    private final TaskListRepository taskListRepository;

    //constructor injects our task list repository^ ready to use in this class.
    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll(); //returns a list of tasklists every single task list in our database
    }

    @Override //This method is used exclusively to create Task Lists
    public TaskList createTaskList(TaskList taskList) { //Any task list passed into this class MUST have a NULL id. Must be a new task List. Will recieve an id when created.
        //Validate that new taskList id is NULL:
        if (null != taskList.getId()) {
            throw new IllegalArgumentException("Task list already has an ID!");
        }
        //make sure that our taskList has a title and is NOT null:
        if (null == taskList.getTitle() || taskList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Task list title must be present!");
        }

        LocalDateTime now = LocalDateTime.now(); //created and updated will be the same time
        //use our TaskListRepository to create a new TaskList instance (no unexpected data gets persisted in the database):
        //this taskList is saved to the database
        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                null,
                now,
                now
        ));
    }
}
