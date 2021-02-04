///*
//package com.example.metalTest.tipo;
//import com.example.metalTest.tipo.domain.Tipo;
//import com.example.metalTest.tipo.repository.TipoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
//@Component
//public class CreateTipos implements CommandLineRunner {
//    @Autowired
//    TipoRepository tipoRepository;
//    String cierra_parentesis = ")";
//    String abre_parentesis = "(";
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        ParseTipos parseTipos = new ParseTipos();
//        ArrayList<String[]> tipos = parseTipos.getTipos();
//        ArrayList<Tipo> tiposList = new ArrayList<>();
//        for (String[] posicion: tipos) {
//            System.out.println("Nombre: "+posicion[0]+"   Tipo: "+posicion[1]);
//            Tipo tipo = new Tipo();
//            tipo.setNombre(posicion[0]);
//            tipo.setTipo(posicion[1]);
//            tiposList.add(tipo);
//        }
//        System.out.println(tipoRepository.saveAll(tiposList));
//    }
//}
//*/
/*package com.example.metalTest.tipo;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CreateTipos implements CommandLineRunner {
    @Autowired
    TipoRepository tipoRepository;

    @Override
    public void run(String... args) throws Exception {

        ParseTipos parseTipos = new ParseTipos();
        ArrayList<String[]> tipos = parseTipos.getTipos();
        ArrayList<Tipo> tiposList = new ArrayList<>();
        for (String[] posicion: tipos) {
            System.out.println("Nombre: "+posicion[0]+"   Tipo: "+posicion[1]);
            Tipo tipo = new Tipo();
            tipo.setNombre(posicion[0]);
            tipo.setTipo(posicion[1]);
            tiposList.add(tipo);
        }
        System.out.println(tipoRepository.saveAll(tiposList));
    }
}*/
