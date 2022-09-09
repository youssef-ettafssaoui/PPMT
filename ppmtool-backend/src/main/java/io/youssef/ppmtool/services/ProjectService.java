package io.youssef.ppmtool.services;

import io.youssef.ppmtool.domain.Project;
import io.youssef.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        // Logic suggests for the update operation
        return projectRepository.save(project);
    }
}
