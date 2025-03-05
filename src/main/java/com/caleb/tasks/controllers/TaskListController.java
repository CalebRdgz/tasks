package com.caleb.tasks.controllers;

import com.caleb.tasks.domain.dto.TaskListDto;
import com.caleb.tasks.domain.entities.TaskList;
import com.caleb.tasks.mappers.TaskListMapper;
import com.caleb.tasks.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //RestController annotation marks this class as a bean and Injects any dependencies that we declare
@RequestMapping(path = "/task-lists") //endpoint Path this controller is going to operate on.
public class TaskListController {
    //dependencies that we declare to be injected:
    private final TaskListService taskListService; //gives us a list of taskLists
    private final TaskListMapper taskListMapper; //convert that taskLists into a list of TaskList Dtos before returning it to the caller

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping //GET Method. http GET request to /task-lists
    public List<TaskListDto> listTaskLists() { //this method responds when we go a GET call to /task-lists
        //Get a list of task-lists and then convert them into a list of Task list Dtos

        //Use our dependencies to get a list of tasklists:
        return taskListService.listTaskLists() //convert this list of taskLists into a list of taskList Dtos
                .stream().map(taskListMapper::toDto) //use the taskListMapper function and convert taskList to Dto
                .toList(); //convert the stream() back into a list.
    }

    @PostMapping //POST Method.
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) { //frontend sends in the body of the request a representation of a  taskListDto, this will allow us to access and use that in our method.
        //Create our Task List:
        TaskList createdTaskList = taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        //This method requires a Task List Dto returned:
        return taskListMapper.toDto(createdTaskList);
    }


}
