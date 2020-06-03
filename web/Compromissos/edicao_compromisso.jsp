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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<html:html>
    <head>
           <link href="../Css/Fontes.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Tabela.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Leyout.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="../JS/Mascara.js"></script>
        <script language="JavaScript" src="../JS/Funcoes.js"></script>
        <!-- DWR IMPORTS-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/CompromissoAjax.js"></script>
        <!--DWR FUNCOES DE COMBO-->
        <script type="text/javascript" language="JavaScript">
            function pegarIdTipoCompromisso(){
                var id = document.getElementById('comboTipoCompromisso').value;
                carregaComboSubTipo(id);
            }
            function pegarIdTipoCompromissoInicial(){
                var id = document.getElementById('comboTipoCompromisso').value;
                CompromissoAjax.pesquisaSubTipoCompromisso(id,montaComboSubTipoInicial);
            }

            function cleanAddOptions(local){
                DWRUtil.removeAllOptions(local);
            }
            function carregaComboSubTipo(id){
                CompromissoAjax.pesquisaSubTipoCompromisso(id,montaComboSubTipo);


            }
            function montaComboSubTipoInicial(listBeans){
                DWRUtil.removeAllOptions("comboSubTipo");
                DWRUtil.addOptions("comboSubTipo", listBeans, "id","descSubTipoCompromisso");
                var i = document.getElementById("hiddenSub").value;
                dwr.util.setValue("comboSubTipo",i);
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



        <title>CompromissosEdicao</title>

    </head>
    
    <body onload="pegarIdTipoCompromissoInicial();">

        <c:out value="${ERROR}"/>
        <div id="conteudo">
            <html:form action="/Compromisso.do?comando=cadastrarCompromisso" enctype="multipart/form-data" acceptCharset="UTF-8">
                <input type="hidden" name="editar" value="notOK"/>

                <table width="80%" border="1" id="tabela_cadastro">
                    <c:forEach var="consulta" items="${consulta}">
                            <c:forEach var="lista" items="${consulta.compromisso}">
                              <input type="hidden" name="id" value="${lista.id}"/>
                    <tr>
                        <td colspan="2"><div align="center">Compromisso Contabil</div></td>
                        <td width="555"><div align="center">Compromissos Agenda</div></td>
                    </tr>
                    <tr>
                        <td width="238">Tipo de compromisso</td>

                        <td width="236">
                            <select name="idTipoCompromisso" onchange="pegarIdTipoCompromisso()" id="comboTipoCompromisso" >
                                <c:set var="tipo" value="${lista.idTipoCompromisso}"/>
                                <c:forEach var="tipoCompromisso" items="${IdTipoCompromisso}">
                                    <c:if test="${tipo==tipoCompromisso.id}">
                                        <option  value="${tipoCompromisso.id}" selected><c:out value="${tipoCompromisso.desc_tipo_compromisso}"/></option>
                                    </c:if>
                                    <option  value="${tipoCompromisso.id}"><c:out value="${tipoCompromisso.desc_tipo_compromisso}"/></option>
                                </c:forEach>
                            </select>
                            <a href="/tipoCompromisso.jsp" target="_blank"
                               onclick="window.open(this.href, this.target,'width=400,height=100');return false;">
                            Adicionar item</a>
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>

                    <td>Subtipo </td>
                       <td>


                         
                          
                           <c:set var="subTipoinicial" value="${lista.idSubTipoCompromisso}"/>
                           <input type="hidden" id="hiddenSub" value="${subTipoinicial}"> 
                        
                           
                           
                        
                        <select id="comboSubTipo" onchange="pegarTitulo()" name="idSubTipoCompromisso">

                            
                          
                        </select>
                        <a href="/subTipoCompromisso.jsp" target="_blank"
                           onclick="window.open(this.href, this.target,'width=500,height=200');return false;">
                        Adicionar item</a>

                   
                       </td><tr>

                
              
                        
                        <td>Data </td>
                        <fmt:parseDate var="dataFormatada" value="${lista.dtData}" pattern="yyyy-mm-dd" type="date"/>
                        <fmt:formatDate value="${dataFormatada}" pattern="dd'/'mm'/'yyyy" var="data2"/>

                        <td><html:text property="dtData" onkeypress="Mascara('DATA',this,event);" maxlength="10"  value="${data2}"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Forma de Pagamento?</td>
                        <td><select name="formaPagamento"  >
                            <c:set var="selecionado" value="${lista.formaPagamento}"/>
                            
                          

                            <c:if test="${selecionado=='0'}">
                                <option value="0" selected><c:out value="A vista"/></option>
                                 <option value="1"><c:out value="Cheque"/></option>
                                <option value="2" ><c:out value="Cartão de Crédito"/></option>
                            </c:if><c:if test="${selecionado=='1'}">
                               <option value="0" ><c:out value="A vista"/></option>
                                 <option value="1" selected><c:out value="Cheque"/></option>
                                <option value="2" ><c:out value="Cartão de Crédito"/></option>
                            </c:if><c:if test="${selecionado=='2'}">
                               <option value="0" ><c:out value="A vista"/></option>
                                 <option value="1"><c:out value="Cheque"/></option>
                                <option value="2" selected><c:out value="Cartão de Crédito"/></option>
                            </c:if>
                                
                               

                        </select></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Titulo do compromisso</td>


                        <td><html:text property="titulo" styleId="titulo" value="${lista.titulo}"/></td>
                        <td>&nbsp;</td>
                    </tr>

                    <tr>
                        <td>Valor</td>
                        <td>R$ <html:text property="valor" value="${lista.valor}"/></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Titulo foi pago ?</td>
                        <td><select name="efetuado">
                             <c:set var="selecionado" value="${lista.efetuado}"/>
                             <c:if test="${selecionado=='true'}">
                                 <option value="true" selected><c:out value="Sim"/></option>
                                <option value="false"><c:out value="Não"/></option>
                             </c:if>
                             <c:if test="${selecionado=='false'}">
                                 <option value="true" ><c:out value="Sim"/></option>
                                <option value="false" selected><c:out value="Não"/></option>
                             </c:if>
                                
                        </select></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Numero de prestações</td>
                        <td><select name="prestacoes">
                                <c:forEach var="repeticao" begin="0" end="60">
                                    <c:set var="prestacaoAtual" value="${lista.prestacoes}"/>
                                        <c:set var="valor" value="false"/>
                                        <c:if test="${prestacaoAtual == repeticao}">
                                        <option value="${repeticao}" selected="true"><c:out value="${repeticao}"/></option>
                                        </c:if>
                                        <option value="${repeticao}"><c:out value="${repeticao}"/></option>

                                </c:forEach>
                        </select></td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>Numero de prestações quitadas</td>
                        <td><select name="prestacoesPagas">
                                <c:forEach var="repeticao" begin="0" end="60">
                                <c:set var="prestacaoAtual" value="${lista.prestacoesPagas}"/>
                                        <c:set var="valor" value="false"/>
                                        <c:if test="${prestacaoAtual == repeticao}">
                                        <option value="${repeticao}" selected="true"><c:out value="${repeticao}"/></option>
                                        </c:if>
                                        <option value="${repeticao}"><c:out value="${repeticao}"/></option>

                                </c:forEach>
                        </select></td>
                        <td>&nbsp;</td>
                    </tr>

                    <tr>
                        <td>Descricao</td>
                        <td><html:textarea property="descricao" value="${lista.descricao}">
                        </html:textarea></td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                        <td>Anexar Comprovante de pagamento</td>
                        <td><html:file property="comprovantes"/><br/>
                        <td><html:file property="localComprovante"/><br/>
                            <html:radio property="extencaoComprovante" value=".html"/>HTML
                            <html:radio property="extencaoComprovante" value=".pdf"/>PDF
                            <html:radio property="extencaoComprovante" value=".doc"/>DOC
                            <html:radio property="extencaoComprovante" value=".txt"/>TXT
                        </td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>        <html:submit onclick="mensagem('Registro Editado!')"><bean:message key="botao.salvar"/> </html:submit>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>
                </c:forEach>
</c:forEach>
            </html:form>


        </div>
    </body>
</html:html>
