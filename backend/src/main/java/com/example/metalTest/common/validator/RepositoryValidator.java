package com.example.metalTest.common.validator;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.personal.domain.Personal;
import io.swagger.annotations.Scope;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@NoArgsConstructor
@Getter
@Setter
public class RepositoryValidator<T> {

    private T objeto;

    public RepositoryValidator(T objeto) {
        this.objeto = objeto;
    }

    /**
     * @param repository
     * @param id
     * @return Devuelve un objeto si la entidad se encuentra en el repositorio
     * @throws ValidateFieldException
     */
    public T getObject(JpaRepository repository, Integer id) throws ValidateFieldException {
        Optional opt;
        try{
            opt = repository.findById(id);
            if (!opt.isPresent()) {
                throw new ValidateFieldException("El recurso que desea acceder no existe", "id", String.valueOf(id));
            }
        }catch (Exception e){
            throw new ValidateFieldException("El recurso que desea acceder no existe, "+ objeto.getClass().toString(), "id", String.valueOf(id));
        }

        setObjeto(((T) opt.get()));
        return objeto ;
    }


}
