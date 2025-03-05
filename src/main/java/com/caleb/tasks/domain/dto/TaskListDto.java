package com.caleb.tasks.domain.dto;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id, //UUID type
        String title,//title is String type
        String description, //description is String type
        Integer count, //expose the count of tasks in our TaskList
        Double progress, //represents how many tasks in our TaskList is completed using a number between 0 and 1.
        List<TaskDto> tasks //expose a list of TaskDto called "tasks"
) {
}
