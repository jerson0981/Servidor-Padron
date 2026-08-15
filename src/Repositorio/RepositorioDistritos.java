/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositorio;

import entidades.Distritoelectoral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author jerso
 */

public class RepositorioDistritos {

    private final String rutaArchivo;

    public RepositorioDistritos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Distritoelectoral buscarPorCodigo(String codigo)
            throws IOException {

        File archivo = new File(rutaArchivo);

        // Verificar que el archivo exista
        if (!archivo.exists()) {
            throw new IOException(
                    "No se encontró el archivo de distritos: "
                    + rutaArchivo
            );
        }

        // Verificar que la ruta corresponda a un archivo
        if (!archivo.isFile()) {
            throw new IOException(
                    "La ruta de distritos no corresponde a un archivo válido."
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

                // Validar cantidad mínima de campos
                if (datos.length < 4) {

                    System.err.println(
                            "Advertencia: línea inválida en distelec.txt"
                    );

                    continue;
                }

                if (datos[0].trim().equals(codigo)) {

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