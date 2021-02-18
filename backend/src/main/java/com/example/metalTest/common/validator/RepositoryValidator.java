package com.example.metalTest.common.validator;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import io.swagger.annotations.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Scope(name = "singleton", description = "singleton")
@Component
public class RepositoryValidator {

    /**
     * @param repository
     * @param id
     * @return Devuelve un objeto si la entidad se encuentra en el repositorio
     * @throws ValidateFieldException
     */
    public Object getObject(JpaRepository repository, Integer id) throws ValidateFieldException {
        Optional opt = repository.findById(id);
        if (!opt.isPresent()) {
            throw new ValidateFieldException("El recurso que desea acceder no existe", "id", String.valueOf(id));
        }
        return opt.get();
    }
}
