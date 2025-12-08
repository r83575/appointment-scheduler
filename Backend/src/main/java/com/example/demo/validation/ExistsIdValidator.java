package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExistsIdValidator implements ConstraintValidator<ExistsIdValid, Long> {

    private final ApplicationContext context;
    private Class<?> entity;

    @Override
    public void initialize(ExistsIdValid annotation) {
        this.entity = annotation.entity();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return true;

        String beanName = entity.getSimpleName().substring(0, 1).toLowerCase() +
                entity.getSimpleName().substring(1) + "Repo";

        JpaRepository<?, Long> repository = (JpaRepository<?, Long>) this.context.getBean(beanName);
        return repository.existsById(value);
    }
}
