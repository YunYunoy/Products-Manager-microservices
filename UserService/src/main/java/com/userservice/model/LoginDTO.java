package com.userservice.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {

    @Size(min = 4, max = 255, message = "Name must be between 4 and 255 characters")
    @NotNull(message = "Name is required")
    private String usernameOrEmail;

    @Size(min = 4, max = 255, message = "Password must be between 4 and 255 characters")
    @NotNull(message = "Password is required")
    private String password;
}
