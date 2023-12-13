package com.diyo.app.user.controller;

import com.diyo.app.user.entity.User;
import com.diyo.app.user.entity.UserRequest;
import com.diyo.app.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Validated
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/applyJob")
    public User createUser(@Valid @NotBlank(message = "First name is required") @RequestParam(value = "firstName") String firstName,
                           @RequestParam(value = "middleName") String middleName,
                           @Valid @NotBlank(message = "Last name is required") @RequestParam(value = "lastName") String lastName,
                           @Valid @NotBlank(message = "Email is required") @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", message = "Invalid email address") @RequestParam(value = "email") String email,
                           @Valid @NotBlank(message = "Phone number is required") @Pattern(regexp = "^[0-9- ]+$", message = "Invalid phone number") @RequestParam(value = "phoneNumber") String phoneNumber,
                           @Valid @NotNull(message = "Resume file is required") @RequestParam(value = "resume") MultipartFile resume) {
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
