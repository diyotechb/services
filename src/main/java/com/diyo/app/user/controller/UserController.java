package com.diyo.app.user.controller;

import com.diyo.app.user.entity.User;
import com.diyo.app.user.entity.UserRequest;
import com.diyo.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/applyJob")
    public User createUser(@RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "middleName") String middleName,
                           @RequestParam(value = "lastName") String lastName,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "phoneNumber") String phoneNumber,
                           @RequestParam(value = "resume") MultipartFile resume) {
        UserRequest request = new UserRequest();
        request.setFirstName(firstName);
        request.setMiddleName(middleName);
        request.setLastName(lastName);
        request.setEmail(email);
        request.setPhoneNumber(phoneNumber);
        request.setResume(resume);
        return userService.createUser(request);
    }
}
