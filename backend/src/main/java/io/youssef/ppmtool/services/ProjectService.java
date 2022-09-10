package io.youssef.ppmtool.services;

import io.youssef.ppmtool.domain.Project;
import io.youssef.ppmtool.exceptions.ProjectIdException;
import io.youssef.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists !");
        }
    }
    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null) {
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist !");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectID) {
        Project project = projectRepository.findByProjectIdentifier(projectID.toUpperCase());
        if(project == null) {
            throw new ProjectIdException("Cannot delete Project with ID '"+projectID+"'. This Project does not exist !");
        }
        projectRepository.delete(project);
    }
}
