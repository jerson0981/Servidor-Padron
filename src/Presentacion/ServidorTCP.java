/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Logica.ServicioPadron;
import dto.Errordto;
import dto.Personadto;
import utilidades.JsonUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// IMPORTS PARA CONCURRENCIA
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jerso
 */


public class ServidorTCP {

    private final int puerto;
    private final ServicioPadron servicioPadron;

    // Pool de hilos para atender varios clientes
    private final ExecutorService pool;


    // ============================================
    // CONSTRUCTOR
    // ============================================

    public ServidorTCP(
            int puerto,
            ServicioPadron servicioPadron
    ) {

        this.puerto = puerto;
        this.servicioPadron = servicioPadron;

        // Pool con capacidad para 10 clientes
        this.pool = Executors.newFixedThreadPool(10);
    }


    // ============================================
    // INICIAR SERVIDOR
    // ============================================

    public void iniciar() {

        try (
                ServerSocket servidor =
                        new ServerSocket(puerto)
        ) {

            System.out.println(
                    "Servidor TCP iniciado."
            );

            System.out.println(
                    "Escuchando en el puerto: "
                    + puerto
            );

            System.out.println(
                    "Pool de hilos disponible: 10"
            );


            while (true) {

                // Esperar una conexion
                Socket cliente =
                        servidor.accept();


                System.out.println(
                        "Cliente conectado: "
                        + cliente.getInetAddress()
                );


                /*
                 * CONCURRENCIA
                 *
                 * El cliente se envia al pool.
                 * El servidor puede continuar
                 * aceptando otros clientes.
                 */

                pool.submit(() -> {

                    atenderCliente(cliente);

                });
            }

        } catch (IOException e) {

            System.out.println(
                    "Error en el servidor TCP: "
                    + e.getMessage()
            );

        } finally {

            pool.shutdown();
        }
    }


    // ============================================
    // ATENDER CLIENTE
    // ============================================

    private void atenderCliente(
            Socket cliente
    ) {

        // Obtener el nombre del hilo
        String nombreHilo =
                Thread.currentThread().getName();


        System.out.println(
                nombreHilo
                + " atendiendo cliente."
        );


        try (
                BufferedReader entrada =
                        new BufferedReader(
                                new InputStreamReader(
                                        cliente.getInputStream()
                                )
                        );

                PrintWriter salida =
                        new PrintWriter(
                                cliente.getOutputStream(),
                                true
                        )
        ) {

            // Recibir solicitud
            String solicitud =
                    entrada.readLine();


            System.out.println(
                    nombreHilo
                    + " - Solicitud recibida: "
                    + solicitud
            );


            // Procesar solicitud
            String respuesta =
                    procesarSolicitud(
                            solicitud
                    );


            // Enviar respuesta
            salida.println(respuesta);


            System.out.println(
                    nombreHilo
                    + " - Respuesta enviada."
            );


        } catch (IOException e) {

            System.out.println(
                    nombreHilo
                    + " - Error atendiendo cliente: "
                    + e.getMessage()
            );

        } finally {

            try {

                cliente.close();

                System.out.println(
                        nombreHilo
                        + " - Cliente desconectado."
                );

            } catch (IOException e) {

                System.out.println(
                        "Error cerrando cliente: "
                        + e.getMessage()
                );
            }
        }
    }


    // ============================================
    // PROCESAR SOLICITUD
    // ============================================

    private String procesarSolicitud(
            String solicitud
    ) {


        // ========================================
        // SOLICITUD VACIA
        // ========================================

        if (solicitud == null
                || solicitud.trim().isEmpty()) {

            Errordto error =
                    new Errordto(
                            true,
                            400,
                            "Solicitud vacia."
                    );

            return JsonUtil.errorToJson(error);
        }


        // ========================================
        // SEPARAR GET|CEDULA
        // ========================================

        String[] partes =
                solicitud.split("\\|", -1);


        if (partes.length != 2) {

            Errordto error =
                    new Errordto(
                            true,
                            400,
                            "Solicitud TCP incompleta o invalida."
                    );

            return JsonUtil.errorToJson(error);
        }


        String comando =
                partes[0].trim();

        String cedula =
                partes[1].trim();


        // ========================================
        // VALIDAR COMANDO
        // ========================================

        if (!comando.equalsIgnoreCase("GET")) {

            Errordto error =
                    new Errordto(
                            true,
                            400,
                            "Comando TCP desconocido."
                    );

            return JsonUtil.errorToJson(error);
        }


        // ========================================
        // VALIDAR CEDULA VACIA
        // ========================================

        if (cedula.isEmpty()) {

            Errordto error =
                    new Errordto(
                            true,
                            400,
                            "La cedula no puede estar vacia."
                    );

            return JsonUtil.errorToJson(error);
        }


        // ========================================
        // VALIDAR FORMATO
        // ========================================

        if (!cedula.matches("\\d+")) {

            Errordto error =
                    new Errordto(
                            true,
                            400,
                            "Formato de cedula invalido."
                    );

            return JsonUtil.errorToJson(error);
        }


        // ========================================
        // CONSULTAR SERVICIO PADRON
        // ========================================

        try {

            Personadto persona =
                    servicioPadron.consultar(
                            cedula
                    );


            // Persona no encontrada
            if (persona == null) {

                Errordto error =
                        new Errordto(
                                true,
                                404,
                                "No se encontro una persona con la cedula indicada."
                        );

                return JsonUtil.errorToJson(error);
            }


            // Persona encontrada
            return JsonUtil.personaToJson(
                    persona
            );


        } catch (IOException e) {

            Errordto error =
                    new Errordto(
                            true,
                            500,
                            "Error durante la lectura de los archivos."
                    );

            return JsonUtil.errorToJson(error);


        } catch (Exception e) {

            Errordto error =
                    new Errordto(
                            true,
                            500,
                            "Error inesperado durante el procesamiento."
                    );

            return JsonUtil.errorToJson(error);
        }
    }
}