package com.example.demo.service;

import com.example.demo.dto.customer.CustomerRequestDto;
import com.example.demo.dto.customer.CustomerResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public List<CustomerResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::toDto)
                .toList();
    }

    public CustomerResponseDto getById(Long id) {
        Customer entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return CustomerMapper.toDto(entity);
    }

    public CustomerResponseDto create(CustomerRequestDto dto) {
        Customer entity = CustomerMapper.toEntity(dto);
        Customer saved = repository.save(entity);
        return CustomerMapper.toDto(saved);
    }

    public CustomerResponseDto update(Long id, CustomerRequestDto dto) {
        Customer existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        CustomerMapper.updateEntity(existing, dto);

        Customer updated = repository.save(existing);

        return CustomerMapper.toDto(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
