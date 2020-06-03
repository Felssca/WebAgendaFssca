/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.action;

import br.com.agenda.action.vo.VOCompromisso;
import br.com.agenda.negocio.FachadaCompromisso;
import br.com.agenda.util.AgendaCalculo;
import br.com.agenda.util.AgendaData;
import br.com.agenda.util.Constantes;
import java.util.ArrayList;
import java.util.Arrays;
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
public class AgendaRelatorio extends DispatchAction {

    @Override
    protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse resp) throws Exception {

        FachadaCompromisso helper = new FachadaCompromisso();
        AgendaData data = new AgendaData();
        data.Calendario();
        req.setAttribute("IdTipoCompromisso", helper.listarTipos());
        req.setAttribute("IdSubTipoCompromisso", helper.listarSubTipos(Constantes.LISTA_NORMAL));
        req.setAttribute("IdRelatorioMesclado", helper.listarSubTipos(Constantes.LISTA_RELATORIO_MESCLADO));
        req.setAttribute("anoAtual", data.getAno());


        return mapping.findForward("inicial");
    }

    public ActionForward relatorioConfig(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {
        ArrayList lista = new ArrayList();
        ArrayList listaFinal = new ArrayList();
        VOCompromisso novoCompromisso = null;
        DynaActionForm consulta = (DynaActionForm) form;
        FachadaCompromisso helper = new FachadaCompromisso();

        lista = helper.prepararPesquisaRelatorioAnual(consulta);
        for (int i = 0; i < lista.size(); i++) {
            novoCompromisso = (VOCompromisso) lista.get(i);
            novoCompromisso.setValorConvertido(AgendaCalculo.mascaraDinheiro(novoCompromisso.getValor(), AgendaCalculo.DINHEIRO_REAL));
            helper.condicoesCalculosRelatorio(novoCompromisso.getIdTipoCompromisso(), novoCompromisso.getValor(), novoCompromisso.getMes());
            listaFinal.add(novoCompromisso);
        }

        helper.calcularBalanco();
        req.setAttribute("lista", listaFinal);
        req.setAttribute("balancoDespesas", AgendaCalculo.balancoDespesas);
        AgendaCalculo.zerarValores();



        return mapping.findForward("relatorio");
    }

    public ActionForward relatorioConfigMesclado(ActionMapping mapping, ActionForm form, HttpServletRequest req,
            HttpServletResponse resp) throws Exception {
        ArrayList lista = new ArrayList();
        ArrayList<VOCompromisso> listaFinal = new ArrayList<VOCompromisso>();
        String[] valores = req.getParameterValues("list2");
        FachadaCompromisso fachada = new FachadaCompromisso();
        VOCompromisso novoCompromisso = null;
        lista.addAll(Arrays.asList(valores));
        listaFinal = fachada.prepararPesquisaRelatorioMesclado(form, lista);
        req.setAttribute("lista", listaFinal);
        req.setAttribute("balancoDespesas", AgendaCalculo.balancoDespesas);
        AgendaCalculo.zerarValores();
        return mapping.findForward("relatorio");
    }
}
