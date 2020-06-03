/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action.vo;

import java.util.Collection;

/**
 *
 * @author Felipessca
 */
public class VOTipo {

    private String tipoSeparador;
    private Collection agenda;

    public String getTipoSeparador() {
        return tipoSeparador;
    }

    public void setTipoSeparador(String tipoSeparador) {
        this.tipoSeparador = tipoSeparador;
    }

    public Collection getAgenda() {
        return agenda;
    }

    public void setAgenda(Collection agenda) {
        this.agenda = agenda;
    }
}
