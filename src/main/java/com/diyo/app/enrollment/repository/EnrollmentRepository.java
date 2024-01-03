package com.diyo.app.enrollment.repository;

import com.diyo.app.enrollment.entity.EnrollmentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentForm, Long> {
}
