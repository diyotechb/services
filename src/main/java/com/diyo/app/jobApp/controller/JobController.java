package com.diyo.app.jobApp.controller;



import com.diyo.app.jobApp.entity.JobListing;
import com.diyo.app.jobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diyo")
public class JobController {
    @Autowired
    JobService jobService;

    //READ ALL
    @GetMapping("/getJobs")
    public List<JobListing> getJobs(){
        return jobService.getAllJobs();
    }

    //READ ONE BY ID
    @GetMapping("/getJobById/{jobId}")
    public JobListing getJobById(@PathVariable Long jobId){
        return jobService.getJobById(jobId);
    }

    //SAVE ONE
    @PostMapping("/saveJob")
    public String saveJob(@RequestBody JobListing oneJob){
        return jobService.saveOneJob(oneJob);
    }

    //SAVE ALL
    @PostMapping("/saveJobs")
    public String saveJobs(@RequestBody List<JobListing> allJobs){
        return jobService.saveAllJob(allJobs);
    }

    //UPDATE ONE

    @PostMapping("/updateJobById/{jobId}")
    public String updateJobById(@PathVariable Long jobId, @RequestBody JobListing jobListing){
        return jobService.updateJobById(jobId, jobListing);
    }

    //DELETE ONE
    @DeleteMapping("/deleteJobById/{jobId}")
    public String deleteJobById(@PathVariable Long jobId){
        return jobService.deleteById(jobId);
    }

    //DELETE ALL
    @DeleteMapping("/deleteJobs")
    public String deleteJobs(){
        return jobService.deleteAll();
    }

    @GetMapping("/test")
    public String testGetMapping(){
        return "test successful";
    }

}
