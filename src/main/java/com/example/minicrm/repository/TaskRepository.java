package com.example.minicrm.repository;

import com.example.minicrm.entity.TaskEntity;
import com.example.minicrm.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByProjectId(Long projectId);
    List<TaskEntity> findByCreatedById(Long createdById);
    List<TaskEntity> findByStatus(TaskStatus status);
}

