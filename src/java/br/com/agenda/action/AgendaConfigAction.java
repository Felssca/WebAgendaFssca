/*
 * AgendaConfigAction.java
 *
 * Created on 25/09/2007, 14:56:42
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action;

import br.com.agenda.action.vo.VOConfigCategoria;
import br.com.agenda.persistencia.dao.ConfigDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Felipessca
 */
public class AgendaConfigAction extends DispatchAction {

    @Override
    public ActionForward unspecified (ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ConfigDAO config = new ConfigDAO();
        ArrayList lista = config.pesquisa("pesquisarCategoria");
        req.setAttribute("listaCategoria", lista);
        return mapping.findForward("cadInicial");
    }
    public ActionForward listaItensReplicados (ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ConfigDAO config = new ConfigDAO();
        ArrayList lista = config.pesquisa("pesquisarCategoria");
        req.setAttribute("listaCategoria", lista);
        return mapping.findForward("cadInicial");
    }

    public ActionForward cadastro(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm cadastro = (DynaActionForm) form;
        
        VOConfigCategoria vo = new VOConfigCategoria();
        vo.setTipo((String) cadastro.get("tipo"));
        ConfigDAO config = new ConfigDAO();
        config.insert(vo);
        
        req.setAttribute("msg", "Arquivo inserido");
       // ActionMessage msg= new ActionMessage("msg","reg.cadastro");
        return mapping.findForward("cadastro");
    }

    public ActionForward deletar(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm config =(DynaActionForm)form;
        
        Integer id = Integer.parseInt(req.getParameter("id"));
        ConfigDAO dao= new ConfigDAO();
        VOConfigCategoria vo= new VOConfigCategoria();
        vo.setId(id);
        dao.delete(vo);     
        req.setAttribute("msg", "Arquivo deletado");
        req.setAttribute(null, vo.getClass().toString());
        return mapping.findForward("deletado");
        
    }
     public ActionForward categoria(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
 
        return mapping.findForward("categoria");
    }
}