package com.taskforge.controller;

import com.taskforge.dto.TaskRequest;
import com.taskforge.dto.TaskResponse;
import com.taskforge.entity.TaskPriority;
import com.taskforge.entity.TaskStatus;
import com.taskforge.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    //获取用户ID（默认1）
    private Long getUserId(Long userId){
        return userId!=null?userId:1L;
    }
    //1.任务创建
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(
            @Valid @RequestBody TaskRequest request,
            @RequestHeader(value="X-User-Id",required = false)Long userId){
        TaskResponse response=taskService.createTask(request,getUserId(userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //2.获取任务列表
    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getTasks(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TaskResponse> tasks = taskService.getTasks(getUserId(userId), status, priority, pageable);
        return ResponseEntity.ok(tasks);
    }
    // 3. 获取单个任务
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        return taskService.getTaskById(id, getUserId(userId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. 更新任务
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request,
            @RequestHeader(value = "X-User-Id", required = false) Long userId) {
        return taskService.updateTask(id, request, getUserId(userId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    }
