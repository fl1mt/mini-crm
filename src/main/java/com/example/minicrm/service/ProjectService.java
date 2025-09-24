package com.example.minicrm.service;
import java.util.Optional;

import com.example.minicrm.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.minicrm.entity.ProjectEntity;
import com.example.minicrm.entity.UserEntity;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<ProjectEntity> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public ProjectEntity createProject(ProjectEntity project, UserEntity owner) {
        project.setOwner(owner);
        return projectRepository.save(project);
    }

    public ProjectEntity updateProject(Long id, ProjectEntity project) {
        return projectRepository.findById(id)
                .map(existing -> {
                    existing.setName(project.getName());
                    existing.setDescription(project.getDescription());
                    return projectRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
