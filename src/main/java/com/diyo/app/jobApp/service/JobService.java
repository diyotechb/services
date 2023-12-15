package com.diyo.app.jobApp.service;


import com.diyo.app.jobApp.entity.JobListing;
import com.diyo.app.jobApp.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;


    //READ ALL
    public List<JobListing> getAllJobs(){
        return jobRepository.findAll();
    }

    //READ SPECIFIC BY ID
    public JobListing getJobById(Long jobId){
        Optional<JobListing> listing = jobRepository.findById(jobId);
        if(!listing.isPresent()){
            throw new RuntimeException("Listing with jobId "+jobId+ " is not present!");
        }
        return listing.get();
    }

    //SAVE ONE JOB LISTING
    public String saveOneJob(JobListing jobListing){
        jobRepository.save(jobListing);
        return "Successfully saved a job listing";
    }

    //SAVE ALL JOB LISTING
    public String saveAllJob(List<JobListing> jobListingList){
        jobRepository.saveAll(jobListingList);
        return "Successfully saved all job listings";
    }

    //UPDATE BY ID
    public String updateJobById(Long jobId, JobListing updatedJob){
        Optional<JobListing> optionalJob = jobRepository.findById(jobId);
        if(optionalJob.isPresent()){
            JobListing existingJob = optionalJob.get();
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
        Optional<JobListing> optionalJobListing = jobRepository.findById(jobId);
        if(optionalJobListing.isPresent()){
            jobRepository.deleteById(jobId);
            return jobId+" successfully deleted!";
        }
        else{
            throw new RuntimeException("Job Id "+jobId+" couldn't be found!");
        }
    }

    //DELETE ALL
    public String deleteAll(){
        List<JobListing> job = jobRepository.findAll();
        int totalJob = job.size();
        jobRepository.deleteAll();
        return totalJob+ " job listings deleted";
    }

}
