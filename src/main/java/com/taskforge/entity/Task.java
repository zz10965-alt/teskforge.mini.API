package com.taskforge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

//id userId title description status priority dueDate aiAssisted
@Entity
@Table(name="tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private Long userId;

    @Column(nullable=false)
    @Size(min=1,max=140)
    private String title;

    @Size(max=10000)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TaskStatus status=TaskStatus.TODO;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private TaskPriority priority=TaskPriority.MEDIUM;

    private LocalDate dueDate;

    @Column(nullable=false)
    private boolean aiAssisted=false;

    //构造函数
    public Task(){}
    public Task(Long userId, String title, String description,
                TaskStatus status, TaskPriority priority,LocalDate dueDate) {
        this.userId = userId;
        this.title = title;
        this.description=description;
        this.status=status;
        this.priority=priority;
        this.dueDate=dueDate;
        this.aiAssisted=false;
    }
    public Boolean getAiAssisted() {
        return aiAssisted;
    }
}
