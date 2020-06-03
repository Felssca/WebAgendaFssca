/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.seguranca;

import br.com.agenda.util.AgendaCriptografia;
import br.com.agenda.persistencia.dao.LoginDAO;
import br.com.agenda.seguranca.filtros.ControleSessao;
import br.com.agenda.util.Constantes;
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
public class AgendaLogin extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return mapping.findForward("inicial");
    }

    public ActionForward cadastrar(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm cadastro = (DynaActionForm) form;
        VOLogin vo = new VOLogin();
        vo.setUsuario((String) cadastro.get("usuario"));
        String senha = AgendaCriptografia.criptografia((String) cadastro.get("senha"));
        vo.setSenha(senha);
        //vo.setPermicao(Constantes.PERMICAO_USUARIO);
        vo.setPermicao(Constantes.PERMICAO_ADMIN);
        LoginDAO dao = new LoginDAO();
        dao.insert(vo);
        req.setAttribute("msg", "Usuario cadastrado");
        return mapping.findForward("cadastro");
    }

    public ActionForward consultar(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm consulta = (DynaActionForm) form;
        VOLogin vo = new VOLogin();
        String senha = AgendaCriptografia.criptografia((String) consulta.get("senha"));
        vo.setUsuario(consulta.getString("usuario"));
        vo.setSenha(senha);
        LoginDAO dao = new LoginDAO();
        vo = dao.consulta(vo);
        if (vo == null) {
            req.setAttribute("erro", Constantes.ACESSO_LOGON_ERRO);
            return mapping.findForward("erro");
        } else {
            ControleSessao.manterSessao(vo, req);
        }
        return mapping.findForward("sucesso");
    }
}
