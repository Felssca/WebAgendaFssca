/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.persistencia.dao;


import br.com.agenda.persistencia.HibernateUtility;
import br.com.agenda.seguranca.VOLogin;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author FelipeSSCA
 */
public class LoginDAO {



    public void insert(VOLogin login) throws Exception {
        
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        sessao.save(login);
        transaction.commit();
        sessao.close();
    }
     public VOLogin consulta(VOLogin login) throws Exception {
        
        Session sessao = HibernateUtility.getSession();
        Transaction transaction = sessao.beginTransaction();
        Criteria select = sessao.createCriteria(VOLogin.class);   
        select.add(Restrictions.eq("usuario",login.getUsuario()));
        select.add(Restrictions.eq("senha",login.getSenha()));
        VOLogin  vo= (VOLogin)select.uniqueResult();
        transaction.commit();
        sessao.close();
        return vo;
    }
}
