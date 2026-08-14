/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import dto.Personadto;
import entidades.Persona;
import entidades.Distritoelectoral;
import Repositorio.RepositorioPadron;
import Repositorio.RepositorioDistritos;

import java.io.IOException;

/**
 *
 * @author jerso
 */
public class ServicioPadron {
   

    private RepositorioPadron repositorioPadron;
    private RepositorioDistritos repositorioDistritos;

    public ServicioPadron(RepositorioPadron repositorioPadron,
                          RepositorioDistritos repositorioDistritos) {

        this.repositorioPadron = repositorioPadron;
        this.repositorioDistritos = repositorioDistritos;
    }

    public Personadto consultar(String cedula) throws IOException {

        Persona persona =
                repositorioPadron.buscarPorCedula(cedula);

        if (persona == null) {
            return null;
        }

        Distritoelectoral distrito =
                repositorioDistritos.buscarPorCodigo(
                        persona.getCodigoElectoral()
                );

        if (distrito == null) {
            return null;
        }

        return new Personadto(
                persona.getCedula(),
                persona.getNombre(),
                persona.getPrimerApellido(),
                persona.getSegundoApellido(),
                persona.getCodigoElectoral(),
                distrito.getProvincia(),
                distrito.getCanton(),
                distrito.getDistrito()
        );
    }
}
