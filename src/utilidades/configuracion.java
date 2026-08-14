/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 *
 * @author jerso
 */
public class configuracion {

    // Puertos utilizados por los servidores
    public static final int PUERTO_TCP = 5000;
    public static final int PUERTO_HTTP = 8080;

    // Rutas de los archivos utilizados por el sistema
    public static final String ARCHIVO_PADRON =
            "C:\\Users\\jerso\\Downloads\\padron_completo\\PADRON_COMPLETO.txt";

    public static final String ARCHIVO_DISTRITOS =
            "C:\\Users\\jerso\\Downloads\\padron_completo\\distelec.txt";

    // Evita crear objetos de esta clase
    private configuracion() {
    }
}
