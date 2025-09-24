package com.example.minicrm.controller;

import com.example.minicrm.dto.TaskRequest;
import com.example.minicrm.dto.TaskResponse;
import com.example.minicrm.entity.TaskEntity;
import com.example.minicrm.entity.TaskStatus;
import com.example.minicrm.entity.UserEntity;
import com.example.minicrm.service.TaskService;
import com.example.minicrm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request, Principal principal) {
        TaskResponse created = taskService.createTask(request, principal.getName());
        return ResponseEntity.ok(created);
    }


    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<TaskEntity>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId));
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<TaskEntity> updateTaskStatus(@PathVariable Long taskId,
                                                       @RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.updateTaskStatus(taskId, status));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

    private UserEntity getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
