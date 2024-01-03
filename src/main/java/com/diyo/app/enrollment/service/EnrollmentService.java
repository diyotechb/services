package com.diyo.app.enrollment.service;

import com.diyo.app.enrollment.entity.EnrollmentForm;
import com.diyo.app.enrollment.repository.EnrollmentRepository;
import com.diyo.app.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    private FileService fileService;

    @Value("${aws.s3.bucket.folder.enrollment.form}")
    private String enrollmentFolder;

    public List<EnrollmentForm> getAllEnrollment(){
        return  enrollmentRepository.findAll();
    }

    public String saveEnrollmentWithDoc(EnrollmentForm enrollmentForm){
        byte[] resumeBytes = enrollmentForm.getResumeDoc().getBytes();
        byte[] licenseBytes = enrollmentForm.getLicenseDoc().getBytes();

        String fileName = enrollmentForm.getFullName();
        String resumeUrl = fileService.uploadByteContent(resumeBytes, enrollmentFolder + "/resume/" + fileName);
        String licenseUrl = fileService.uploadByteContent(licenseBytes,enrollmentFolder + "/license/" + fileName);

        enrollmentForm.setResumeDoc(resumeUrl);
        enrollmentForm.setLicenseDoc(licenseUrl);

        enrollmentRepository.save(enrollmentForm);
        return "Saved successful";
    }
}
