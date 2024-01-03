package com.diyo.app.enrollment.controller;

import com.diyo.app.enrollment.entity.EnrollmentForm;
import com.diyo.app.enrollment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {
    @Autowired
    EnrollmentService enrollmentService;

    @GetMapping("/all")
    public List<EnrollmentForm> getAllEnrollmentForm(){
        List<EnrollmentForm> result = enrollmentService.getAllEnrollment();
        return ResponseEntity.ok(result).getBody();
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveEnrollmentWithDoc(@Validated @RequestBody EnrollmentForm enrollmentForm){
        String result = enrollmentService.saveEnrollmentWithDoc(enrollmentForm);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", result);
        return ResponseEntity.ok(responseMap);
    }
}
