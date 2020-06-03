/*
 * ConfigDAO.java
 * 
 * Created on 25/09/2007, 15:14:07
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.persistencia.dao;


import br.com.agenda.action.vo.VOConfigCategoria;
import br.com.agenda.persistencia.HibernateUtility;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author FelipeSSCA
 */
public class ConfigDAO {
      public void insert(VOConfigCategoria conf) throws Exception {
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        sessao.save(conf);
        transaction.commit();
        sessao.close();
    }
      public ArrayList pesquisa(String condicao) 
            throws Exception {
        ArrayList lista = null;
        Session sessao = HibernateUtility.getSession();
        Transaction tx = sessao.beginTransaction();
        if (condicao.equals("pesquisarCategoria")) {
          Criteria select = sessao.createCriteria(VOConfigCategoria.class);
          select.addOrder(Order.asc("tipo"));
          lista = (ArrayList) select.list();
        }
        if(condicao.equals("pesquisarAniversario")){
        Criteria select = sessao.createCriteria(VOConfigCategoria.class);
        
        }
        tx.commit();
        sessao.close();

        return lista;
    }
      public void delete(VOConfigCategoria config)throws Exception{
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        sessao.delete(config);
        transaction.commit();
        sessao.close();
      
      }

}
