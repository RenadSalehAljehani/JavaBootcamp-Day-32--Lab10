package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.ApiResponse.ApiResponse;
import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobPost")
@RequiredArgsConstructor
public class JobPostController {

    // 1. Declare a dependency for JobPostService using Dependency Injection
    private final JobPostService jobPostService;

    // 2. CRUD
    // 2.1 Get
    @GetMapping("/get")
    public ResponseEntity getAllJobPosts() {
        return ResponseEntity.status(200).body(jobPostService.getAllJobPosts());
    }

    // 2.2 Post
    @PostMapping("/add")
    public ResponseEntity addJobPost(@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("New Job Post Added."));
    }

    //2.3 Update
    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (jobPostService.updateJobPost(id, jobPost)) {
            return ResponseEntity.status(200).body(new ApiResponse("Job Post Updated."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Job Post Not Found."));
    }

    // 2.4 Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        if (jobPostService.deleteJobPost(id)) {
            return ResponseEntity.status(200).body(new ApiResponse("Job Post Deleted."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Job Post Not Found."));
    }
}