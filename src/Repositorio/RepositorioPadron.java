/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;
import entidades.Persona;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author jerso
 */
public class RepositorioPadron {
 
    private String rutaArchivo;

    public RepositorioPadron(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Persona buscarPorCedula(String cedula) throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 7 &&
                        datos[0].trim().equals(cedula)) {

                    return new Persona(
                            datos[0].trim(),
                            datos[1].trim(),
                            datos[4].trim(),
                            datos[5].trim(),
                            datos[6].trim()
                    );
                }
            }
        }

        return null;
    }
}   

