package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Repository.JobApplicationRepository;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import com.example.jobseekingsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    // 1. Declare a dependency for JobApplicationRepository using Dependency Injection
    private final JobApplicationRepository jobApplicationRepository;

    // 2. Declare a dependency for UserRepository using Dependency Injection
    private final UserRepository userRepository;

    // 3. Declare a dependency for JobPostRepository using Dependency Injection
    private final JobPostRepository jobPostRepository;

    // 4. CRUD
    // 4.1 Get
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    // 5. Extra Endpoints
    // 5.1 Apply for a job
    public String applyForJob(Integer userId, Integer jobPostId) {

        // 1. Check if user and job post are exist
        boolean userIsExists = userRepository.existsById(userId);
        boolean jobPostIsExists = jobPostRepository.existsById(jobPostId);

        // 2. Handel responses
        if (!userIsExists && !jobPostIsExists) {
            return "Both User & Job Post Not Found.";
        }
        if (!userIsExists) {
            return "User Not Found.";
        }
        if (!jobPostIsExists) {
            return "Job Post Not Found.";
        } else {
            JobApplication newJobApplication = new JobApplication();
            newJobApplication.setUserId(userId);
            newJobApplication.setJobPostId(jobPostId);
            jobApplicationRepository.save(newJobApplication);
            return "Job Application Has Been Successfully Completed.";
        }
    }

    // 5.2  Withdraw a job application
    public Boolean withdrawJobApplication(Integer id) {
        // 1. To check if the row to be deleted exists
        JobApplication oldJobApplication = jobApplicationRepository.getReferenceById(id);
        if (oldJobApplication == null) {
            return false;
        }
        jobApplicationRepository.delete(oldJobApplication);
        return true;
    }
}