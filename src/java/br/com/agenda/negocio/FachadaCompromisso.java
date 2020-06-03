/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.negocio;

import br.com.agenda.action.vo.VOCompromisso;
import br.com.agenda.action.vo.VOCompromissoReplicado;
import br.com.agenda.persistencia.dao.CompromissoDAO;
import br.com.agenda.persistencia.dao.CompromissoReplicadoDAO;
import br.com.agenda.persistencia.dao.RelatorioDAO;
import br.com.agenda.util.AgendaCalculo;
import br.com.agenda.util.AgendaData;
import br.com.agenda.util.Constantes;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.DynaActionForm;

/**
 *
 * @author Felipe
 */
public class FachadaCompromisso {

    private String mes = null;
    private String ano = null;

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void verificaData(ActionForm form) throws Exception {
        AgendaData ag = new AgendaData();
        ag.Calendario();

        DynaActionForm data = (DynaActionForm) form;
        mes = ((String) data.getString("mes"));
        ano = ((String) data.getString("ano"));
        if (mes == null || mes.equals("")) {
            mes = ag.getMes();
        }
        if (ano == null || ano.equals("")) {
            ano = ag.getAno();
        }


    }

    /**
     * Metodos acessores da da Lista de compromisso
     * @param tipo
     * @param valor
     */
    public void condicoesCalculos(Integer tipo, Double valor, Integer formaPagamento) {

        if (tipo == Constantes.DESPESAS) {
            if (formaPagamento != 2) {
                AgendaCalculo.somatoriaDespesas(valor);
            }

        }
        if (tipo == Constantes.DESPESAS_FIXAS) {
            if (formaPagamento != 2) {
                AgendaCalculo.somatoriaDespesasFixas(valor);
            }
        }
        if (tipo == Constantes.RECEITAS) {
            AgendaCalculo.somatoriaReceitas(valor);
        }
        if (tipo == Constantes.RECEITAS_FIXAS) {
            AgendaCalculo.somatoriaReceitasFixas(valor);
        }

    }

    /**
     * Metodos acessores da da Lista de compromisso
     * @param tipo
     * @param valor
     */
    public void condicoesCalculosRelatorio(Integer tipo, Double valor,String mesRecebido) {


        if (tipo == Constantes.DESPESAS) {
            
            AgendaCalculo.somatoriaDespesas(valor);
            if(mes == null ? mesRecebido != null : !mes.equals(mesRecebido)){
            setMes(mesRecebido);
            AgendaCalculo.valorParcial = 0;
            
            }else{
            AgendaCalculo.somatorioParcialRelatorio(valor);
            }



        }
        if (tipo == Constantes.DESPESAS_FIXAS) {

            AgendaCalculo.somatoriaDespesasFixas(valor);

        }
        if (tipo == Constantes.RECEITAS) {
            AgendaCalculo.somatoriaReceitas(valor);
        }
        if (tipo == Constantes.RECEITAS_FIXAS) {
            AgendaCalculo.somatoriaReceitasFixas(valor);
        }

    }

    public void calcularBalanco() {
        AgendaCalculo.balancoDespesas();
        AgendaCalculo.balancoReceitas();
        AgendaCalculo.balanco();
    }

    public ArrayList prepararPesquisa(ActionForm form) throws Exception {
        ArrayList lista = null;
        verificaData(form);
        CompromissoDAO dao = new CompromissoDAO();
        lista = dao.pesquisaCompromisso(mes, ano, 0, "ordemId", Constantes.LISTAR_TUDO);
        return lista;
    }

    /*Pesquisa Seletiva*/
    public ArrayList prepararPesquisaSeletiva(ActionForm form) throws Exception {
        ArrayList lista = null;
        verificaData(form);
        DynaActionForm formulario = (DynaActionForm) form;
        Integer idSub = (Integer.parseInt(formulario.getString("idSubTipoCompromisso")));
        CompromissoDAO dao = new CompromissoDAO();
        lista = dao.pesquisarCompromissoSeletivo(idSub, mes, ano);
        return lista;


    }
    /*Pesquisa Seletiva*/

    public VOCompromisso prepararPesquisaSeletivaUnitaria(ActionForm form) throws Exception {
        VOCompromisso vo = new VOCompromisso();
        DynaActionForm formulario = (DynaActionForm) form;
        Integer idSub = (Integer.parseInt(formulario.getString("id")));
        CompromissoDAO dao = new CompromissoDAO();
        vo = dao.pesquisarCompromissoSeletivoUnitario(idSub);
        return vo;


    }

    /*Edita ou deleta */
    public ArrayList prepararPesquisa(Integer id, String constante, ActionForm form) throws Exception {
        ArrayList lista = null;
        CompromissoDAO dao = new CompromissoDAO();
        verificaData(form);
        lista = dao.pesquisaCompromisso(mes, ano, id, "ordemId", constante);
        return lista;
    }
    /*Ordena pela escolha */

