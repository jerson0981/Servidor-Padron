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
import utilidades.configuracion;

/**
 *
 * @author jerso
 */

public class App {

    public static void main(String[] args) {

        // =========================================
        // REPOSITORIOS
        // =========================================

        RepositorioPadron repoPadron =
                new RepositorioPadron(
                        configuracion.ARCHIVO_PADRON
                );

        RepositorioDistritos repoDistritos =
                new RepositorioDistritos(
                        configuracion.ARCHIVO_DISTRITOS
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
        // SERVIDOR TCP
        // =========================================

        ServidorTCP servidorTCP =
                new ServidorTCP(
                        configuracion.PUERTO_TCP,
                        servicio
                );


        // =========================================
        // SERVIDOR HTTP
        // =========================================

        ServidorHTTP servidorHTTP =
                new ServidorHTTP(
                        configuracion.PUERTO_HTTP,
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


        // =========================================
        // INFORMACION DEL SERVIDOR
        // =========================================

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
                "TCP  -> puerto "
                + configuracion.PUERTO_TCP
        );

        System.out.println(
                "HTTP -> puerto "
                + configuracion.PUERTO_HTTP
        );
    }
}