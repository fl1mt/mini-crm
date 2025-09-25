package com.example.minicrm.dto;

import com.example.minicrm.entity.ProjectEntity;
import com.example.minicrm.entity.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectResponse {
    private Long id;
    private String name;
    private List<TaskShortDTO> tasks;

    public ProjectResponse(Long id, String name, List<TaskShortDTO> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    public static ProjectResponse fromEntity(ProjectEntity project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
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
