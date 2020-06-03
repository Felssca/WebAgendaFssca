/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action.vo;

import java.util.Collection;

/**
 *
 * @author Felipe
 */
public class VOTipoCompromisso {
private Integer id;
private Collection compromisso;
private String desc_tipo_compromisso;


    public Collection getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(Collection compromisso) {
        this.compromisso = compromisso;
    }


    public String getDesc_tipo_compromisso() {
        return desc_tipo_compromisso;
    }

    public void setDesc_tipo_compromisso(String desc_tipo_compromisso) {
        this.desc_tipo_compromisso = desc_tipo_compromisso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

 
}
