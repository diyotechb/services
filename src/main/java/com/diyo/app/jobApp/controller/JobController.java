package com.diyo.app.jobApp.controller;

import com.diyo.app.jobApp.entity.JobListing;
import com.diyo.app.jobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs/")
public class JobController {
    @Autowired
    JobService jobService;

    //READ ALL
    @GetMapping("/all")
    public List<JobListing> getJobs(){
        return jobService.getAllJobs();
    }

    //READ ONE BY ID
    @GetMapping("/{id}")
    public JobListing getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    //SAVE ONE
    @PostMapping("/save")
    public String saveJob(@RequestBody JobListing oneJob){
        return jobService.saveOneJob(oneJob);
    }

    //SAVE ALL
    @PostMapping("/saveAll")
    public String saveJobs(@RequestBody List<JobListing> allJobs){
        return jobService.saveAllJob(allJobs);
    }

    //UPDATE ONE

    @PutMapping("/{id}")
    public String updateJobById(@PathVariable Long id, @RequestBody JobListing jobListing){
        return jobService.updateJobById(id, jobListing);
    }

    //DELETE ONE
    @DeleteMapping("/delete/{id}")
    public String deleteJobById(@PathVariable Long id){
        return jobService.deleteById(id);
    }

    //DELETE ALL
    @DeleteMapping("/deleteAll")
    public String deleteJobs(){
        return jobService.deleteAll();
    }
}
