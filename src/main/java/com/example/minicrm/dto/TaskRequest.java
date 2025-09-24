package com.example.minicrm.dto;

import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String assigneeEmail;

    public Long getProjectId(){
        return projectId;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getStatus(){
        return status;
    }

    public String getAssigneeEmail(){
        return assigneeEmail;
    }
}
