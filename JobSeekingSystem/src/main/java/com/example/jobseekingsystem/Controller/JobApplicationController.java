package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.ApiResponse.ApiResponse;
import com.example.jobseekingsystem.Service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobApplication")
@RequiredArgsConstructor
public class JobApplicationController {
    // 1. Declare a dependency for JobApplicationService using Dependency Injection
    private final JobApplicationService jobApplicationService;

    // 2. CRUD
    // 2.1 Get
    @GetMapping("/get")
    public ResponseEntity getAllJobApplications() {
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplications());
    }

    // 3. Extra Endpoints
    // 3.1 Apply for a job
    @PostMapping("/apply/{userId}/{jobPostId}")
    public ResponseEntity applyForJob(@PathVariable Integer userId, @PathVariable Integer jobPostId) {
        return ResponseEntity.status(200).body(new ApiResponse(jobApplicationService.applyForJob(userId,jobPostId)));
    }

    // 3.2  Withdraw a job application
    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity withdrawJobApplication(@PathVariable Integer id){
        if(jobApplicationService.withdrawJobApplication(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Job Application Has Been Successfully Withdrawn."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Job Application Not Found."));
    }
}