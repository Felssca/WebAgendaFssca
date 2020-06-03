/*
 * HibernateUtility.java
 *
 * Created on 18/09/2007, 11:49:18
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtility {

    private static SessionFactory factory;
    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
             factory = null;
             System.out.print(e);
        }
    }

    public static Session getSession() throws Exception{  
        return  factory.openSession();
    
      
     
        
   
    }
}
