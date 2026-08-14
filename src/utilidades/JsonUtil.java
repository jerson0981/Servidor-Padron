/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;
import dto.Errordto;
import dto.Personadto;
/**
 *
 * @author jerso
 */


public class JsonUtil {

    public static String personaToJson(Personadto persona) {

        return "{"
                + "\"cedula\":\"" + persona.getCedula() + "\","
                + "\"nombre\":\"" + persona.getNombre() + "\","
                + "\"primerApellido\":\"" + persona.getPrimerApellido() + "\","
                + "\"segundoApellido\":\"" + persona.getSegundoApellido() + "\","
                + "\"codigoElectoral\":\"" + persona.getCodigoElectoral() + "\","
                + "\"provincia\":\"" + persona.getProvincia() + "\","
                + "\"canton\":\"" + persona.getCanton() + "\","
                + "\"distrito\":\"" + persona.getDistrito() + "\""
                + "}";
    }

    public static String errorToJson(Errordto error) {

        return "{"
                + "\"error\":" + error.isError() + ","
                + "\"codigo\":" + error.getCodigo() + ","
                + "\"mensaje\":\"" + error.getMensaje() + "\""
                + "}";
    }
}
