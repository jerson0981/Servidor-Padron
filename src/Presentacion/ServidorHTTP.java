/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Logica.ServicioPadron;
import dto.Errordto;
import dto.Personadto;
import utilidades.JsonUtil;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author jerso
 */

public class ServidorHTTP {

    private final int puerto;
    private final ServicioPadron servicioPadron;
    private HttpServer servidor;

    public ServidorHTTP(int puerto, ServicioPadron servicioPadron) {
        this.puerto = puerto;
        this.servicioPadron = servicioPadron;
    }

    public void iniciar() {

        try {

            servidor = HttpServer.create(
                    new InetSocketAddress(puerto),
                    0
            );

            servidor.createContext(
                    "/padron",
                    this::atenderSolicitud
            );

            servidor.start();

            System.out.println("Servidor HTTP iniciado.");
            System.out.println(
                    "Escuchando en el puerto: " + puerto
            );

        } catch (IOException e) {

            System.out.println(
                    "Error iniciando servidor HTTP: "
                    + e.getMessage()
            );
        }
    }

    private void atenderSolicitud(HttpExchange exchange)
            throws IOException {

        try {

            // =====================================
            // VALIDAR METODO HTTP
            // =====================================

            String metodo =
                    exchange.getRequestMethod();

            if (!metodo.equalsIgnoreCase("GET")) {

                Errordto error = new Errordto(
                        true,
                        405,
                        "Metodo HTTP no permitido."
                );

                enviarRespuesta(
                        exchange,
                        405,
                        JsonUtil.errorToJson(error)
                );

                return;
            }


            // =====================================
            // OBTENER RUTA
            // =====================================

            String ruta =
                    exchange.getRequestURI().getPath();

            /*
             * La ruta esperada es:
             *
             * /padron/703250014
             */

            String[] partes =
                    ruta.split("/");


            // =====================================
            // VALIDAR RUTA
            // =====================================

            if (partes.length != 3
                    || !partes[1].equals("padron")) {

                Errordto error = new Errordto(
                        true,
                        404,
                        "Ruta HTTP inexistente."
                );

                enviarRespuesta(
                        exchange,
                        404,
                        JsonUtil.errorToJson(error)
                );

                return;
            }


            // =====================================
            // OBTENER CEDULA
            // =====================================

            String cedula =
                    partes[2].trim();


            // =====================================
            // VALIDAR CEDULA VACIA
            // =====================================

            if (cedula.isEmpty()) {

                Errordto error = new Errordto(
                        true,
                        400,
                        "La cedula no puede estar vacia."
                );

                enviarRespuesta(
                        exchange,
                        400,
                        JsonUtil.errorToJson(error)
                );

                return;
            }


            // =====================================
            // VALIDAR FORMATO DE CEDULA
            // =====================================

            if (!cedula.matches("\\d+")) {

                Errordto error = new Errordto(
                        true,
                        400,
                        "Formato de cedula invalido."
                );

                enviarRespuesta(
                        exchange,
                        400,
                        JsonUtil.errorToJson(error)
                );

                return;
            }


            // =====================================
            // CONSULTAR PADRON
            // =====================================

            Personadto persona =
                    servicioPadron.consultar(cedula);


            // =====================================
            // PERSONA NO ENCONTRADA
            // =====================================

            if (persona == null) {

                Errordto error = new Errordto(
                        true,
                        404,
                        "No se encontro una persona con la cedula indicada."
                );

                enviarRespuesta(
                        exchange,
                        404,
                        JsonUtil.errorToJson(error)
                );

                return;
            }


            // =====================================
            // PERSONA ENCONTRADA
            // =====================================

            String json =
                    JsonUtil.personaToJson(persona);

            enviarRespuesta(
                    exchange,
                    200,
                    json
            );


        } catch (IOException e) {

            Errordto error = new Errordto(
                    true,
                    500,
                    "Error durante la lectura de los archivos."
            );

            enviarRespuesta(
                    exchange,
                    500,
                    JsonUtil.errorToJson(error)
            );

        } catch (Exception e) {

            Errordto error = new Errordto(
                    true,
                    500,
                    "Error inesperado durante el procesamiento."
            );

            enviarRespuesta(
                    exchange,
                    500,
                    JsonUtil.errorToJson(error)
            );
        }
    }


    // =============================================
    // ENVIAR RESPUESTA HTTP
    // =============================================

    private void enviarRespuesta(
            HttpExchange exchange,
            int codigoEstado,
            String respuesta
    ) throws IOException {

        byte[] datos =
                respuesta.getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.getResponseHeaders().set(
                "Content-Type",
                "application/json; charset=UTF-8"
        );

        exchange.sendResponseHeaders(
                codigoEstado,
                datos.length
        );

        try (OutputStream salida =
                     exchange.getResponseBody()) {

            salida.write(datos);
        }
    }
}
