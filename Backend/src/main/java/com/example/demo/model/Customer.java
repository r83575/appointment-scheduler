package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String identifier;
    private String email;
    private String phoneNumber;
    private String anotherPhoneNumber;
    private String address;
    private String city;
    private String password;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;
}
