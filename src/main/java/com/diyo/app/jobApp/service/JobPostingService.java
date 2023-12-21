package com.diyo.app.jobApp.service;


import com.diyo.app.jobApp.entity.JobPosting;
import com.diyo.app.jobApp.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPostingService {
    @Autowired
    JobPostingRepository jobPostingRepository;


    //READ ALL
    public List<JobPosting> getAllJobs(){
        return jobPostingRepository.findAll();
    }

    //READ SPECIFIC BY ID
    public JobPosting getJobById(Long jobId){
        Optional<JobPosting> listing = jobPostingRepository.findById(jobId);
        if(!listing.isPresent()){
            throw new RuntimeException("Listing with jobId "+jobId+ " is not present!");
        }
        return listing.get();
    }

    //SAVE ONE JOB LISTING
    public String saveOneJob(JobPosting jobPosting){
        jobPostingRepository.save(jobPosting);
        return "Successfully saved a job listing";
    }

    //SAVE ALL JOB LISTING
    public String saveAllJob(List<JobPosting> jobPostingList){
        jobPostingRepository.saveAll(jobPostingList);
        return "Successfully saved all job listings";
    }

    //UPDATE BY ID
    public String updateJobById(Long jobId, JobPosting updatedJob){
        Optional<JobPosting> optionalJob = jobPostingRepository.findById(jobId);
        if(optionalJob.isPresent()){
            JobPosting existingJob = optionalJob.get();
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setVendor(updatedJob.getVendor());
            existingJob.setClient(updatedJob.getClient());
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setId(updatedJob.getId());
            return "Job Id: "+jobId+" updated!";
        }else{
            throw new RuntimeException("JobId "+jobId+ " is not found!");
        }
    }

    //DELETE BY ID
    public String deleteById(Long jobId){
        Optional<JobPosting> optionalJobListing = jobPostingRepository.findById(jobId);
        if(optionalJobListing.isPresent()){
            jobPostingRepository.deleteById(jobId);
            return jobId+" successfully deleted!";
        }
        else{
            throw new RuntimeException("Job Id "+jobId+" couldn't be found!");
        }
    }

    //DELETE ALL
    public String deleteAll(){
        List<JobPosting> job = jobPostingRepository.findAll();
        int totalJob = job.size();
        jobPostingRepository.deleteAll();
        return totalJob+ " job listings deleted";
    }

}
