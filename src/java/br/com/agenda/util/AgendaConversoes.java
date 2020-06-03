/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.util;

/**
 *
 * @author Felipe
 */
public class AgendaConversoes {

    public String converterCompromissoCumprido(Boolean valor) {
        String valorConvertido = "";
        if (valor == false) {
            valorConvertido = "Pendente";
        } else {
            valorConvertido = "OK";
        }

        return valorConvertido;


    }
    public String converterFormaPagamento(Integer formaPagamento){
    String formaConvertida = "";
    switch(formaPagamento){
        case 0:formaConvertida = "A vista";break;
        case 1:formaConvertida = "Cheque";break;
        case 2:formaConvertida = "Cartão de Crédito";break;
     
    }
        return formaConvertida;
    }
}
