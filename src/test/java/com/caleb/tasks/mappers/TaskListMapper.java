package com.caleb.tasks.mappers;

import com.caleb.tasks.domain.dto.TaskListDto;
import com.caleb.tasks.domain.entities.TaskList;

public interface TaskListMapper {

    TaskList fromDto(TaskListDto taskListDto); //"fromDto" method takes in a "TaskListDto" and returns a "TaskList"

    TaskListDto toDto(TaskList taskList);// "toDTO' method takes in a "TaskList" and returns a "TaskListDto"


}
