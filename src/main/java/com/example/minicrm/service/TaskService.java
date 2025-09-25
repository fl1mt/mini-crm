package com.example.minicrm.service;

import com.example.minicrm.dto.TaskRequest;
import com.example.minicrm.dto.TaskResponse;
import com.example.minicrm.entity.ProjectEntity;
import com.example.minicrm.entity.TaskEntity;
import com.example.minicrm.entity.TaskStatus;
import com.example.minicrm.entity.UserEntity;
import com.example.minicrm.repository.ProjectRepository;
import com.example.minicrm.repository.TaskRepository;
import com.example.minicrm.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public TaskResponse createTask(TaskRequest request, String email) {
        UserEntity creator = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProjectEntity project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        UserEntity assignee = null;
        if (request.getAssigneeEmail() != null && !request.getAssigneeEmail().isEmpty()) {
            assignee = userRepository.findByEmail(request.getAssigneeEmail())
                    .orElseThrow(() -> new RuntimeException("Assignee not found"));
        }

        TaskEntity task = new TaskEntity();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(TaskStatus.valueOf(request.getStatus()));
        task.setProject(project);
        task.setCreatedBy(creator);
        task.setAssignee(assignee);

        TaskEntity saved = taskRepository.save(task);

        return TaskResponse.fromEntity(saved);
    }

    public List<TaskResponse> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId)
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }


    public TaskEntity updateTaskStatus(Long taskId, TaskStatus status) {
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<TaskEntity> getTasksByOwner(Long ownerId) {
        return taskRepository.findByCreatedById(ownerId);
    }

    public List<TaskEntity> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }
}
