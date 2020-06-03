/*
 * AgendaEditAction.java
 * 
 * Created on 02/10/2007, 07:22:56
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action;

import br.com.agenda.action.vo.VOAgenda;
import br.com.agenda.util.AgendaData;
import br.com.agenda.util.AgendaImagem;
import br.com.agenda.persistencia.dao.AgendaDAO;
import br.com.agenda.persistencia.dao.ConfigDAO;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Felipessca
 */
public class AgendaEditDelAction extends DispatchAction {

    @Override
    public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {
        String id = req.getParameter("idEdicao");
        String tipoSelecionado = null;
        ArrayList lista = null;
        AgendaDAO agenda = new AgendaDAO();
        req.setAttribute("id", id);
        lista = agenda.pesquisa("pesquisarID", req);
        

        req.setAttribute("consultaSeletiva", lista);
        req.setAttribute("tipoSelecionado", tipoSelecionado);

        ConfigDAO conf = new ConfigDAO();
        ArrayList listaConfig = null;
        listaConfig = conf.pesquisa("pesquisarCategoria");
        req.setAttribute("listaCategoria", listaConfig);

        return mapping.findForward("editar");
    }

    public ActionForward editar(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {

        DynaActionForm cadastro = (DynaActionForm) form;
        VOAgenda vo = new VOAgenda();
        AgendaData data= new AgendaData();
        String ID = ((String) cadastro.get("id"));
        String fotoAtual = ((String) cadastro.get("foto"));

        vo.setId(Integer.parseInt(ID));
        String nome=((String) cadastro.get("nome"));
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
        
        String dataAniver=((String)cadastro.get("aniversario"));
        vo.setAniversario((Date) data.formataData(dataAniver));
        vo.setCarac((String) cadastro.get("carac"));
        //vo.setRegiao((String) cadastro.get("regiao"));
        vo.setMsn((String) cadastro.get("msn"));
        vo.setSkype((String) cadastro.get("skype"));
        vo.setOrkut((String) cadastro.get("orkut"));

        vo.setFotoFile((FormFile) cadastro.get("fotoFile"));
        AgendaImagem util = new AgendaImagem();
        util.gravarFoto(vo.getFotoFile(), "Edicao", fotoAtual);
        vo.setFoto(util.getNomeArquivo());

        AgendaDAO agenda = new AgendaDAO();
        agenda.update(vo);
        req.setAttribute("msg", "reg.editado");
        return mapping.findForward("editado");
    }

    public ActionForward deletar(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {

        Integer id = Integer.parseInt(req.getParameter("idDelecao"));
        VOAgenda vo = new VOAgenda();
        vo.setId(id);
        AgendaDAO agenda = new AgendaDAO();
        agenda.detete(vo);
        req.setAttribute("msg", "Arquivo deletado");
        return mapping.findForward("deletado");
    }
}
