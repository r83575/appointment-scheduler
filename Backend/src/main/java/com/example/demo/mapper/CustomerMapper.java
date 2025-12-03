package com.example.demo.mapper;

import com.example.demo.dto.customer.CustomerRequestDto;
import com.example.demo.dto.customer.CustomerResponseDto;
import com.example.demo.model.Customer;

public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDto dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .identifier(dto.getIdentifier())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .anotherPhoneNumber(dto.getAnotherPhoneNumber())
                .address(dto.getAddress())
                .city(dto.getCity())
                .password(dto.getPassword())
                .build();
    }

    public static CustomerResponseDto toDto(Customer entity) {
        CustomerResponseDto dto = new CustomerResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setIdentifier(entity.getIdentifier());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setAnotherPhoneNumber(entity.getAnotherPhoneNumber());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        return dto;
    }

    public static void updateEntity(Customer entity, CustomerRequestDto dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setIdentifier(dto.getIdentifier());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAnotherPhoneNumber(dto.getAnotherPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setPassword(dto.getPassword());
    }

}
