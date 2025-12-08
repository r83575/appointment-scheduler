package com.example.demo.dto.customer;

import jakarta.validation.constraints.*;
import lombok.Data;
import com.example.demo.validation.IsraeliIdValid;
import com.example.demo.validation.StrongPasswordValid;
import com.example.demo.validation.PhoneNumbersValid;

@PhoneNumbersValid
@Data
public class CustomerRequestDto {

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @IsraeliIdValid(message = "identifier is not a valid Israeli ID")
    @NotBlank(message = "identifier is required")
    private String identifier;

    @Email(message = "email format invalid")
    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "phoneNumber is required")
    @Pattern(regexp = "^(\\d{9}|\\d{10})$", message = "phoneNumber must contain 9 or 10 digits")
    private String phoneNumber;

    @Pattern(regexp = "^(\\d{9}|\\d{10})$", message = "anotherPhoneNumber must contain 9 or 10 digits")
    private String anotherPhoneNumber;

    private String address;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "password is required")
    @StrongPasswordValid(message = "password is not strong enough")
    private String password;
}
