package com.diyo.app.jobApp.service;

import com.diyo.app.exception.InvalidResumeFileException;
import com.diyo.app.exception.InvalidResumeUrlException;
import com.diyo.app.exception.InvalidUserIdException;
import com.diyo.app.services.FileService;
import com.diyo.app.jobApp.entity.JobApplication;
import com.diyo.app.jobApp.model.JobApplicationRequest;
import com.diyo.app.jobApp.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private FileService fileService;

    public JobApplication createUser(JobApplicationRequest request) {
        MultipartFile selectedResume = request.getResume();
        if(selectedResume == null || selectedResume.isEmpty() || selectedResume.getOriginalFilename() == null || selectedResume.getOriginalFilename().isEmpty()) {
            throw new InvalidResumeFileException("Invalid resume file");
        }
        String fileName = (request.getFirstName() + "_" + request.getLastName()).replace(" ", "_");
        String resumeUrl = fileService.uploadFile(selectedResume, fileName);
        JobApplication user = new JobApplication();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setResumeUrl(resumeUrl);
        return jobApplicationRepository.save(user);
    }

    public List<JobApplication> getUsers(){
        return jobApplicationRepository.findAll();
    }

    public JobApplication getUser(int id) throws Exception {
        Optional<JobApplication> user = jobApplicationRepository.findById((long) id);
        if(user.isPresent()){
            String url = user.get().getResumeUrl();
            if(url==null){
                throw new InvalidResumeUrlException("Invalid Resume Url");
            }
            return user.get();
        }else{
            throw new InvalidUserIdException("User id isn't valid!");
        }
    }
}
