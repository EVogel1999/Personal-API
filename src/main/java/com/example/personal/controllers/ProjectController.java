package com.example.personal.controllers;

import com.example.personal.database.schema.Project;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping("/test")
    public String test() {
        return "Testing the Project Controller";
    }

    @GetMapping()
    public Project[] getAllProjects() {
        return null;
    }
}
