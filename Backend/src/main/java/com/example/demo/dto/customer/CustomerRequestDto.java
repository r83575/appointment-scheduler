package com.example.demo.dto.customer;

import lombok.Data;

@Data
public class CustomerRequestDto {
    private String firstName;
    private String lastName;
    private String identifier;
    private String email;
    private String phoneNumber;
    private String anotherPhoneNumber;
    private String address;
    private String city;
    private String password;
}
