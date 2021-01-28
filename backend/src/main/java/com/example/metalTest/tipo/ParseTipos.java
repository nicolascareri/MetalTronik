//package com.example.metalTest.tipo;
//
//import lombok.Getter;
//import com.opencsv.CSVReader;
//
//import java.io.FileReader;
//import java.util.ArrayList;
//
//@Getter
//public class ParseTipos {
//    private String path= "tipos.csv" ;
//
//    public ArrayList<String[]> getTipos(){
//        CSVReader reader = null;
//        ArrayList<String[]> tipos = new ArrayList<>();
//        try
//        {
//            //parsing a CSV file into CSVReader class constructor
//            reader = new CSVReader(new FileReader("C:\\Users\\facun\\OneDrive\\Documentos\\MetalTronik\\MetalTronik\\backend\\src\\main\\java\\com\\example\\metalTest\\tipo\\tipos.csv"));
//            String [] nextLine;
//            //reads one line at a time
//            while ((nextLine = reader.readNext()) != null){
//                    tipos.add(nextLine);
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return tipos;
//    }
//
//
//}
