package com.caleb.tasks.services.impl;

import com.caleb.tasks.domain.entities.TaskList;
import com.caleb.tasks.repositories.TaskListRepository;
import com.caleb.tasks.services.TaskListService;
import org.springframework.stereotype.Service;

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
}
