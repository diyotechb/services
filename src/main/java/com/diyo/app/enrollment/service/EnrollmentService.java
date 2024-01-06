package com.diyo.app.enrollment.service;

import com.diyo.app.enrollment.entity.EnrollmentForm;
import com.diyo.app.enrollment.repository.EnrollmentRepository;
import com.diyo.app.services.FileService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.diyo.app.constants.S3FolderConstants.ENROLLMENT_FORM_LICENSE_FOLDER;
import static com.diyo.app.constants.S3FolderConstants.ENROLLMENT_FORM_RESUME_FOLDER;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final FileService fileService;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, FileService fileService) {
        this.enrollmentRepository = enrollmentRepository;
        this.fileService = fileService;
    }

    public List<EnrollmentForm> getAllEnrollment(){
        return  enrollmentRepository.findAll();
    }

    public String saveEnrollmentWithDoc(EnrollmentForm enrollmentForm){
        String fullName = enrollmentForm.getFullName();
        String resumeBase64 = enrollmentForm.getResumeDoc();
        String licenseBase64 = enrollmentForm.getLicenseDoc();

        String resumeUrl = "";
        String licenseUrl = "";

        if (StringUtils.isNotEmpty(resumeBase64)) {
            resumeUrl = fileService.uploadBase64Content(resumeBase64, String.format("%s/%s", ENROLLMENT_FORM_RESUME_FOLDER, fullName));
        }

        if (StringUtils.isNotEmpty(licenseBase64)) {
            licenseUrl = fileService.uploadBase64Content(licenseBase64, String.format("%s/%s", ENROLLMENT_FORM_LICENSE_FOLDER, fullName));
        }

        enrollmentForm.setResumeDoc(resumeUrl);
        enrollmentForm.setLicenseDoc(licenseUrl);

        enrollmentRepository.save(enrollmentForm);
        return "Saved successfully";
    }
}
