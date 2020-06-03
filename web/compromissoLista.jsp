<%-- 
    Document   : compromissoLista
    Created on : 03/10/2008, 00:51:14
    Author     : Felipe
--%>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>--%>
<%@page import="br.com.agenda.action.vo.VOCompromisso" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
   
    <link rel="shortcut icon" href="Img/icone.gif"type="image/x-icon"/>
    <link href="/Css/Fontes.css" rel="stylesheet" type="text/css" />
    <link href="/Css/Tabela.css" rel="stylesheet" type="text/css" />
    <link href="/Css/Layout.css" rel="stylesheet" type="text/css" />

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script language="JavaScript" src="/JS/Funcoes.js"></script>
    <script language="JavaScript" src="/JS/Mascara.js"></script>
    <!-- DWR IMPORTS-->
    <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/CompromissoAjax.js"></script>
    <!--DWR FUNCOES DE COMBO-->
    <script type="text/javascript" language="JavaScript">
    

        function mostrar_esconderDiv(id) {
            var el = document.getElementById(id);
            el.style.display = (el.style.display=="") ? "none" : "";
        }
      


    </script>


    <title>ListaCompromissos</title>
</head>
<html:html>
    <body onload="pegarIdTipoCompromisso(); mostrar_esconderDiv('cadastro');">
        <div id="campo_pesquisa" >
            <table border="0" width="80%">
                <thead>
                    <tr>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td bgcolor="#BFCBE0"><font color="#ffffff"><a href="#" onclick="mostrar_esconderDiv('cadastro')"> Cadastrar:</a></font></td>
                    </tr>
                </tbody>
            </table> 
        </div>

        <div id="cadastro">
            <%@include file="compromisso.jsp" %>

        </div>

        <html:form action="/CompromissoConsulta.do?comando=listarCompromissos" >

            <div id="campo_pesquisa">
                <table border="0" width="80%">
                    <thead>
                        <tr>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td bgcolor="#BFCBE0"><font color="#ffffff">Pesquisa:</font></td>
                        </tr>
                    </tbody>
                </table>



                <table border="1" id="tabela_cadastro" >
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Mês</td>
                            <td><select name="mes">

                                    <c:forEach var="mesRepeticao" begin="0" end="12">
                                        <option value="${mesRepeticao}"><c:out value="${mesRepeticao}"/></option>
                                    </c:forEach>
                                </select></td>
                            <td >Ano</td>
                            <td > <html:text property="ano" value="${anoAtual}"/></td>
                            <td ><input type="submit" value="Pesquisar" />

                        </tr>
                    </tbody>
                </table>
                <table border="0" id="tabela_cor" width="80%">
                    <tbody>
                        <tr class="fonte_texto" >
                            <td  align="center">Lista referente ao mês:<b><c:out value="${mesPesquisa}"/></b>
                                ano:<b><c:out value="${anoPesquisa}"/></b></td>
                        </tr>
                    </tbody>
                </table>



            </div>
        </html:form>
        <div id="conteudo">

            <table width="80%" id="tabela_cor" border="0" cellspacing="2"  bgcolor="#CCCCCC">

                <tr class="fonte_texto" bgcolor="#BFCBE0">
                    <html:form action="/CompromissoConsulta.do?comando=listarCompromissos" >
                        <td align="center"><bean:message key="campo.deletar"/></td>
                        <td align="center"><bean:message key="campo.editar"/></td>
                        <td align="center"> <c:url  var="urlTitulo" value="/Compromisso.do?comando=listarCompromissos" >
                                <c:param name="mes" value="${mesPesquisa}"/>
                                <c:param name="ano" value="${anoPesquisa}"/>
                                <c:param name="ordenar" value="ok"/>
                                <c:param name="tipoOrdem" value="titulo"/>
                            </c:url><a href="${urlTitulo}"><bean:message key="compromisso.titulo"/></a></td>
                        <td align="center"><bean:message key="compromisso.data"/></td>
                        <td align="center"><bean:message key="compromisso.valor"/></td>
                        <td align="center"><bean:message key="compromisso.desc"/></td>
                        <td align="center"><bean:message key="compromisso.mes"/></td>
                        <td align="center"><bean:message key="compromisso.prestacao"/></td>
                        <td align="center"><bean:message key="compromisso.efetuado"/></td>


                    

                    </tr>
                <tr>
                <font color="red"><c:out value="${ERRO}"/></font>
                <font color="geen"><c:out value="${AVISO}"/></font>
                <font class="fonte_pequena">Usuario:<strong><c:out value="${LOGIN_USUARIO}"/></strong></font></tr>

                <c:forEach var="consultas" items="${consulta}">

                    <c:if test="${consultas.tipoSeparador!=null}">
                        <tr class="fonte_corpo">
                            <td align="left"colspan="9" style="background-color:#D5DAE0;font-color:#FFFFFF">
                                <c:set var="elemento" value="${consultas.tipoSeparador}"/>
                                <c:if test="${elemento=='1'}">
                                    <font color="red"> <c:out value="Despesas"/></font>
                                </c:if> <c:if test="${elemento=='2'}">
                                    <font color="green"><c:out value="Receitas"/></font>
                                </c:if><c:if test="${elemento=='3'}">
                                    <font color="red"><c:out value="Despesas Fixas"/></font>
                                </c:if> <c:if test="${elemento=='4'}">
                                    <font color="green"><c:out value="Receitas Fixas"/></font>
                                </c:if>
                            </td></tr>
                        </c:if>
                        <c:forEach var="lista" items="${consultas.compromisso}">

                        <tr class="fonte_corpo" bgcolor="#FFFFFF">
                            <c:url  var="urlDelecao" value="/Compromisso.do?comando=deletarCompromisso"  >
                                <c:param name="idDelecao" value="${lista.id}"/>
                            </c:url><td><a href="${urlDelecao}"onclick="mensagem('Registro deletado!')">
                                    <img src="Img/deletar.gif" border="0"/></a></td>

                            <c:url  var="urlEdicao" value="/Compromisso.do?comando=listarCompromissos" >
                                <c:param name="editar" value="ok"/>
                                <c:param name="id" value="${lista.id}"/>
                            </c:url><td><a href="${urlEdicao}"><img src="Img/editar.gif" border="0"/></a></td>

                            <c:url  var="urlPesqSeletiva" value="/Compromisso.do?comando=listarCompromissos" >
                                <c:param name="id" value="${lista.id}"/>
                                <c:param name="seletiva" value="ok"/>
                            </c:url><td><a href="${urlPesqSeletiva}"><c:out value="${lista.titulo}"/></a></td>

                            <td>
                                <fmt:parseDate var="dataFormatada" value="${lista.dtData}" pattern="yyyy-mm-dd" type="date"/>
                                <fmt:formatDate value="${dataFormatada}" pattern="dd '/' mm '/' yyyy"/>  </td>
                            <td><c:out value="${lista.valorConvertido}"/></td>
                            <td><c:out value="${lista.descricao}"/></td>
                            <td align="center"><c:out value="${lista.descFormaPagamento}"/></td>
                            <td align="center"><c:out value="${lista.prestacoesPagas}/"/><c:out value="${lista.prestacoes}"/></td>
                            <c:choose>
                                <c:when test="${lista.descEfetuado=='OK'}">
                                    <td align="right"><font color="green"/><b><c:out value="${lista.descEfetuado}"/></b></td>
                                </c:when><c:otherwise>
                                    <td align="right"><font color="red"/><b><c:out value="${lista.descEfetuado}"/></b></td>
                                </c:otherwise>
                            </c:choose>




                        </tr>

                    </c:forEach>
                </c:forEach>
            </table>
