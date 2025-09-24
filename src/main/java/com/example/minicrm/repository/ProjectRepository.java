package com.example.minicrm.repository;

import com.example.minicrm.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
