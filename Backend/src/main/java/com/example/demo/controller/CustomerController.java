package com.example.demo.controller;

import com.example.demo.dto.customer.CustomerRequestDto;
import com.example.demo.dto.customer.CustomerResponseDto;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService service;

    // GET ALL
    @GetMapping
    public List<CustomerResponseDto> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public CustomerResponseDto create(@RequestBody CustomerRequestDto dto) {
        return service.create(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CustomerResponseDto update(
            @PathVariable Long id,
            @RequestBody CustomerRequestDto dto
    ) {
        return service.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
