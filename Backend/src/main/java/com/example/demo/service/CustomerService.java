package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }

    public Optional<Customer> getById(Long id) {
        return repository.findById(id);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    public Customer update(Long id, Customer updated) {
        return repository.findById(id).map(existing -> {
            existing.setFirstName(updated.getFirstName());
            existing.setLastName(updated.getLastName());
            existing.setIdentifier(updated.getIdentifier());
            existing.setEmail(updated.getEmail());
            existing.setPhoneNumber(updated.getPhoneNumber());
            existing.setAnotherPhoneNumber(updated.getAnotherPhoneNumber());
            existing.setAddress(updated.getAddress());
            existing.setCity(updated.getCity());
            existing.setPassword(updated.getPassword());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
