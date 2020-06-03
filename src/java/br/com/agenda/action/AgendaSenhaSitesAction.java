/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action;

import br.com.agenda.persistencia.dao.ConfigDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * Classe que mostra a senha e os sites 
 * @author Felipessca
 */
public class AgendaSenhaSitesAction extends DispatchAction{
    
     @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ConfigDAO conf = new ConfigDAO();
        ArrayList listaConfig = null;
        listaConfig = conf.pesquisa("pesquisarCategoria");
        req.setAttribute("listaCategoria", listaConfig);
        return mapping.findForward("cadInicial");
    }
    
    
}
