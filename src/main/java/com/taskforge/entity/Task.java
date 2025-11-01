package com.teskforge.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

//id userId title description status priority dueDate aiAssisted
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private long userId;

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
    public Task(long userId, String title, String description,
                TaskStatus status, TaskPriority priority,LocalDate dueDate) {
        this.userId = userId;
        this.title = title;
        this.description=description;
        this.status=status;
        this.priority=priority;
        this.dueDate=dueDate;
        this.aiAssisted=false;
    }

    //getter and setter
    public long getId(){return id;}
    public void setId(long id){this.id=id;}

    public long getUserId(){return userId;}
    public void setUserId(long userId){this.userId=userId;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description=description;}

    public TaskStatus getStatus(){return status;}
    public void setStatus(TaskStatus status){this.status=status;}

    public TaskPriority getPriority(){return priority;}
    public void setPriority(TaskPriority priority){this.priority=priority;}

    public LocalDate getDueDate(){return dueDate;}
    public void setDueDate(LocalDate dueDate){this.dueDate=dueDate;}

    public boolean getAiAssisted(){return aiAssisted;}
    public void setAiAssited(boolean aiAssisted){this.aiAssisted=aiAssisted;}



}
