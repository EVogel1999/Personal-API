package com.example.personal.controllers;

import com.example.personal.services.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/files")
public class FileController {

    FileService fileService = new FileService();

    @GetMapping("/test")
    public String test() {
        return "Testing the files controller";
    }

    @GetMapping("/{name:.+}")
    public ResponseEntity<Object> getFile(@PathVariable String name) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] content = fileService.getFile(name);
        return new ResponseEntity<>(content, headers, OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        fileService.uploadFile(file);
        return new ResponseEntity<>(CREATED);
    }

    @DeleteMapping("/{name:.+}")
    public ResponseEntity<Object> deleteFile(@PathVariable String name) {
        fileService.deleteFile(name);
        return new ResponseEntity<>(NO_CONTENT);
    }
}
