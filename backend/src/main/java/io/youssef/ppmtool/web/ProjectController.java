package io.youssef.ppmtool.web;


import io.youssef.ppmtool.domain.Project;
import io.youssef.ppmtool.services.MapValidationErrorService;
import io.youssef.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    // creating the route to post a new project or create a new project
    // return a response entity of type project.
    // BindingResult = an interface that basically invokes the validator on an object?
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        // using ProjectService to save our project in the database
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Project with ID: '"+projectId+"'was deleted !", HttpStatus.OK);
    }

}
