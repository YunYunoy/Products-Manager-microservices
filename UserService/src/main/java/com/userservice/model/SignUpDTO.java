package com.userservice.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SignUpDTO {

    @Size(min = 4, max = 255, message = "Name must be between 4 and 255 characters")
    @NotNull(message = "Name is required")
    private String name;

    @Size(min = 4, max = 255, message = "Email must be between 4 and 255 characters")
    @Email(message = "Invalid email format")
    private String email;

    @Size(min = 4, max = 255, message = "Password must be between 4 and 255 characters")
    @NotNull(message = "Password is required")
    private String password;


    @Pattern(regexp = "\\d{3}-\\d{3}-\\d{3}$",message = "Invalid phone number format")  // Pattern: xxx-xxx-xxx
    private String phoneNumber;

    @Size(min = 1, max = 255, message = "Street must be between 1 and 255 characters")
    private String street;

    @Size(min = 1, max = 255, message = "City must be between 1 and 255 characters")
    private String city;

    @Pattern(regexp = "\\d{2}-\\d{3}", message = "Invalid postal code format")
    private String postalCode;

    @AssertTrue(message = "Terms and conditions must be accepted")
    private boolean termsAccepted;
}