package com.diyo.app.jobApp.repository;

import com.diyo.app.jobApp.entity.JobApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
