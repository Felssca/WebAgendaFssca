/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.persistencia.dao;

import br.com.agenda.action.vo.VOCompromisso;
import br.com.agenda.action.vo.VOSubTipoCompromisso;
import br.com.agenda.persistencia.HibernateUtility;
import br.com.agenda.seguranca.filtros.ControleSessao;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Felipessca
 *
 *
 */
public class RelatorioDAO {

    StringBuffer select = new StringBuffer("");

    public ArrayList<VOCompromisso> relatorioSimplesMes(Integer tipo, Integer subTipo, String mes, String ano) throws Exception, HibernateException {
        ArrayList lista = new ArrayList();
        Session sessao = HibernateUtility.getSession();
        Integer usuario = ControleSessao.ID_USUARIO;
        Query query = null;
        select.append("from br.com.agenda.action.vo.VOCompromisso AS compromisso WHERE compromisso.idUsuario=:usuario ");
        select.append("AND MONTH(compromisso.dtData)=:mes AND YEAR(compromisso.dtData)=:ano AND compromisso.idSubTipoCompromisso=:subTipo ");
        select.append("ORDER BY compromisso.titulo asc,compromisso.idTipoCompromisso asc,compromisso.dtData asc");
        query = sessao.createQuery(select.toString());
        query.setInteger("usuario", usuario);
        query.setString("mes", mes);
        query.setString("ano", ano);
        query.setInteger("subTipo", subTipo);
        lista = (ArrayList) query.list();
        select = null;
        return lista;

    }
    /*
     * Relatorio entre meses
     */

    public ArrayList<VOCompromisso> relatorioEntreMeses(Integer tipo, Integer subTipo, String mesInicial, String mesFinal, String ano) throws Exception, HibernateException {
        ArrayList lista = new ArrayList();
        Session sessao = HibernateUtility.getSession();
        Integer usuario = ControleSessao.ID_USUARIO;
        Query query = null;
        select.append("from br.com.agenda.action.vo.VOCompromisso AS compromisso WHERE compromisso.idUsuario=:usuario ");
        select.append("AND YEAR(compromisso.dtData)=:ano AND compromisso.idSubTipoCompromisso=:subTipo ");
        select.append("ORDER BY compromisso.titulo asc,compromisso.idTipoCompromisso asc,compromisso.dtData asc");
        query = sessao.createQuery(select.toString());
        query.setInteger("usuario", usuario);
        query.setString("ano", ano);
        query.setInteger("subTipo", subTipo);
        lista = (ArrayList) query.list();
        select = null;
        return lista;

    }
    /*
     * Relatorio Mesclado
     */

    public ArrayList<VOCompromisso> relatorioMesclado(ArrayList subTipo, String ano) throws Exception, HibernateException {
        ArrayList lista = new ArrayList();
        Session sessao = HibernateUtility.getSession();
        Integer usuario = ControleSessao.ID_USUARIO;
        Query query = null;
        select.append("from br.com.agenda.action.vo.VOCompromisso AS compromisso WHERE compromisso.idUsuario=:usuario ");
        select.append("AND YEAR(compromisso.dtData)=:ano ");
        for (int i = 0; i < subTipo.size(); i++) {
            if (i == 0) {
                select.append(" AND compromisso.idSubTipoCompromisso=:subTipo").append(i);
            } else {
                select.append(" OR compromisso.idSubTipoCompromisso=:subTipo").append(i);
            }
        }
        select.append(" ORDER BY compromisso.titulo asc,compromisso.idTipoCompromisso asc,compromisso.dtData asc");
        select.toString();
        query = sessao.createQuery(select.toString());
        query.setInteger("usuario", usuario);
        query.setString("ano", ano);
        for (int i = 0; i < subTipo.size(); i++) {
            query.setInteger("subTipo"+i, Integer.parseInt((String) subTipo.get(i)));
        }

        lista = (ArrayList) query.list();
        select = null;
        return lista;

    }

    /**
     *
     * Ações referentes a tablea sub_tipo_compromisso relatório
     */
    public ArrayList pesquisaSubTipoCompromissoRelatorio(Integer tipo, Integer tipo2) throws Exception {
        ArrayList lista = null;
        Integer idUsuario = ControleSessao.ID_USUARIO;
        Session sessao = HibernateUtility.getSession();
        Criteria criteria = sessao.createCriteria(VOSubTipoCompromisso.class);
        Criterion condicao1 = Restrictions.eq("idTipoCompromisso", tipo);
        Criterion condicao2 = Restrictions.eq("idTipoCompromisso", tipo2);
        LogicalExpression andExp = Restrictions.or(condicao1, condicao2);
        criteria.add(andExp);
        criteria.add(Restrictions.eq("idUsuario", idUsuario));
        criteria.addOrder(Order.asc("descSubTipoCompromisso"));
        lista = (ArrayList) criteria.list();
        sessao.close();
        return lista;

    }
}
