package io.youssef.ppmtool.web;


import io.youssef.ppmtool.domain.Project;
import io.youssef.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // creating the route to post a new project or create a new project
    // return a response entity of type project.
    // BindingResult = an interface that basically invokes the validator on an object?
    @PostMapping("")
    public ResponseEntity<?> createNewProject(@RequestBody Project project, BindingResult result) {

        if(result.hasErrors()) {
            return new ResponseEntity<String>("Invalid Project Object !", HttpStatus.BAD_REQUEST);
        }
        // using ProjectService to save our project in the database
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

}
