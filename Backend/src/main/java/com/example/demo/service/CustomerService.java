package com.example.demo.service;

import com.example.demo.dto.customer.CustomerRequestDto;
import com.example.demo.dto.customer.CustomerResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
                .orElseThrow(() -> {
                    log.warn("Customer not found: id={}", id);
                    return new ResourceNotFoundException("Customer not found");
                });

        return CustomerMapper.toDto(entity);
    }

    public CustomerResponseDto create(CustomerRequestDto dto) {

        log.info("Creating customer");

        Customer entity = CustomerMapper.toEntity(dto);
        Customer saved = repository.save(entity);

        log.info("Customer created successfully: customerId={}", saved.getId());

        return CustomerMapper.toDto(saved);
    }

    public CustomerResponseDto update(Long id, CustomerRequestDto dto) {

        log.info("Updating customer: id={}", id);

        Customer existing = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Customer not found: id={}", id);
                    return new ResourceNotFoundException("Customer not found");
                });

        CustomerMapper.updateEntity(existing, dto);

        Customer updated = repository.save(existing);

        log.info("Customer updated successfully: customerId={}", updated.getId());

        return CustomerMapper.toDto(updated);
    }

    public void delete(Long id) {
        log.info("Deleting customer: id={}", id);
        repository.deleteById(id);
    }
}
