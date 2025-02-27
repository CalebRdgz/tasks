package com.caleb.tasks.mappers.impl;

import com.caleb.tasks.domain.dto.TaskDto;
import com.caleb.tasks.domain.entities.Task;
import com.caleb.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

//inject this class throughout our application where it's needed. Mark it as a bean with @Component:
@Component //@component marks this class as a bean. Injects any dependencies we declare for this class (not any rn) Also makes this class a candidate for injection wherever it's declared in this application.
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        //implement the fromDto method with the entities we created in Task
        return new Task(
                taskDto.id(), //.id() is a method and convention used by Java records
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        //implement the TaskDto method:
        //Map the other way:
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
