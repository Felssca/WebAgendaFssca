/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.agenda.action;

import br.com.agenda.action.vo.VOConfigCategoria;
import br.com.agenda.persistencia.dao.ConfigDAO;
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
public class AgendaCategoriaAction extends DispatchAction {
    
    
    public ActionForward cadastro(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm cadastro = (DynaActionForm) form;
        VOConfigCategoria vo = new VOConfigCategoria();
        vo.setTipo((String) cadastro.get("tipo"));
        ConfigDAO config = new ConfigDAO();
        config.insert(vo);
        req.setAttribute("msg", "Cadastro Efetuado");
        return mapping.findForward("cadastro");
    }

}
