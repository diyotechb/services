package com.diyo.app.jobApp.repository;


import com.diyo.app.jobApp.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobListing, Long> {


}
