package com.caleb.tasks.mappers;

import com.caleb.tasks.domain.dto.TaskDto;
import com.caleb.tasks.domain.entities.Task;

public interface TaskMapper {
    //Use an interface because it's better practice to code to interfaces rather than concrete classes.

    //define the contract of this interface:
    Task fromDto(TaskDto taskDto); //method "fromDto" takes a "taskDto" and returns a "Task"

    //second method:
    TaskDto toDto(Task task); //"toDto" takes a "task" and returns a "TaskDto"
}
