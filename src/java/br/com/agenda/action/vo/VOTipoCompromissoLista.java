/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action.vo;

import java.util.ArrayList;
import java.util.Collection;


/**
 *
 * @author Felipe
 */
public class VOTipoCompromissoLista {
    
    private Integer tipoSeparador;
    private ArrayList compromisso;

    public ArrayList getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(ArrayList compromisso) {
        this.compromisso = compromisso;
    }

    public Integer getTipoSeparador() {
        return tipoSeparador;
    }

    public void setTipoSeparador(Integer tipoSeparador) {
        this.tipoSeparador = tipoSeparador;
    }
    

}
