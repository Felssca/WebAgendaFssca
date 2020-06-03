/*
 * CadastroAction.java
 *
 * Created on 13/09/2007, 19:27:32
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action;

import br.com.agenda.action.vo.VOAgenda;
import br.com.agenda.exception.AgendaExceptions;
import br.com.agenda.persistencia.dao.AgendaDAO;
import br.com.agenda.util.AgendaData;
import br.com.agenda.util.AgendaImagem;
import br.com.agenda.persistencia.dao.ConfigDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;

/**
 *
 * @author Felipessca
 */
public class AgendaCadastroAction extends DispatchAction {

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ConfigDAO conf = new ConfigDAO();
        ArrayList listaConfig = null;
        listaConfig = conf.pesquisa("pesquisarCategoria");
        req.setAttribute("listaCategoria", listaConfig);
        return mapping.findForward("cadInicial");
    }

    public ActionForward cadastrar(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {

        DynaValidatorForm cadastro = (DynaValidatorForm) form;
        VOAgenda vo = new VOAgenda();
        AgendaData data = new AgendaData();


        String nome = ((String) cadastro.get("nome"));
        vo.setNome(nome.toUpperCase());
        vo.setTelefone((String) cadastro.get("telefone"));
        vo.setTelefone2((String) cadastro.get("telefone2"));
        vo.setTelefone3((String) cadastro.get("telefone3"));
        vo.setCelular((String) cadastro.get("celular"));
        vo.setCelular2((String) cadastro.get("celular2"));
        vo.setCelular3((String) cadastro.get("celular3"));
        vo.setEmail((String) cadastro.get("email"));
        vo.setEmail2((String) cadastro.get("email2"));
        vo.setEmail3((String) cadastro.get("email3"));
        vo.setEndereco((String) cadastro.get("endereco"));
        vo.setEndereco2((String) cadastro.get("endereco2"));
        vo.setEndereco3((String) cadastro.get("endereco3"));
        vo.setTipo((String) cadastro.get("tipo"));
        String dataAniver = ((String) cadastro.get("aniversario"));
        vo.setAniversario((Date) data.formataData(dataAniver));
        vo.setCarac((String) cadastro.get("carac"));
        //vo.setRegiao((String) cadastro.get("regiao"));
        vo.setMsn((String) cadastro.get("msn"));
        vo.setSkype((String) cadastro.get("skype"));
        vo.setOrkut((String) cadastro.get("orkut"));
        //  vo.setSite((String) cadastro.get("orkut"));

        vo.setFotoFile((FormFile) cadastro.get("fotoFile"));
        AgendaImagem util = new AgendaImagem();
        util.gravarFoto(vo.getFotoFile(), "Cadastro", "");
        vo.setFoto(util.getNomeArquivo());

          if (validarAgenda(vo, req) == false) {
            return mapping.findForward("erro");
        }

        AgendaDAO dao = new AgendaDAO();
        dao.cadastrarAgenda(vo);
        
         req.setAttribute("msg", "CadastroEfetuado");
         return mapping.findForward("cadastro");




        /*
         * Utilizando a fachada de negocio

        CadastroAgendaBO agenda = new CadastroAgendaBO();
        agenda.cadastrar(vo);
        req.setAttribute("msg",agenda.getMensagem());
        return mapping.findForward(agenda.getRequest());
         */

     
            
        
    }

    private boolean validarAgenda(VOAgenda vo, HttpServletRequest req) {
        AgendaExceptions validacao = new AgendaExceptions();
        Object key = null;
        if (validacao.verificarErrosAgenda(vo).isEmpty()) {
            return true;
        } else {
            HashMap mapaErro = (HashMap) validacao.getErro();
            Iterator it = mapaErro.keySet().iterator();
            if (it.hasNext()) {
                key = it.next();
            }
            req.setAttribute("ERRO", mapaErro.get(key.toString()));
        }
        return false;

    }
}

    
 
