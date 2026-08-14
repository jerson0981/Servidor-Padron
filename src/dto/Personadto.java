/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author jerso
 */
public class Personadto {

    private String cedula;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String codigoElectoral;
    private String provincia;
    private String canton;
    private String distrito;

    public Personadto(String cedula,
                      String nombre,
                      String primerApellido,
                      String segundoApellido,
                      String codigoElectoral,
                      String provincia,
                      String canton,
                      String distrito) {

        this.cedula = cedula;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.codigoElectoral = codigoElectoral;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public String getCodigoElectoral() {
        return codigoElectoral;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCanton() {
        return canton;
    }

    public String getDistrito() {
        return distrito;
    }
}
