/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.nuevapractica.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jeanp
 */
public class ArchivoUtil {

    public static void guardarCantanteEnArchivo(Cantante cantante, String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write(cantante.toString()); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cantante leerCantanteDeArchivo(String archivo) {
        try (Scanner scanner = new Scanner(new File(archivo))) {
            // Leer los datos del archivo y crear un nuevo Cantante
            // Aquí debes implementar la lógica para leer los datos y crear el objeto Cantante
            // Por simplicidad, en este ejemplo se asume que la primera línea del archivo contiene los datos del cantante
            String cantanteData = scanner.nextLine();
            // Parsea los datos para crear un Cantante (debes implementar el método 'parseCantante' en la clase Cantante)
            Cantante cantante = parseCantante(cantanteData);
            return cantante;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Disco> leerDiscosDeArchivo(String archivo) {
        List<Disco> discos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                String discoData = scanner.nextLine();
                Disco disco = parseDisco(discoData);
                discos.add(disco);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return discos;
    }

    // Método para guardar una lista de discos en un archivo
    public static void guardarDiscosEnArchivo(List<Disco> discos, String archivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Disco disco : discos) {
                writer.write(disco.toString() + "\n"); // Aquí se guarda la representación del disco en el archivo
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    private static Cantante parseCantante(String cantanteData) {
       String[] datos = cantanteData.split(",");
    if (datos.length == 12) {
        int codigo = Integer.parseInt(datos[0]);
        String nombre = datos[1];
        String apellido = datos[2];
        int edad = Integer.parseInt(datos[3]);
        String nacionalidad = datos[4];
        double salario = Double.parseDouble(datos[5]);
        String nombreArtistico = datos[6];
        String generoMusical = datos[7];
        int numeroDeSencillos = Integer.parseInt(datos[8]);
        int numeroDeConciertos = Integer.parseInt(datos[9]);
        int numeroDeGiras = Integer.parseInt(datos[10]);
        
        return new Cantante(nombreArtistico, generoMusical, numeroDeSencillos, numeroDeConciertos, numeroDeGiras, codigo, nombre, apellido, edad, nacionalidad, salario);
    } else {
         
        return null;
    }
}

    private static Disco parseDisco(String discoData) {
         String[] datos = discoData.split(",");
    if (datos.length == 3) {
        int codigo = Integer.parseInt(datos[0]);
        String nombre = datos[1];
        int anioLanzamiento = Integer.parseInt(datos[2]);
       
        return new Disco(codigo, nombre, anioLanzamiento);
    } else {
       
        return null;
    }
}
    
   

}


    