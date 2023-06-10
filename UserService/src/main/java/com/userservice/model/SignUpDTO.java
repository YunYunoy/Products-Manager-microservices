package com.userservice.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpDTO {

    @Size(min = 4, max = 255)
    @NotNull
    private String name;

    @Size(min = 4, max = 255)
    @Email
    private String email;

    @Size(min = 4, max = 255)
    @NotNull
    private String password;
}