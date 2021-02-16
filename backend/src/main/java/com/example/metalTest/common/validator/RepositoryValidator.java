package com.example.metalTest.common.validator;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class RepositoryValidator {

    public Object getObject(JpaRepository<Object, Integer> repository, Integer id) throws ValidateFieldException {
        Optional opt = repository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El recurso que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }
}
