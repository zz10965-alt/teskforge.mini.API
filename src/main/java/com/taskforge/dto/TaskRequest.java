package com.taskforge.dto;

import com.taskforge.entity.TaskPriority;
import com.taskforge.entity.TaskStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    @Size(min=1,max=140,message="Title must be between a and 140 characters")
    private String title;

    @Size(max=10000,message="Description must not exceed 10000 characters")
    private String description;

    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;

}
