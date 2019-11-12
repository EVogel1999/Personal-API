package com.example.personal.controllers;

import com.example.personal.database.schema.Project;
import com.example.personal.exceptions.BadRequestError;
import com.example.personal.exceptions.NotFoundError;
import com.example.personal.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    ProjectService service = new ProjectService();

    @GetMapping("/test")
    public String test() {
        return "Testing the Project Controller";
    }

    @GetMapping()
    public ResponseEntity<Object> getAllProjects() {
        try {
            return new ResponseEntity<>(service.getProjects(), OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProject(@RequestParam String id) {
        try {
            return new ResponseEntity<>(service.getProject(id), OK);
        } catch (NotFoundError error) {
            return new ResponseEntity<>(error.getMessage(), NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createProject(@RequestBody Project project) {
        try {
            return new ResponseEntity<>(service.createProject(project), CREATED);
        } catch (BadRequestError error) {
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProject(@RequestParam String id, @RequestBody Project project) {
        try {
            return new ResponseEntity<>(service.updateProject(id, project), NO_CONTENT);
        } catch (NotFoundError error) {
            return new ResponseEntity<>(error.getMessage(), NOT_FOUND);
        } catch (BadRequestError error) {
            return new ResponseEntity<>(error.getMessage(), BAD_REQUEST);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@RequestParam String id) {
        try {
            service.deleteProject(id);
            return new ResponseEntity<>(NO_CONTENT);
        } catch (NotFoundError error) {
            return new ResponseEntity<>(error.getMessage(), NOT_FOUND);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), INTERNAL_SERVER_ERROR);
        }
    }
}
