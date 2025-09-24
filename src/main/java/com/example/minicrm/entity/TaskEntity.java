package com.example.minicrm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status; // NEW, IN_PROGRESS, DONE

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private UserEntity createdBy;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private UserEntity assignee;

    public void setProject(ProjectEntity project){
        this.project = project;
    }

    public void setCreatedBy(UserEntity user){
        this.createdBy = user;
    }

    public void setStatus(TaskStatus status){
        this.status = status;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public TaskStatus getStatus(){
        return status;
    }
    public UserEntity getCreatedBy(){
        return createdBy;
    }

    public void setAssignee(UserEntity assignee){
        this.assignee = assignee;
    }

    public ProjectEntity getProject(){
        return project;
    }

    public String getDescription(){
        return description;
    }

    public String getTitle(){
        return title;
    }

    public Long getId(){
        return id;
    }


    public UserEntity getAssignee(){
        return assignee;
    }
}

