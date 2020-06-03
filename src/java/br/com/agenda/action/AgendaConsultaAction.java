/*
 * ConsultaAction.java
 *
 * Created on 14/09/2007, 10:54:49
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action;

import br.com.agenda.action.vo.VOTipo;
import br.com.agenda.action.vo.VOAgenda;
import br.com.agenda.util.AgendaData;
import br.com.agenda.persistencia.dao.AgendaDAO;
import br.com.agenda.persistencia.dao.CompromissoDAO;
import br.com.agenda.persistencia.dao.ConfigDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

public class AgendaConsultaAction extends DispatchAction {

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {

        AgendaDAO agenda = new AgendaDAO();
        ArrayList listaAtual = null;
        ArrayList lista = null;
        ArrayList listaFinal = new ArrayList();
        String tipoSelecionado = req.getParameter("tipoSelecionado");

        if (tipoSelecionado == null) {
            tipoSelecionado = "Todos";
        }
        req.setAttribute("tipoSelecionado", tipoSelecionado);

        try {
            lista = agenda.pesquisa("pesquisarTudo", req);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        String tipoAnterior = null;
        VOAgenda novaAgenda = null;

        if (lista.isEmpty()) {

            return mapping.findForward("consulta");
        } else {

            for (int i = 0; i < lista.size(); i++) {
                novaAgenda = (VOAgenda) lista.get(i);
                String tipoAtual = novaAgenda.getTipo();//nome
                VOTipo VO = new VOTipo();
                if (!tipoAtual.equals(tipoAnterior)) {
                    tipoAnterior = tipoAtual;
                    VO.setTipoSeparador(tipoAtual);
                    listaAtual = new ArrayList();
                    listaAtual.add(novaAgenda);
                    VO.setAgenda(listaAtual);
                    listaFinal.add(VO);

                } else {
                    listaAtual.add(novaAgenda);
                    listaFinal.add(VO);
                }

            }

            req.setAttribute("consulta", listaFinal);

            ConfigDAO conf = new ConfigDAO();
            ArrayList listaConfig = null;
            listaConfig = conf.pesquisa("pesquisarCategoria");

            req.setAttribute("listaCategoria", listaConfig);
            /*
         * Monta a lista de aniversariantes
             */
            AgendaData dataAniver = new AgendaData();
            ArrayList aniversariante = dataAniver.verificarAniversario(lista);
            req.setAttribute("aniversariantes", aniversariante);
            /*
         * Monta a lista de compromissos
         * 
             */
            ArrayList listaCompromissos = null;
            //CompromissoDAO dao = new CompromissoDAO();
            //listaCompromissos = dao.pesquisaCompromisso();
            // req.setAttribute("listaCompromisso", listaCompromissos);
        }

        return mapping.findForward("consulta");
    }

    public ActionForward buscaSeletiva(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ArrayList lista = null;
        AgendaDAO agenda = new AgendaDAO();
        DynaActionForm consulta = (DynaActionForm) form;
        String criterio = (String) consulta.get("comboCriteria");
        String nomeBusca = (String) consulta.get("nomeBusca");
        req.setAttribute("criterio", criterio);
        req.setAttribute("criterioBusca", nomeBusca);
        lista = agenda.pesquisa("pesquisar", req);
        req.setAttribute("consultaSeletiva", lista);
        return mapping.findForward("consultaSeletiva");
    }

    public ActionForward buscaSeletivaID(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ArrayList lista = null;
        AgendaDAO agenda = new AgendaDAO();
        String id = req.getParameter("idConsulta");
        req.setAttribute("id", id);
        lista = agenda.pesquisa("pesquisarID", req);
        req.setAttribute("consultaSeletiva", lista);
        return mapping.findForward("consultaSeletiva");
    }
}
