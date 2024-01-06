package com.diyo.app.services;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileService {
    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;
    private final AmazonS3 s3Client;

    public FileService(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public static String extractContentType(String base64Data) {
        if (base64Data.startsWith("data:")) {
            int commaIndex = base64Data.indexOf(",");
            if (commaIndex != -1) {
                String prefix = base64Data.substring(0, commaIndex);
                String[] parts = prefix.split(";");
                if (parts.length > 0) {
                    return parts[0].replace("data:", "");
                }
            }
        }
        return null;
    }

    public String uploadMultiPartFile(MultipartFile file, String preferredFileName) {
        try {
            return uploadFile(preferredFileName, file.getContentType(),file.getSize(), file.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    public String uploadBase64Content(String base64Content, String preferredFileName){
        String getContentType = extractContentType(base64Content);
        String base64WithoutPrefix = base64Content.substring(base64Content.indexOf(",") + 1); // Remove data URI prefix
        byte[] fileBytes = Base64.decodeBase64(base64WithoutPrefix);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
        return uploadFile(preferredFileName, getContentType,fileBytes.length, byteArrayInputStream);
    }

    public String uploadFile(String preferredFileName, String contentType, long contentLength, InputStream inputStream){
        String fileName = generateFileName(preferredFileName);
        String fileUrl = getS3FileUrl(fileName);

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(contentLength);
            metadata.setContentType(contentType);

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, inputStream, metadata);
            s3Client.putObject(putObjectRequest);

            return fileUrl;
        } catch (SdkClientException e) {
            throw new RuntimeException("Failed to upload file to S3", e);
        }
    }

    private String getS3FileUrl(String fileName) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
    }

    private String generateFileName(String preferredFileName){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_hhmma");
        String formattedDate = now.format(formatter);
        return preferredFileName + "_" + formattedDate.replace(" ", "_").toLowerCase();
    }
}
