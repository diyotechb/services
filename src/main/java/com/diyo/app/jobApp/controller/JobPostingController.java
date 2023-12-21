package com.diyo.app.jobApp.controller;

import com.diyo.app.jobApp.entity.JobPosting;
import com.diyo.app.jobApp.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobPosting")
public class JobPostingController {
    @Autowired
    JobPostingService jobPostingService;

    //READ ALL
    @GetMapping("/all")
    public List<JobPosting> getJobs(){
        return jobPostingService.getAllJobs();
    }

    //READ ONE BY ID
    @GetMapping("/{id}")
    public JobPosting getJobById(@PathVariable Long id){
        return jobPostingService.getJobById(id);
    }

    //SAVE ONE
    @PostMapping("/save")
    public String saveJob(@RequestBody JobPosting oneJob){
        return jobPostingService.saveOneJob(oneJob);
    }

    //SAVE ALL
    @PostMapping("/saveAll")
    public String saveJobs(@RequestBody List<JobPosting> allJobs){
        return jobPostingService.saveAllJob(allJobs);
    }

    //UPDATE ONE

    @PutMapping("/{id}")
    public String updateJobById(@PathVariable Long id, @RequestBody JobPosting jobPosting){
        return jobPostingService.updateJobById(id, jobPosting);
    }

    //DELETE ONE
    @DeleteMapping("/delete/{id}")
    public String deleteJobById(@PathVariable Long id){
        return jobPostingService.deleteById(id);
    }

    //DELETE ALL
    @DeleteMapping("/deleteAll")
    public String deleteJobs(){
        return jobPostingService.deleteAll();
    }
}
