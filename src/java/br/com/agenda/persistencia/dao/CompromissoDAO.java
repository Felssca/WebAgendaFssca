/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.persistencia.dao;

import br.com.agenda.action.vo.VOCompromisso;
import br.com.agenda.action.vo.VOTipoCompromisso; 
import br.com.agenda.action.vo.VOSubTipoCompromisso;
import br.com.agenda.exception.AgendaExceptions;
import br.com.agenda.persistencia.HibernateUtility;
import br.com.agenda.seguranca.filtros.ControleSessao;
import br.com.agenda.util.AgendaProperties;
import br.com.agenda.util.Constantes;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Felipe
 */
public class CompromissoDAO {

    AgendaProperties propriedades = new AgendaProperties();
    AgendaExceptions execao = new AgendaExceptions();

    public ArrayList<VOCompromisso> pesquisaCompromisso(String mes, String ano, Integer id, String tipoOrdem, String condicao)
            throws Exception, HibernateException {

        ArrayList lista = new ArrayList();
        Session sessao = HibernateUtility.getSession();
        StringBuilder select = new StringBuilder("");
        Query query = null;

        select.append("from br.com.agenda.action.vo.VOCompromisso AS compromisso WHERE compromisso.idUsuario=:usuario ");

        /*
         * Listar o compromisso a ser editado ou visualizado
         */

        if (condicao.equals(Constantes.EDITAR) || condicao.equals(Constantes.LISTAR_SELETIVA)) {

            select.append("AND compromisso.id=:id");
            query = sessao.createQuery(select.toString());
            query.setInteger("id", id);
        }

        /*
         * Listar todos os compromissos
         */

        if (condicao.equals(Constantes.LISTAR_TUDO)) {
            select.append("AND MONTH(compromisso.dtData)=:mes AND YEAR(compromisso.dtData)=:ano ");
            select.append("ORDER BY compromisso.idTipoCompromisso asc,compromisso.dtData asc,compromisso.titulo asc");
            query = sessao.createQuery(select.toString());
            query.setString("mes", mes);
            query.setString("ano", ano);

        }


        /*
         * Listar todos os ordenados por
         */
        if (condicao.equals(Constantes.LISTAR_ORDER_BY)) {
            if (tipoOrdem.equals("titulo")) {
                select.append("AND MONTH(compromisso.dtData)=:mes AND YEAR(compromisso.dtData)=:ano ");
                select.append("ORDER BY compromisso.titulo asc,compromisso.idTipoCompromisso asc,compromisso.dtData asc");
            }
            query = sessao.createQuery(select.toString());

            query.setString("mes", mes);
            query.setString("ano", ano);
        }





        query.setInteger("usuario", ControleSessao.ID_USUARIO);

        lista = (ArrayList) query.list();
        sessao.close();
        return lista;

    }

    public ArrayList<VOCompromisso> pesquisarCompromissoSeletivo(Integer subTipo, String mes, String ano) throws Exception, HibernateException {
        ArrayList lista = new ArrayList();
        Session sessao = HibernateUtility.getSession();
        StringBuffer select = new StringBuffer("");
        Query query = null;
        select.append("from br.com.agenda.action.vo.VOCompromisso AS compromisso WHERE compromisso.idUsuario=:usuario ");
        select.append("AND MONTH(compromisso.dtData)=:mes AND YEAR(compromisso.dtData)=:ano AND compromisso.idSubTipoCompromisso=:subTipo");
        select.append("ORDER BY compromisso.titulo asc,compromisso.idTipoCompromisso asc,compromisso.dtData asc");
        query.setInteger("usuario", ControleSessao.ID_USUARIO);
        query.setString("mes", mes);
        query.setString("ano", ano);
        query.setInteger("subtipo", subTipo);
        query = sessao.createQuery(select.toString());
        return lista;

    }

