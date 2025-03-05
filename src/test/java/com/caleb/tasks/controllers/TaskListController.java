package com.caleb.tasks.controllers;

import com.caleb.tasks.domain.dto.TaskListDto;
import com.caleb.tasks.mappers.TaskListMapper;
import com.caleb.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //RestController annotation marks this class as a bean and Injects any dependencies that we declare
@RequestMapping(path = "/task-lists") //Path this controller is going to operate on.
public class TaskListController {
    //dependencies that we declare to be injected:
    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping //GET Method
    public List<TaskListDto> listTaskLists() { //this method responds when we go a GET call to /task-lists
        //Get a list of task-lists and then convert them into a list of Task list Dtos

        //Use our dependencies to get a list of tasklists:
        return taskListService.listTaskLists() //convert this list of taskLists into a list of taskList Dtos
                .stream().map(taskListMapper::toDto) //use the taskListMapper function and convert taskList to Dto
                .toList(); //convert the stream() back into a list.
    }

}
