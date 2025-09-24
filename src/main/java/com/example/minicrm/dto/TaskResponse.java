package com.example.minicrm.dto;

import com.example.minicrm.entity.TaskStatus;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Long projectId;
    private String createdByEmail;
    private String assigneeEmail;

    public TaskResponse(Long id, String title, String description, TaskStatus status,
                        Long projectId, String createdByEmail, String assigneeEmail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.projectId = projectId;
        this.createdByEmail = createdByEmail;
        this.assigneeEmail = assigneeEmail;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public Long getProjectId() { return projectId; }
    public String getCreatedByEmail() { return createdByEmail; }
    public String getAssigneeEmail() { return assigneeEmail; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(TaskStatus status) { this.status = status; }
    public void setProjectId(Long projectId) { this.projectId = projectId; }
    public void setCreatedByEmail(String createdByEmail) { this.createdByEmail = createdByEmail; }
    public void setAssigneeEmail(String assigneeEmail) { this.assigneeEmail = assigneeEmail; }
}
