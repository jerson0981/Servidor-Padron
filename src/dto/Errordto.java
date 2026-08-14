/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author jerso
 */


public class Errordto {

    private boolean error;
    private int codigo;
    private String mensaje;

    public Errordto(boolean error, int codigo, String mensaje) {
        this.error = error;
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public boolean isError() {
        return error;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }
}
