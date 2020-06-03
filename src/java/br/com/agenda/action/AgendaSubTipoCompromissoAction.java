/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action;

import br.com.agenda.action.vo.VOTipoCompromisso;
import br.com.agenda.action.vo.VOSubTipoCompromisso;
import br.com.agenda.persistencia.dao.CompromissoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author Felipe
 */
public class AgendaSubTipoCompromissoAction extends DispatchAction {
    
     public ActionForward cadastrarSubTipoCompromisso(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm cadastro = (DynaActionForm) form;
        VOSubTipoCompromisso vo = new VOSubTipoCompromisso();
        Integer idTipoCompromisso = Integer.parseInt(req.getParameter("idTipoCompromisso"));
        vo.setDescSubTipoCompromisso((String)(cadastro.get("descSubTipoCompromisso")));
        vo.setIdTipoCompromisso(idTipoCompromisso);
        CompromissoDAO dao = new CompromissoDAO();
        dao.cadastrarSubTipoCompromisso(vo);
        return mapping.findForward("sucesso");

    }
      public ActionForward deletarSubTipoCompromisso(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {

        Integer id = Integer.parseInt(req.getParameter("id"));
        
        VOTipoCompromisso vo = new VOTipoCompromisso();
        vo.setId(id);
        
        CompromissoDAO compromisso = new CompromissoDAO();
        compromisso.deletarTipoCompromisso(vo);
        req.setAttribute("MSG", "Arquivo deletado");
        return mapping.findForward("deletado");
    }

}
