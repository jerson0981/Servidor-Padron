/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;
import entidades.Distritoelectoral;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author jerso
 */
public class RepositorioDistritos {
   

    private String rutaArchivo;

    public RepositorioDistritos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Distritoelectoral buscarPorCodigo(String codigo)
            throws IOException {

        try (BufferedReader br =
                     new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 4 &&
                        datos[0].trim().equals(codigo)) {

                    return new Distritoelectoral(
                            datos[0].trim(),
                            datos[1].trim(),
                            datos[2].trim(),
                            datos[3].trim()
                    );
                }
            }
        }

        return null;
    }
}
