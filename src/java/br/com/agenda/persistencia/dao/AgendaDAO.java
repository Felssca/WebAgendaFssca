 /*
 * AgendaConsultaDAO.java
 *
 * Created on 18/09/2007, 15:57:08
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.persistencia.dao;

import br.com.agenda.action.vo.VOAgenda;
import br.com.agenda.persistencia.HibernateUtility;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author FelipeSSCA
 */
public class AgendaDAO  {

    public void cadastrarAgenda(VOAgenda agenda) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction tx = null;
        try {
            tx = sessao.beginTransaction();
            sessao.save(agenda);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            sessao.close();
        }

    }

    public ArrayList pesquisa(String condicao, HttpServletRequest req) throws Exception {
        ArrayList lista = null;

        try{
        Session sessao = HibernateUtility.getSession();
        Transaction tx = sessao.beginTransaction();
       

        if (condicao.equals("pesquisar")) {
            Criteria select = sessao.createCriteria(VOAgenda.class);
            String criterio = (String) req.getAttribute("criterio");
            String nomeBusca = (String) req.getAttribute("criterioBusca");
            select.add(Restrictions.like(criterio, nomeBusca + "%"));
            lista = (ArrayList) select.list();
        }
      
        if (condicao.equals("pesquisarID")) {
            String ID = (String) req.getAttribute("id");
            int id = Integer.parseInt(ID);
            Criteria select = sessao.createCriteria(VOAgenda.class);
            select.add(Restrictions.eq("id", id));
            lista = (ArrayList) select.list();

        }
        
       
        if (condicao.equals("pesquisarTudo")) {
            String tipo = (String) req.getAttribute("tipoSelecionado");
            Criteria select = sessao.createCriteria(VOAgenda.class);
            if (tipo.equals("Todos")) {
                select.addOrder(Order.asc("tipo"));
                select.addOrder(Order.asc("nome"));
            } else {
                select.addOrder(Order.asc("nome"));
                select.add(Restrictions.eq("tipo", tipo));
            }
            

            lista = (ArrayList) select.list();
            
        }
        tx.commit();
        sessao.close();
            }catch(HibernateException e){
        e.printStackTrace();
        }
        return lista;
    }

    public void update(VOAgenda agenda) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        sessao.update(agenda);
        transaction.commit();
        sessao.close();
    }

    public void detete(VOAgenda agenda) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        sessao.delete(agenda);
        transaction.commit();
        sessao.close();
    }
}
