/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action.vo;

/**
 *
 * @author Felipessca
 */
public class VOCompromissoReplicado {

    private int id;
    private int idCompromisso;
    private boolean replicado;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCompromisso() {
        return idCompromisso;
    }

    public void setIdCompromisso(int idCompromisso) {
        this.idCompromisso = idCompromisso;
    }

    public boolean isReplicado() {
        return replicado;
    }

    public void setReplicado(boolean replicado) {
        this.replicado = replicado;
    }

}