    public ArrayList prepararPesquisa(Integer id, String tipoOrdem, String constante, String mes, String ano) throws Exception {
        ArrayList lista = null;
        CompromissoDAO dao = new CompromissoDAO();
        lista = dao.pesquisaCompromisso(mes, ano, id, tipoOrdem, constante);
        return lista;
    }

    /*Lista tipos */
    public ArrayList listarTipos() throws Exception {
        CompromissoDAO dao = new CompromissoDAO();
        ArrayList listaCompromisso = null;
        listaCompromisso = dao.pesquisaTipoCompromisso();
        return listaCompromisso;

    }
    /*Lista Subtipos */

    public ArrayList listarSubTipos(String tipoLista) throws Exception {
        CompromissoDAO dao = new CompromissoDAO();
        RelatorioDAO relatorio = new RelatorioDAO();
        ArrayList listaSubCompromisso = null;
        if (tipoLista.equals(Constantes.LISTA_NORMAL)) {
            listaSubCompromisso = dao.pesquisaSubTipoCompromisso(Constantes.DESPESAS);
        }
        if (tipoLista.equals(Constantes.LISTA_RELATORIO_MESCLADO)) {
            listaSubCompromisso = relatorio.pesquisaSubTipoCompromissoRelatorio(Constantes.DESPESAS, Constantes.DESPESAS_FIXAS);

        }
        return listaSubCompromisso;



    }
 /*Cadastrar Compromissos*/

    /*
     * RELATORIOS
     *
     */
    public ArrayList prepararPesquisaSimplesRelatorio(ActionForm form) throws Exception {
        ArrayList lista = null;
        verificaData(form);
        RelatorioDAO dao = new RelatorioDAO();
        DynaActionForm formulario = (DynaActionForm) form;
        Integer idTipo = (Integer.parseInt(formulario.getString("idTipoCompromisso")));
        Integer idSub = (Integer.parseInt(formulario.getString("idSubTipoCompromisso")));
        lista = dao.relatorioSimplesMes(idTipo, idSub, mes, ano);
        return lista;
    }
      public ArrayList prepararPesquisaRelatorioMesclado(ActionForm form,ArrayList listaSub) throws Exception {
        ArrayList lista = null;
        verificaData(form);
        RelatorioDAO dao = new RelatorioDAO();
        DynaActionForm formulario = (DynaActionForm) form;
        lista = dao.relatorioMesclado(listaSub, ano);
        return lista;
    }

    public ArrayList prepararPesquisaRelatorioAnual(ActionForm form) throws Exception {
        ArrayList lista = null;
        verificaData(form);
        RelatorioDAO dao = new RelatorioDAO();
        DynaActionForm formulario = (DynaActionForm) form;
        Integer idTipo = (Integer.parseInt(formulario.getString("idTipoCompromisso")));
        Integer idSub = (Integer.parseInt(formulario.getString("idSubTipoCompromisso")));
        AgendaData ag = new AgendaData();
        ag.Calendario();
        String mesFinal = ag.getMes();
        lista = dao.relatorioEntreMeses(idTipo, idSub, mes, mesFinal, ano);
        return lista;
    }
    /*
     * Cadastra o compromisso nos meses selecionados
     */

    public void cadastrarCompromissoReplicante(VOCompromisso vo, int qtReplicas) throws SQLException {
        CompromissoReplicadoDAO daoReplicado = new CompromissoReplicadoDAO();
        CompromissoDAO dao = new CompromissoDAO();
        AgendaData agendaData = new AgendaData();
        VOCompromissoReplicado voReplicado = new VOCompromissoReplicado();
        Date novaData = new Date();
        int prestacoesPagas = 0;
        int contador = qtReplicas;
        voReplicado.setIdCompromisso(vo.getId());
        voReplicado.setReplicado(true);


        try {
            daoReplicado.cadastrarLogReplicado(voReplicado);
            dao.atualizarCompromisso(vo);
            for (int i = 1; i <= contador; i++) {
                novaData = agendaData.criarDataReplica(vo.getDtData());
                prestacoesPagas = vo.getPrestacoesPagas();
                vo.setDtData(novaData);
                if (vo.getPrestacoes() > 0) {
                    if (vo.getPrestacoes() >= vo.getPrestacoesPagas()) {
                        vo.setPrestacoesPagas(prestacoesPagas + 1);
                    }
                }

               int novaId = dao.cadastrarCompromisso(vo);
               voReplicado.setIdCompromisso(novaId);
               daoReplicado.cadastrarLogReplicado(voReplicado);


            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean verificarCompromissoReplicado(int id) throws SQLException {
        CompromissoReplicadoDAO dao = new CompromissoReplicadoDAO();
        boolean replica = false;
        try {
            replica = dao.pesquisarCompromissoReplicado(id);
        } catch (Exception ex) {
            Logger.getLogger(FachadaCompromisso.class.getName()).log(Level.SEVERE, null, ex);
        }

        return replica;
    }
}
