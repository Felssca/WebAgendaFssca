<%-- 
    Document   : compromisso
    Created on : 10/07/2008, 22:54:26
    Author     : Felipe
--%>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <html:html>
    <head>
           <link href="Css/Fontes.css" rel="stylesheet" type="text/css" />
        <link href="Css/Tabela.css" rel="stylesheet" type="text/css" />
        <link href="Css/Layout.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="JS/Mascara.js"></script>
        <script language="JavaScript" src="JS/Funcoes.js"></script>
        <!-- DWR IMPORTS-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/CompromissoAjax.js"></script>
        <!--DWR FUNCOES DE COMBO-->
        <script language="JavaScript">
            function pegarIdTipoCompromisso(){
                var id = document.getElementById('comboTipoCompromisso').value;
                carregaComboSubTipo(id);
            }

            function cleanAddOptions(local){
                DWRUtil.removeAllOptions(local);
            }
            function carregaComboSubTipo(id){
                CompromissoAjax.pesquisaSubTipoCompromisso(id,montaComboSubTipo);
            }
            function montaComboSubTipo(listBeans){
                DWRUtil.removeAllOptions("comboSubTipo");
                DWRUtil.addOptions("comboSubTipo", listBeans, "id","descSubTipoCompromisso");
            }
            function pegarTitulo(){
                var i = document.getElementById("comboSubTipo").selectedIndex;
                var tituloSub = document.getElementById('comboSubTipo').options[i].text;
                 document.getElementById('titulo').value=tituloSub;


            }

        </script>




        <title>Compromissos</title>

    </head>
    <body onload="pegarIdTipoCompromisso();pegarTitulo()">

        <c:out value="${ERROR}"/>
        <div id="conteudo">
            <html:form action="Compromisso.do?comando=cadastrarCompromisso" enctype="multipart/form-data"  acceptCharset="UTF-8">
                <table width="80%%" border="1" id="tabela_cadastro">
                    <tr>
                        <td colspan="2"><div align="center">Compromisso Contabil</div></td>
                    </tr>
                    <tr>
                        <td width="238">Tipo de compromisso</td>

                        <td width="236">
                            <select name="idTipoCompromisso" onchange="pegarIdTipoCompromisso()" id="comboTipoCompromisso">
                                <c:forEach var="tipoCompromisso" items="${IdTipoCompromisso}">
                                    <option  value="${tipoCompromisso.id}"><c:out value="${tipoCompromisso.desc_tipo_compromisso}"/></option>
                                </c:forEach>
                            </select>
                          
                        </td>
                        
                    </tr>
                    <tr>

                    <td>Subtipo </td>
                    <td>

                       
                        <select id="comboSubTipo" onchange="pegarTitulo()" name="idSubTipoCompromisso"></select>
                        <a href="subTipoCompromisso.jsp" target="_blank"
                           onclick="window.open(this.href, this.target,'width=500,height=200');return false;">
                        Adicionar item</a>

                    </td>
                  
                    <tr>
                        <td>Data </td>
                        <td><html:text property="dtData" onkeypress="Mascara('DATA',this,event);" maxlength="10"/></td>
                        
                    </tr>
                    <tr>
                        <td>Forma de Pagamento?</td>
                        <td><select name="formaPagamento">
                                <option value="0"><c:out value="A vista"/></option>
                                <option value="1"><c:out value="Cheque"/></option>
                                <option value="2"><c:out value="Cartão de Crédito"/></option>

                        </select></td>
                        
                    </tr>
                    <tr>
                        <td>Titulo do compromisso</td>
                        
                        
                        <td><html:text property="titulo" styleId="titulo"/></td>
                        
                    </tr>
                    
                    <tr>
                        <td>Valor R$ </td>
                        <td><html:text property="valor"/></td>
                        
                    </tr>
                    <tr>
                        <td>Titulo foi pago ?</td>
                        <td><select name="efetuado">
                                <option value="true"><c:out value="Sim"/></option>
                                <option value="false"><c:out value="Não"/></option>
                        </select></td>
                        
                    </tr>
                    <tr>
                        <td>Número de prestações</td>
                        <td><select name="prestacoes">
                                <c:forEach var="repeticao" begin="0" end="60">
                                    <option value="${repeticao}"><c:out value="${repeticao}"/></option>
                                </c:forEach>
                        </select></td>
                        
                    </tr>
                    <tr>
                        <td>Número de prestações quitatas</td>
                        <td><select name="prestacoesPagas">
                                <c:forEach var="repeticao" begin="0" end="60">
                                    <option value="${repeticao}"><c:out value="${repeticao}"/></option>
                                </c:forEach>
                        </select></td>

                    </tr>
                   
                    <tr>
                        <td>Descricao</td>
                        <td><html:textarea property="descricao" cols="40">
                        </html:textarea></td>
                        
                    </tr>
                     <tr>
                        <td>Anexar Comprovante de pagamento</td>
                        <td><html:file property="comprovantes" disabled="true"/><BR>
                        <html:file property="localComprovante" /><BR>
                            <html:radio property="extencaoComprovante" value=".html"/>HTML
                            <html:radio property="extencaoComprovante" value=".pdf"/>PDF
                            <html:radio property="extencaoComprovante" value=".doc"/>DOC
                            <html:radio property="extencaoComprovante" value=".txt"/>TXT
                        </td>
                        
                    </tr>
                      <tr>
                          <td>        <html:submit onclick="mensagem('Registro Adicionado!')"><bean:message key="botao.salvar" /> </html:submit>
                        <td>&nbsp;</td>
                       
                    </tr>
                    
                </table>
            </html:form>


        </div>
    </body>
</html:html>
