/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.persistencia.dao;

import br.com.agenda.action.vo.VOCompromisso;
import br.com.agenda.action.vo.VOCompromissoReplicado;
import br.com.agenda.exception.AgendaExceptions;
import br.com.agenda.persistencia.HibernateUtility;
import br.com.agenda.util.AgendaProperties;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author FelipeSSCA
 */
public class CompromissoReplicadoDAO {

    AgendaProperties propriedades = new AgendaProperties();
    AgendaExceptions execao = new AgendaExceptions();
      /**
     *
     * Ações referentes a replica de registros compromissos
     */
    public boolean pesquisarCompromissoReplicado(int id) throws Exception {
        boolean replicado = false;

        String select;
        Query query = null;
        Session sessao = HibernateUtility.getSession();
        select = "Select replicado from br.com.agenda.action.vo.VOCompromissoReplicado AS replicadoCompromisso WHERE replicadoCompromisso.idCompromisso =:id ";
        query = sessao.createQuery(select);
        query.setInteger("id", id);
        Object resultado = query.uniqueResult();
        if (resultado != null) {
            replicado = (Boolean) resultado;
        }
        return replicado;


    }
      /**
     *
     * Ações referentes a replica de registros Lista compromissos
     */
    public ArrayList<VOCompromissoReplicado> pesquisarCompromissoReplicadoLista() throws Exception {
        ArrayList<VOCompromissoReplicado> replicado = new ArrayList<VOCompromissoReplicado>();
        String select;
        VOCompromisso compromisso;
        Query query = null;
        Session sessao = HibernateUtility.getSession();
        select = "Select idReplicado from br.com.agenda.action.vo.VOCompromissoReplicado AS replicadoCompromisso";
        query = sessao.createQuery(select);
        replicado = (ArrayList)query.list();
        return replicado;


    }
    /*
     * Ações referentes a replica de registros compromissos.Cadastra o idCompromisso para controle
     */

    public void cadastrarLogReplicado(VOCompromissoReplicado vo) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction tx = null;
        try {
            tx = sessao.beginTransaction();
            sessao.save(vo);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
           execao.logarErro(this.getClass().getName(), propriedades.getErroSqlInsercao(), e);
        } finally {
            sessao.close();
        }


    }

}
