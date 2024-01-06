package com.diyo.app.jobApp.controller;

import com.diyo.app.jobApp.entity.JobApplication;
import com.diyo.app.jobApp.service.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/api/jobApplication")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @PostMapping("/applyJob")
    public ResponseEntity<Map<String, String>> applyJob(@Validated @RequestBody JobApplication jobApplication){
        String result = jobApplicationService.applyJob(jobApplication);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", result);
        return ResponseEntity.ok(responseMap);
    }

    //Get Mapping for returning the job applicant
    @GetMapping("/all")
    public List<JobApplication> getUsers(){
        return jobApplicationService.getUsers();
    }

    @GetMapping("/{id}")
    public JobApplication getUser(@PathVariable int id) throws Exception {
        return jobApplicationService.getUser(id);
    }

    @GetMapping("/now")
    public String getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate = dateFormat.format(date);
        return "Current Date and Time: " + formattedDate;
    }
}
