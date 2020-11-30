package com.example.metalTest.parte.service.impl;

import com.example.metalTest.parte.domain.Parte;
import java.util.List;
public class ParteBuscador {



    private Parte findParte(Integer parteId, List<Parte> parteList ){
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
    public Parte getParte(Integer parteId, List<Parte> parteList){
        if(parteId != null){
            return findParte(parteId, parteList);
        }
        return null;
    }
}
