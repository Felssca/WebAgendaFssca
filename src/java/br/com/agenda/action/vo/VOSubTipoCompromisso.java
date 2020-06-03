/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action.vo;

/**
 *
 * @author Felipe
 */
public class VOSubTipoCompromisso {
    
    private int id;
    private int idTipoCompromisso;
    private String descSubTipoCompromisso;
    private int idUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipoCompromisso() {
        return idTipoCompromisso;
    }

    public void setIdTipoCompromisso(int idTipoCompromisso) {
        this.idTipoCompromisso = idTipoCompromisso;
    }

    public String getDescSubTipoCompromisso() {
        return descSubTipoCompromisso;
    }

    public void setDescSubTipoCompromisso(String descSubTipoCompromisso) {
        this.descSubTipoCompromisso = descSubTipoCompromisso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
