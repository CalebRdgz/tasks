package com.caleb.tasks.mappers.impl;

import com.caleb.tasks.domain.dto.TaskListDto;
import com.caleb.tasks.domain.entities.Task;
import com.caleb.tasks.domain.entities.TaskList;
import com.caleb.tasks.domain.entities.TaskStatus;
import com.caleb.tasks.mappers.TaskListMapper;
import com.caleb.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component //@Component to declare TaskListMapperImpl as a Bean. Allows dependencies to be injected.
public class TaskListMapperImpl implements TaskListMapper {
    //inject TaskMapper into this class to convert from a TaskDto to a Task:
    private final TaskMapper taskMapper;

    //through this constructor that our TaskMapper will be injected and available to be used:
    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return new TaskList(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks()) //deals with the list of tasks. ofNullable handles the case where it could be null
                        .map(tasks -> tasks.stream() //if NOT null, call tasks.steam().map()
                                .map(taskMapper::fromDto) //taking that list from List<TaskDto> tasks and converted each one from a Dto into a Task
                                .toList() //convert .stream() into a list. //return null if TaskList is null
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return new TaskListDto(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(), //calculate the count (how many tasks are linked to a TaskList):
                Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0), //If TaskList is null, return 0
                calculateTaskListProgress(taskList.getTasks()), //To get the progress value

                //list of task Dtos:
                Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> //if we do have a list of tasks:
                                tasks.stream().map(taskMapper::toDto).toList() //convert the tasks to Dto
                        ).orElse(null) //return null if Optional.ofNullable is null
        );
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks){
            return null;
        }

        //gives us the count of all the tasks in our list that are CLOSED with the closed status.
        long closedTaskCount = tasks.stream().filter(task ->
                TaskStatus.CLOSED == task.getStatus()
        ).count();

        //calculate the progress value:
        return (double) closedTaskCount / tasks.size();
    }
}