    public VOCompromisso pesquisarCompromissoSeletivoUnitario(Integer id) throws Exception, HibernateException {
        VOCompromisso vo = new VOCompromisso();
        Session sessao = HibernateUtility.getSession();
        StringBuilder select = new StringBuilder("");
        Query query = null;
        select.append("from br.com.agenda.action.vo.VOCompromisso AS compromisso WHERE compromisso.idUsuario=:usuario ");
        select.append("AND compromisso.id=:id");
        query = sessao.createQuery(select.toString());
        query.setInteger("usuario", ControleSessao.ID_USUARIO);
        query.setInteger("id", id);
        vo = (VOCompromisso) query.uniqueResult();
        sessao.close();
        return vo;

    }

    public int cadastrarCompromisso(VOCompromisso compromisso) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction tx = null;
        int idRetorno = 0;
        try {
            tx = sessao.beginTransaction();
            sessao.save(compromisso);
            idRetorno = compromisso.getId();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            
            execao.logarErro(this.getClass().getName(), propriedades.getErroSqlInsercao(), e);
        } finally {
            sessao.close();
            

        }

        return idRetorno;
    }

    public void deletarCompromisso(VOCompromisso compromisso) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        try {
            sessao.delete(compromisso);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            execao.logarErro(this.getClass().getName(), propriedades.getErroSqlInsercao(), e);
        } finally {
            sessao.close();
        }
    }

    public void atualizarCompromisso(VOCompromisso compromisso) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction tx = sessao.beginTransaction();
        try {
            sessao.update(compromisso);
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

    /**
     *
     * Ações referentes a tablea tipo_compromisso
     */
    public void cadastrarTipoCompromisso(VOTipoCompromisso tipoCompromisso) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction tx = null;
        try {
            tx = sessao.beginTransaction();
            sessao.save(tipoCompromisso);
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

    public ArrayList pesquisaTipoCompromisso() throws Exception {
        ArrayList lista = null;
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(VOTipoCompromisso.class);
        lista = (ArrayList) select.list();
        sessao.close();
        return lista;

    }

    public String pesquisarDescTipo(Integer id) throws Exception, HibernateException {

        String descTipo;
        String select;
        Query query = null;
        Session sessao = HibernateUtility.getSession();
        select = "desc_tipo_compromisso from br.com.agenda.action.vo.VOTipoCompromisso AS compromisso WHERE compromisso.id=:id ";
        query = sessao.createQuery(select);
        query.setInteger("id", id);
        descTipo = query.uniqueResult().toString();

        return descTipo;
    }

    public void deletarTipoCompromisso(VOTipoCompromisso tipoCompromisso) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        try {
            sessao.delete(tipoCompromisso);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            execao.logarErro(this.getClass().getName(), propriedades.getErroSqlInsercao(), e);
        } finally {
            sessao.close();
        }
    }

    public void cadastrarSubTipoCompromisso(VOSubTipoCompromisso subTipoCompromisso) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction tx = null;
        Integer usuario = ControleSessao.ID_USUARIO;
        subTipoCompromisso.setIdUsuario(usuario);
        try {
            tx = sessao.beginTransaction();
            sessao.save(subTipoCompromisso);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            sessao.close();
        }


    }

    /**
     *
     * Ações referentes a tablea sub_tipo_compromisso
     */
    public ArrayList pesquisaSubTipoCompromisso(Integer tipo) throws Exception {
        ArrayList lista = null;
        Integer idUsuario  = ControleSessao.ID_USUARIO;
        Session sessao = HibernateUtility.getSession();
        Criteria select = sessao.createCriteria(VOSubTipoCompromisso.class);
        select.add(Restrictions.eq("idTipoCompromisso", tipo));
        select.add(Restrictions.eq("idUsuario", idUsuario));
        select.addOrder(Order.asc("descSubTipoCompromisso"));
        lista = (ArrayList) select.list();
        sessao.close();
        return lista;

    }
}
