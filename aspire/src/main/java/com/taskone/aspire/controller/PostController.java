package com.taskone.aspire.controller;

import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;

//import com.taskone.aspire.model.Employee;
//import com.taskone.aspire.model.EmployeeManager;
//import com.taskone.aspire.model.Manager;
import com.taskone.aspire.service.PostService;
//import com.taskone.aspire.service.PostService;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employees")
public class PostController {

    private final PostService postService;

    //@Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/process-file")
    public ResponseEntity<String> processFile(@RequestBody String inputJson) {
        String outputFilePath = "output.json";

        try {
            postService.processAndWriteToFile(inputJson, outputFilePath);
            return new ResponseEntity<>("File processed and output saved successfully", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Error processing file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
    

