package com.diyo.app.enrollment.entity;

import com.diyo.app.enrollment.enums.Status;
import com.diyo.app.enrollment.enums.Training;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_enrollment_form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Invalid email address")
    @Column(name = "email_address")
    private String emailAddress;

    @NotBlank(message = "Address is required")
    @Column(name = "current_address")
    private String currentAddress;

    @NotBlank(message = "Date of Birth is required")
    @Column(name = "date_of_birth")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$", message = "Invalid date of birth, use MM/DD/YYYY format.")
    private String dateOfBirth;

    @Pattern(regexp = "^[0-9- ]+$", message = "Invalid phone number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank(message = "Visa Status type is required")
    @Column(name = "visa_status")
    private String visaStatus;

    @Column(name = "has_driver_license")
    private boolean hasDriverLicense;

    @NotBlank(message = "Education is required")
    @Column(name = "education")
    private String education;

    @NotBlank(message = "Institution name is required")
    @Column(name = "institution")
    private String institution;

    @NotBlank(message = "Last degree is required")
    @Column(name = "degree")
    private String degree;

    @NotBlank(message = "Completion Date is required")
    @Column(name = "completion_date")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/\\d{4}$", message = "Invalid date of birth, use MM/DD/YYYY format.")
    private String completionDate;

    @Column(name = "referral")
    private String referral;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "training_preference")
    private Training trainingPreference;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "application_status")
    private Status applicationStatus;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "resume_doc")
    private String resumeDoc;

    @Column(name = "license_doc")
    private String licenseDoc;
}
