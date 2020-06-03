/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.negocio;

import br.com.agenda.action.vo.VOCompromisso;
import br.com.agenda.action.vo.VOCompromissoReplicado;
import br.com.agenda.persistencia.dao.CompromissoDAO;
import br.com.agenda.persistencia.dao.CompromissoReplicadoDAO;
import java.util.ArrayList;

/**
 *
 * @author Felipessca
 */
public class FachadaCompromissoConfig {

    public ArrayList<VOCompromisso> listarCompromissosReplicados() throws Exception {
        ArrayList<VOCompromissoReplicado> lista = new ArrayList<VOCompromissoReplicado>();
        ArrayList<VOCompromisso> listaCompromisso = new ArrayList<VOCompromisso>();
        CompromissoDAO daoCompromisso = new CompromissoDAO();
        VOCompromissoReplicado voReplicado = new VOCompromissoReplicado();
        CompromissoReplicadoDAO dao = new CompromissoReplicadoDAO();
        lista = (ArrayList) dao.pesquisarCompromissoReplicadoLista();
        
        for (int i = 0; i < lista.size(); i++) {
            voReplicado = (VOCompromissoReplicado) lista.get(i);
            listaCompromisso.add(daoCompromisso.pesquisarCompromissoSeletivoUnitario(voReplicado.getId()));

        }

        return listaCompromisso;
    }
}
