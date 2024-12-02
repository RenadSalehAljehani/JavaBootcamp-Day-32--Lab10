package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    // 1. Declare a dependency for JobPostRepository using Dependency Injection
    private final JobPostRepository jobPostRepository;

    // 2. CRUD
    // 2.1 Get
    public List<JobPost> getAllJobPosts() {
        return jobPostRepository.findAll();
    }

    // 2.2 Post
    public void addJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);
    }

    // 2.3 Update
    public Boolean updateJobPost(Integer id, JobPost jobPost) {
        // 1. To check if the row to be updated exists
        JobPost oldJobPost = jobPostRepository.getReferenceById(id);

        if (oldJobPost == null) {
            return false;
        }
        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setPostingDate(jobPost.getPostingDate());
        jobPostRepository.save(oldJobPost);
        return true;
    }

    // 2.4 Delete
    public Boolean deleteJobPost(Integer id) {
        // 1. To check if the row to be deleted exists
        JobPost oldJobPost = jobPostRepository.getReferenceById(id);

        if (oldJobPost == null) {
            return false;
        }
        jobPostRepository.delete(oldJobPost);
        return true;
    }
}