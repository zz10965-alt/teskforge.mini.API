package com.taskforge.repository;

import com.taskforge.entity.Task;
import com.taskforge.entity.TaskPriority;
import com.taskforge.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByUserId(Long userId, Pageable pageable);
    Page<Task> findByUserIdAndStatus(Long userId, TaskStatus status, Pageable pageable);
    Page<Task> findByUserIdAndPriority(Long userId, TaskPriority priority, Pageable pageable);
    Page<Task> findByUserIdAndStatusAndPriority(Long userId, TaskStatus status, TaskPriority priority, Pageable pageable);

    boolean existsByIdAndUserId(Long id, Long userId);
}
