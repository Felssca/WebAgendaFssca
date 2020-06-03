/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action;

import br.com.agenda.action.vo.VOTipoCompromisso;
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
public class AgendaTipoCompromissoAction extends DispatchAction {
    
     public ActionForward cadastrarTipoCompromisso(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm cadastro = (DynaActionForm) form;
        VOTipoCompromisso vo = new VOTipoCompromisso();
        vo.setDesc_tipo_compromisso((String)(cadastro.get("desc_tipo_compromisso")));
        CompromissoDAO dao = new CompromissoDAO();
        dao.cadastrarTipoCompromisso(vo);
        return mapping.findForward("sucesso");

    }
      public ActionForward deletarTipoCompromisso(ActionMapping mapping, ActionForm form, HttpServletRequest req,
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
