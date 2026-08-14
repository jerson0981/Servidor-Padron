/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import Logica.ServicioPadron;
import Presentacion.ServidorHTTP;
import Presentacion.ServidorTCP;
import Repositorio.RepositorioDistritos;
import Repositorio.RepositorioPadron;

/**
 *
 * @author jerso
 */


public class App {

    public static void main(String[] args) {

        // =========================================
        // RUTAS DE LOS ARCHIVOS
        // =========================================

         String archivoPadron =
                "C:\\Users\\jerso\\Downloads\\padron_completo\\PADRON_COMPLETO.txt";

        String archivoDistritos =
                "C:\\Users\\jerso\\Downloads\\padron_completo\\distelec.txt";


        // =========================================
        // REPOSITORIOS
        // =========================================

        RepositorioPadron repoPadron =
                new RepositorioPadron(
                        archivoPadron
                );

        RepositorioDistritos repoDistritos =
                new RepositorioDistritos(
                        archivoDistritos
                );


        // =========================================
        // LOGICA
        // =========================================

        ServicioPadron servicio =
                new ServicioPadron(
                        repoPadron,
                        repoDistritos
                );


        // =========================================
        // SERVIDOR TCP - PUERTO 5000
        // =========================================

        ServidorTCP servidorTCP =
                new ServidorTCP(
                        5000,
                        servicio
                );


        // =========================================
        // SERVIDOR HTTP - PUERTO 8080
        // =========================================

        ServidorHTTP servidorHTTP =
                new ServidorHTTP(
                        8080,
                        servicio
                );


        // =========================================
        // INICIAR TCP EN OTRO HILO
        // =========================================

        Thread hiloTCP = new Thread(() -> {

            servidorTCP.iniciar();

        });

        hiloTCP.start();


        // =========================================
        // INICIAR HTTP
        // =========================================

        servidorHTTP.iniciar();


        System.out.println();
        System.out.println(
                "================================="
        );

        System.out.println(
                " SERVIDOR PADRON EN FUNCIONAMIENTO"
        );

        System.out.println(
                "================================="
        );

        System.out.println(
                "TCP  -> puerto 5000"
        );

        System.out.println(
                "HTTP -> puerto 8080"
        );
    }
}