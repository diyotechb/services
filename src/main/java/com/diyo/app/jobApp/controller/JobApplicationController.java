package com.diyo.app.jobApp.controller;

import com.diyo.app.jobApp.entity.JobApplication;
import com.diyo.app.jobApp.model.JobApplicationRequest;
import com.diyo.app.jobApp.service.JobApplicationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/api/jobApplication")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;
    @PostMapping("/applyJob")
    public JobApplication createUser(@Valid @NotBlank(message = "First name is required") @RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "middleName") String middleName,
                           @Valid @NotBlank(message = "Last name is required") @RequestParam(value = "lastName") String lastName,
                           @Valid @NotBlank(message = "Email is required") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Invalid email address") @RequestParam(value = "email") String email,
                           @Valid @NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9- ]+$", message = "Invalid phone number") @RequestParam(value = "phoneNumber") String phoneNumber,
                           @Valid @NotNull(message = "Resume file is required") @RequestParam(value = "resume") MultipartFile resume) {
        JobApplicationRequest request = new JobApplicationRequest();
        request.setFirstName(firstName);
        request.setMiddleName(middleName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setPhoneNumber(phoneNumber);
        request.setResume(resume);
        return jobApplicationService.createUser(request);
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
