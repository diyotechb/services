package com.diyo.app.jobApp.service;

import com.diyo.app.exception.InvalidResumeUrlException;
import com.diyo.app.exception.InvalidUserIdException;
import com.diyo.app.jobApp.entity.JobApplication;
import com.diyo.app.jobApp.repository.JobApplicationRepository;
import com.diyo.app.services.FileService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.diyo.app.constants.S3FolderConstants.CAREER_APPLY_FORM_RESUME_FOLDER;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private FileService fileService;

    public String applyJob(JobApplication jobApplication){
        String resumeBase64 = jobApplication.getResumeUrl();
        String resumeUrl = "";

        String fullName = (jobApplication.getFirstName() + "_" + jobApplication.getLastName()).replace(" ", "_");

        if (StringUtils.isNotEmpty(resumeBase64)) {
            resumeUrl = fileService.uploadBase64Content(resumeBase64, String.format("%s/%s", CAREER_APPLY_FORM_RESUME_FOLDER, fullName));
        }

        jobApplication.setResumeUrl(resumeUrl);
        jobApplicationRepository.save(jobApplication);
        return "Saved successfully";
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
