/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action;

import br.com.agenda.action.vo.VOTipoCompromissoLista;
import br.com.agenda.action.vo.VOCompromisso;
import br.com.agenda.negocio.FachadaCompromisso;
import br.com.agenda.persistencia.dao.CompromissoDAO;
import br.com.agenda.seguranca.filtros.ControleSessao;
import br.com.agenda.util.AgendaCalculo;
import br.com.agenda.util.AgendaComprovantes;
import br.com.agenda.util.AgendaConversoes;
import br.com.agenda.util.AgendaData;
import br.com.agenda.util.Constantes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Felipe
 */
public class AgendaCompromissoAction extends DispatchAction {

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {

        FachadaCompromisso helper = new FachadaCompromisso();
        req.setAttribute("IdTipoCompromisso", helper.listarTipos());
        req.setAttribute("IdSubTipoCompromisso", helper.listarSubTipos(Constantes.LISTA_NORMAL));

        return mapping.findForward("inicial");
    }

    public ActionForward listarCompromissos(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ArrayList lista = new ArrayList();
        ArrayList listaAtual = null;
        ArrayList listaFinal = new ArrayList();
        HttpSession sessao = ((HttpServletRequest) req).getSession(true);

        AgendaData data = new AgendaData();
        data.Calendario();

        String mes = (String) (req.getParameter("mes"));
        String ano = (String) (req.getParameter("ano"));
        int tipoAnterior = 0;
        VOCompromisso novoCompromisso = null;
        FachadaCompromisso helper = new FachadaCompromisso();
        AgendaConversoes conversao = new AgendaConversoes();

        boolean edicao = false;
        boolean seletivo = false;
        boolean ordenar = false;
        boolean reqEdicao = false;
        boolean replicado = false;

        req.setAttribute("IdTipoCompromisso", helper.listarTipos());
        req.setAttribute("IdSubTipoCompromisso", helper.listarSubTipos(Constantes.LISTA_NORMAL));

        if (req.getParameter("editar") != null) {
            if (req.getParameter("editar").equals("ok")) {
                reqEdicao = true;
            }
        }
        if (reqEdicao) {

            int id = (Integer.parseInt(req.getParameter("id")));
            lista = helper.prepararPesquisa(id, Constantes.EDITAR, form);
            edicao = true;

        } else if (req.getParameter("seletiva") != null) {
            VOCompromisso vo = new VOCompromisso();
            vo = helper.prepararPesquisaSeletivaUnitaria(form);
            replicado = helper.verificarCompromissoReplicado(vo.getId());
            lista.add(vo);
            seletivo = true;

        } else if (req.getParameter("ordenar") != null) {
            String ordem = (String) (req.getParameter("tipoOrdem"));

            lista = helper.prepararPesquisa(0, ordem, Constantes.LISTAR_ORDER_BY, mes, ano);
            ordenar = true;

        } else {
            lista = helper.prepararPesquisa(form);
        }

        AgendaComprovantes util = new AgendaComprovantes();

        for (int i = 0; i < lista.size(); i++) {
            novoCompromisso = (VOCompromisso) lista.get(i);
            novoCompromisso.setValorConvertido(AgendaCalculo.mascaraDinheiro(novoCompromisso.getValor(), AgendaCalculo.DINHEIRO_REAL));
            novoCompromisso.setDescEfetuado(conversao.converterCompromissoCumprido(novoCompromisso.isEfetuado()));
            novoCompromisso.setDescFormaPagamento(conversao.converterFormaPagamento(novoCompromisso.getFormaPagamento()));
            helper.condicoesCalculos(novoCompromisso.getIdTipoCompromisso(), novoCompromisso.getValor(), novoCompromisso.getFormaPagamento());
            novoCompromisso.setArquivo(util.retornarComprovante(novoCompromisso.getComprovanteBlob()));
            
            int tipoAtual = novoCompromisso.getIdTipoCompromisso();

            VOTipoCompromissoLista vo = new VOTipoCompromissoLista();
            if (tipoAtual != tipoAnterior) {
                tipoAnterior = tipoAtual;
                vo.setTipoSeparador(tipoAtual);
                listaAtual = new ArrayList();
                listaAtual.add(novoCompromisso);
                vo.setCompromisso(listaAtual);
                listaFinal.add(vo);
            } else {
                listaAtual.add(novoCompromisso);
                listaFinal.add(vo);

            }

        }
        helper.calcularBalanco();
        req.setAttribute("consulta", listaFinal);
        req.setAttribute("totalDespesas", AgendaCalculo.despesas);
        req.setAttribute("totalDespesasFixas", AgendaCalculo.despesasFixas);
        req.setAttribute("balancoDespesas", AgendaCalculo.balancoDespesas);

        req.setAttribute("totalReceitas", AgendaCalculo.receitas);
        req.setAttribute("totalReceitasFixas", AgendaCalculo.receitasFixas);
        req.setAttribute("balancoReceitas", AgendaCalculo.balancoReceitas);

        req.setAttribute("balancoGeral", AgendaCalculo.balancoGeral);
        AgendaCalculo.zerarValores();

        helper.verificaData(form);

        req.setAttribute("mesPesquisa", helper.getMes());
        req.setAttribute("anoPesquisa", helper.getAno());
        req.setAttribute("anoAtual", data.getAno());

        if (listaFinal.isEmpty()) {
            req.setAttribute("AVISO", "Não existem registro para essa data.");
        }
        if (edicao) {
            req.setAttribute("IdTipoCompromisso", helper.listarTipos());
            req.setAttribute("IdSubTipoCompromisso", helper.listarSubTipos(Constantes.LISTA_NORMAL));

            return mapping.findForward("editar");
        }

        if (seletivo) {
            req.setAttribute("IdTipoCompromisso", helper.listarTipos());
            req.setAttribute("replicado", replicado);
            req.setAttribute("IdSubTipoCompromisso", helper.listarSubTipos(Constantes.LISTA_NORMAL));
            sessao.setAttribute("arquivo", novoCompromisso.getArquivo());
            sessao.setAttribute("localComprovantes", novoCompromisso.getLocalComprovantes());
            sessao.setAttribute("nome", novoCompromisso.getTitulo());
            sessao.setAttribute("extencao", novoCompromisso.getExtencaoComprovante());
            return mapping.findForward("listaSeletivaUnitaria");
        }

        return mapping.findForward("lista");

    }

    
    public ActionForward cadastrarCompromisso(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {
        DynaActionForm cadastro = (DynaActionForm) form;
        VOCompromisso vo = new VOCompromisso();
        AgendaData dataCompromisso = new AgendaData();
        Double valor;

        vo.setIdTipoCompromisso(Integer.parseInt(cadastro.getString("idTipoCompromisso")));
        vo.setId(Integer.parseInt(cadastro.getString("idTipoCompromisso")));
        vo.setIdSubTipoCompromisso(Integer.parseInt(cadastro.getString("idSubTipoCompromisso")));
        vo.setTitulo((String) (cadastro.get("titulo")));
        String data = ((String) cadastro.get("dtData"));
        vo.setDtData((Date) dataCompromisso.formataData(data));
        vo.setDescricao((String) (cadastro.get("descricao")));
        vo.setFormaPagamento(Integer.parseInt(cadastro.getString("formaPagamento")));
        vo.setPrestacoes(Integer.parseInt(cadastro.getString("prestacoes")));
        vo.setPrestacoesPagas(Integer.parseInt(cadastro.getString("prestacoesPagas")));

        AgendaComprovantes util = new AgendaComprovantes();
        vo.setComprovantes((FormFile) (cadastro.get("comprovantes")));
        vo.setLocalComprovante((FormFile) (cadastro.get("localComprovante")));
        
        if (vo.getLocalComprovante() != null) {
            vo.setLocalComprovantes(vo.getLocalComprovante().toString());
        }

        vo.setComprovanteBlob(null);
        vo.setExtencaoComprovante((String) cadastro.getString("extencaoComprovante"));

        valor = new Double(cadastro.getString("valor"));
        vo.setValor(valor);
        vo.setIdUsuario(ControleSessao.ID_USUARIO);
        vo.setEfetuado((Boolean.parseBoolean((String) cadastro.get("efetuado"))));

        String editado = (String) cadastro.getString("editar");
        CompromissoDAO dao = new CompromissoDAO();

        if (editado.equals("notOK")) {
            vo.setId(Integer.parseInt(cadastro.getString("id")));
            dao.atualizarCompromisso(vo);
            req.setAttribute("msg", "Edição efetuada");
            req.setAttribute("editar", "notOK");
        } else {
            dao.cadastrarCompromisso(vo);
            req.setAttribute("msg", "Cadastro Efetuado");

        }

        return mapping.findForward("cadastroCompromisso");

    }

    public ActionForward deletarCompromisso(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {

        Integer id = Integer.parseInt(req.getParameter("idDelecao"));
        VOCompromisso vo = new VOCompromisso();
        vo.setId(id);
        CompromissoDAO compromisso = new CompromissoDAO();
        compromisso.deletarCompromisso(vo);
        req.setAttribute("MSG", "Arquivo deletado");
        return mapping.findForward("deletado");
    }
//Metodo responsável por exibir os comprovantes na sessao.
    public ActionForward exibirArquivoComprovante(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        
        try {
            
            HttpSession sessao = ((HttpServletRequest) req).getSession();
            String nomeArquivo = (String) sessao.getAttribute("nome");
            
            System.err.println("arquivo"+nomeArquivo);
            
            
        } catch (Exception e) {
            
            
        }
/*Metodo depricado pegava os Bites do banco e transformava.
        try {

            HttpSession sessao = ((HttpServletRequest) req).getSession();
            String nomeArquivo = (String) sessao.getAttribute("nome");
            byte[] arquivo = ((byte[]) sessao.getAttribute("arquivo"));
            String extencao = (String) sessao.getAttribute("extencao");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment; filename=" + nomeArquivo + extencao);
            res.getOutputStream().write(arquivo);
            res.getOutputStream().flush();

        } catch (IOException e) {
            e.printStackTrace();

        }
*/
        return null;

    }
    /*
     * Metodo responsavel por replicar os compromissos
     */

    public ActionForward replicarCompromisso(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {

        FachadaCompromisso helper = new FachadaCompromisso();
        int qtReplicas = Integer.parseInt(req.getParameter("meses"));
        ArrayList lista = new ArrayList();
        VOCompromisso vo = new VOCompromisso();
        vo = helper.prepararPesquisaSeletivaUnitaria(form);
        helper.cadastrarCompromissoReplicante(vo, qtReplicas);

        return mapping.findForward("cadastroCompromisso");
    }

}
