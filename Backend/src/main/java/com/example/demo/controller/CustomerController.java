package com.example.demo.controller;

import com.example.demo.dto.customer.CustomerRequestDto;
import com.example.demo.dto.customer.CustomerResponseDto;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public List<CustomerResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public CustomerResponseDto create(@Valid @RequestBody CustomerRequestDto dto) {
        return service.create(dto);
    }


    @PutMapping("/{id}")
    public CustomerResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
