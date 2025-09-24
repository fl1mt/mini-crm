package com.example.minicrm.dto;

import lombok.Data;

@Data
public class ProjectDTO {
    private String name;
    private String description;
    private Long ownerId;
}
