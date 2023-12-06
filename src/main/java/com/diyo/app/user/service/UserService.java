package com.diyo.app.user.service;

import com.diyo.app.services.FileService;
import com.diyo.app.user.entity.User;
import com.diyo.app.user.entity.UserRequest;
import com.diyo.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileService fileService;

    public User createUser(UserRequest request) {
        String resumeUrl = fileService.uploadFile(request.getResume());
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setResumeUrl(resumeUrl);
        return userRepository.save(user);
    }
}