</html:form>
        </div>
        <div id="coluna_direita_compromisso">
            <html:form action="/CompromissoConsulta.do?comando=listarCompromissos" >
            <table border="1" id="tabela_cor" class="fonte_corpo" border="0" cellspacing="2" id="tabela_cadastro">
                <thead>
                    <tr bgcolor="#B8D2BA">
                        <th colspan="2">Balanço:</th>
                       
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td align="center" bgcolor="ffffff">Desc:</td>
                        <td align="center" bgcolor="ffffff">Valor:</td>
                    </tr>
                    <tr>
                        <td>Despesas:</td>
                        <td>R$ <c:out value="${totalDespesas}"/></td>
                    </tr>
                    <tr>
                        <td>DespeasFixas</td>
                        <td>R$ <c:out value="${totalDespesasFixas}"/></td>
                    </tr>
                    <tr>
                    <tr>
                        <td>Total</td>
                        <td>R$ <c:out value="${balancoDespesas}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2" bgcolor="red"></td>
                        <td></td>
                    </tr>
                    <tr>
                    <tr>
                        <td>Receitas</td>
                        <td>R$ <c:out value="${totalReceitas}"/></td>
                    </tr>
                    <tr>
                        <td>Receitas Fixas</td>
                        <td>R$ <c:out value="${totalReceitasFixas}"/></td>
                    </tr>
                    <tr>
                        <td>Total</td>
                        <td>R$ <c:out value="${balancoReceitas}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"  bgcolor="green"></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Balanço geral</td>
                        <td>R$ <c:out value="${balancoGeral}"/></td>
                  
                </tbody>
            </table>
                        </html:form>

        </div>


    </body>
</html:html>
