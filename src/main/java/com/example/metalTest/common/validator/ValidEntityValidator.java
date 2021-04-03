package com.example.metalTest.common.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEntityValidator implements ConstraintValidator<ValidEntity, Object> {

    @Autowired
    private ApplicationContext applicationContext;

    private JpaRepository repository;

    private boolean required;

    @Override
    public void initialize(ValidEntity constraintAnnotation) {
        repository = applicationContext.getBean(constraintAnnotation.repository());
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o != null) {
            return repository.existsById(o);
        } else return !required;
    }
}
