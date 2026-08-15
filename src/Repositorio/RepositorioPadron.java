/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import entidades.Persona;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author jerso
 */

public class RepositorioPadron {

    private final String rutaArchivo;

    public RepositorioPadron(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Persona buscarPorCedula(String cedula) throws IOException {

        File archivo = new File(rutaArchivo);

        // Verificar que el archivo exista
        if (!archivo.exists()) {
            throw new IOException(
                    "No se encontró el archivo del padrón: "
                    + rutaArchivo
            );
        }

        // Verificar que sea un archivo válido
        if (!archivo.isFile()) {
            throw new IOException(
                    "La ruta del padrón no corresponde a un archivo válido."
            );
        }

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(archivo)
                     )) {

            String linea;

            while ((linea = br.readLine()) != null) {

                // Ignorar líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos =
                        linea.split(",", -1);

                
                if (datos.length < 7) {

                    System.err.println(
                            "Advertencia: línea inválida en PADRON_COMPLETO.txt"
                    );

                    continue;
                }

                if (datos[0].trim().equals(cedula)) {

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