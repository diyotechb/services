package com.diyo.app.jobApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name="job_listing")
public class JobListing {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="job_id")
    private Long jobId;

    @Column(name="job_title")
    private String jobTitle;

    @Column(name="job_description")
    private String jobDescription;

    @Column(name="client")
    private String client;

    @Column(name="vendor")
    private String vendor;

}
