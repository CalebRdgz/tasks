package com.caleb.tasks.services;

import com.caleb.tasks.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists(); //interface only needs to define this method for now
    //returns a task list with the TaskList passed in (TaskList taskList):
    TaskList createTaskList(TaskList taskList);
}
