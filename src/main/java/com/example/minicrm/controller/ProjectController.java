package com.example.minicrm.controller;
import com.example.minicrm.entity.UserEntity;

import com.example.minicrm.entity.ProjectEntity;
import com.example.minicrm.service.ProjectService;
import com.example.minicrm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectEntity>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectEntity> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<ProjectEntity> createProject(@RequestBody ProjectEntity project) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        System.out.println("Username from token (projectController): " + email);
        UserEntity currentUser = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(projectService.createProject(project, currentUser));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProjectEntity> updateProject(@PathVariable Long id, @RequestBody ProjectEntity project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
