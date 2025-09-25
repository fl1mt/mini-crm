package com.example.minicrm.dto;

import com.example.minicrm.entity.ProjectEntity;
import com.example.minicrm.entity.TaskEntity;
import com.example.minicrm.entity.TaskStatus;
import com.example.minicrm.entity.UserEntity;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private ProjectDTO project;
    private UserDTO createdBy;
    private UserDTO assignee;

    public TaskResponse() {}

    public TaskResponse(Long id,
                        String title,
                        String description,
                        TaskStatus status,
                        ProjectDTO project,
                        UserDTO createdBy,
                        UserDTO assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.project = project;
        this.createdBy = createdBy;
        this.assignee = assignee;
    }

    public static TaskResponse fromEntity(TaskEntity entity) {
        return new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                ProjectDTO.fromEntity(entity.getProject()),
                UserDTO.fromEntity(entity.getCreatedBy()),
                entity.getAssignee() != null ? UserDTO.fromEntity(entity.getAssignee()) : null
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public UserDTO getCreatedBy() {
        return createdBy;
    }

    public UserDTO getAssignee() {
        return assignee;
    }
    public static class ProjectDTO {
        private Long id;
        private String name;

        public ProjectDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public static ProjectDTO fromEntity(ProjectEntity project) {
            return new ProjectDTO(project.getId(), project.getName());
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class UserDTO {
        private Long id;
        private String username;

        public UserDTO(Long id, String username) {
            this.id = id;
            this.username = username;
        }

        public static UserDTO fromEntity(UserEntity user) {
            return new UserDTO(user.getId(), user.getUsername());
        }

        public Long getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }
    }
}
