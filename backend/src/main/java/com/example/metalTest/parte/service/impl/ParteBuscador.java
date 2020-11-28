package com.example.metalTest.parte.service.impl;

import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.parte.repository.ParteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
public class ParteBuscador {



    private Parte findParte(Integer maquinaId, Integer parteId, List<Parte> parteList ){
        if (!parteList.isEmpty()){
            for (Parte a: parteList
            ) {
                if(a.getId() == parteId) return a;
            }
        }
        return null;
    }

    /**
     * Verifica que el campo "parteId" exista, si asi es lo busca en la base de datos
     * @param parteId
     * @return null(si parteId es null o si no lo encuentra en la base) o Parte
     */
    public Parte getParte(Integer maquinaId, Integer parteId, List<Parte> parteList){
        if(parteId != null){
            return findParte(maquinaId, parteId, parteList);
        }
        return null;
    }
}
