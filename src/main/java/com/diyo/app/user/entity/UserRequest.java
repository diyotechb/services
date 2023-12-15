package com.diyo.app.user.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
public class UserRequest {
    @NotBlank
    private String firstName;
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^[0-9- ]+$", message = "Invalid phone number")
    private String phoneNumber;
    @NotNull
    private MultipartFile resume;
}
