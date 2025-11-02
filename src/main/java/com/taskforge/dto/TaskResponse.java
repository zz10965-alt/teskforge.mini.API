package com.taskforge.dto;

import com.taskforge.entity.TaskPriority;
import com.taskforge.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponse {
    private long id;
    private long userId;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate dueDate;
    private Boolean aiAssisted;

    //带参数构造函数（给service）
    public TaskResponse (Long id,Long userId,String title, String description,
                         TaskStatus status, TaskPriority priority, LocalDate dueDate, Boolean aiAssisted){
        this.id=id;
        this.userId=userId;
        this.title=title;
        this.description=description;
        this.status=status;
        this.priority=priority;
        this.dueDate=dueDate;
        this.aiAssisted=aiAssisted;
    }

    public TaskResponse() {

    }
}
