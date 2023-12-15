package com.diyo.app.jobApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Table(name="job_listing")
public class JobListing {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description", length = 1000)
    private String description;

    @Column(name="location")
    private String location;

    @Column(name="requirements", length = 1000)
    private String requirements;

    @Column(name="instructions", length = 1000)
    private String instructions;

    @Column(name="client")
    private String client;

    @Column(name="vendor")
    private String vendor;

    @Column(name="deadline")
    private LocalDateTime deadline;
}
