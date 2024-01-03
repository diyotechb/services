package com.diyo.app.services;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileService {
    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    @Value("${aws.s3.bucket.folder.job.application}")
    private String jobApplicationFolder;

    private final AmazonS3 s3Client;

    public FileService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadFile(MultipartFile file, String preferredFileName) {
        String folderPath = jobApplicationFolder +"/";
        String fileName = folderPath + generateFileName(file, preferredFileName);
        String fileUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream(), metadata);
            s3Client.putObject(putObjectRequest);
            return fileUrl;
        } catch (IOException | SdkClientException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    public String uploadByteContent(byte[] fileBytes, String preferredFileName){
        String fileName = generateFileName(preferredFileName);
        String fileUrl = "https://" + bucketName + ".s3." + region + ".amazonaws.com/" + fileName;

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileBytes.length);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, byteArrayInputStream, metadata);
            s3Client.putObject(putObjectRequest);

            return fileUrl;
        } catch (SdkClientException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    private String generateFileName(MultipartFile file, String preferredFileName) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmma");
        String formattedDate = now.format(formatter);
        String fileName = preferredFileName + "_" + formattedDate;
        if (preferredFileName == null || preferredFileName.isEmpty()) {
            fileName = file.getOriginalFilename() + "_" + formattedDate;
        }
        return fileName.replace(" ", "_").toLowerCase();
    }

    private String generateFileName(String preferredFileName){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmma");
        String formattedDate = now.format(formatter);
        String fileName = preferredFileName + "_" + formattedDate;

        return fileName.replace(" ", "_").toLowerCase();
    }
}
