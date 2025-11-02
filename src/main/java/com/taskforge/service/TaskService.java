package com.taskforge.service;

import com.taskforge.dto.TaskRequest;
import com.taskforge.dto.TaskResponse;
import com.taskforge.entity.Task;
import com.taskforge.entity.TaskPriority;
import com.taskforge.entity.TaskStatus;
import com.taskforge.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    // 创建任务
    public TaskResponse createTask(TaskRequest request, Long userId) {
        Task task = new Task();
        task.setUserId(userId);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus() != null ? request.getStatus() : TaskStatus.TODO);
        task.setPriority(request.getPriority() != null ? request.getPriority() : TaskPriority.MEDIUM);
        task.setDueDate(request.getDueDate());

        Task savedTask = taskRepository.save(task);
        return toResponse(savedTask);
    }

    // 获取任务列表
    public Page<TaskResponse> getTasks(Long userId, TaskStatus status, TaskPriority priority, Pageable pageable) {
        Page<Task> tasks;

        if (status != null) {
            tasks = taskRepository.findByUserIdAndStatus(userId, status, pageable);
        } else if (priority != null) {
            tasks = taskRepository.findByUserIdAndPriority(userId, priority, pageable);
        } else {
            tasks = taskRepository.findByUserId(userId, pageable);
        }

        return tasks.map(this::toResponse);
    }

    // 获取单个任务
    public Optional<TaskResponse> getTaskById(Long id, Long userId) {
        return taskRepository.findById(id)
                .filter(task -> task.getUserId().equals(userId))
                .map(this::toResponse);
    }

    // 更新任务
    public Optional<TaskResponse> updateTask(Long id, TaskRequest request, Long userId) {
        return taskRepository.findById(id)
                .filter(task -> task.getUserId().equals(userId))
                .map(task -> {
                    if (request.getTitle() != null) task.setTitle(request.getTitle());
                    if (request.getDescription() != null) task.setDescription(request.getDescription());
                    if (request.getStatus() != null) task.setStatus(request.getStatus());
                    if (request.getPriority() != null) task.setPriority(request.getPriority());
                    task.setDueDate(request.getDueDate());

                    return toResponse(taskRepository.save(task));
                });
    }

    // Task到TaskResponse的转换
    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setUserId(task.getUserId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setPriority(task.getPriority());
        response.setDueDate(task.getDueDate());
        response.setAiAssisted(task.getAiAssisted());
        return response;
    }
}
