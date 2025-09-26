package com.example.minicrm.dto;

import com.example.minicrm.entity.ProjectEntity;
import com.example.minicrm.entity.TaskEntity;
import com.example.minicrm.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private UserEntity owner;
    private List<TaskShortDTO> tasks;

    public ProjectResponse(Long id, String name, UserEntity owner, String description, List<TaskShortDTO> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
        this.owner = owner;
        this.description = description;
    }

    public static ProjectResponse fromEntity(ProjectEntity project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getOwner(),
                project.getDescription(),
                project.getTasks() != null
                        ? project.getTasks().stream()
                        .map(TaskShortDTO::fromEntity)
                        .collect(Collectors.toList())
                        : List.of()
        );
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){
        return description;
    }

    public UserEntity getOwner(){
        return owner;
    }

    public List<TaskShortDTO> getTasks() {
        return tasks;
    }

    public static class TaskShortDTO {
        private String title;
        private String description;

        public TaskShortDTO(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public static TaskShortDTO fromEntity(TaskEntity task) {
            return new TaskShortDTO(task.getTitle(), task.getDescription());
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }
    }
}
