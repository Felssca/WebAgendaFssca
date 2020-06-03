<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>





<html:html>

    <head>
        <link rel="shortcut icon" href="Img/icone.gif"type="image/x-icon"/>
        <link href="Css/Fontes.css" rel="stylesheet" type="text/css" />
        <link href="Css/Tabela.css" rel="stylesheet" type="text/css" />
        <link href="Css/Leyout.css" rel="stylesheet" type="text/css" />
        
        <html:base/>
    </head>
    
    <body bgcolor="white">
        <div id="campo_pesquisa">
            <table border="0" width="100%" >
                <html:form action="/Consulta.do">
                    <tr class="fonte_buscaSel"><td><bean:message key="campo.visualizar"/>
                            <select name="tipoSelecionado">
                                <logic:iterate id="lista" name="listaCategoria">
                                    <option value="<bean:write name="lista" property="tipo"/>">
                                    <bean:write name="lista" property="tipo"/></option>
                                </logic:iterate>
                            </select>
                            <html:submit><bean:message key="botao.ok"/></html:submit>
                        </html:form>
                    </td>
                    
                    <html:form action="/ConsultaSeletiva.do?comando=buscaSeletiva" 
                               onsubmit="return validateFormConsulta(this);">
                        <td><bean:message key="campo.pesquisar"/>
                        <html:select property="comboCriteria">
                            <html:option value="nome">Nome</html:option>
                            <html:option value="telefone">Telefone</html:option>
                            <html:option value="telefone2">Telefone Com</html:option>
                            <html:option value="celular">Celular</html:option>
                            <html:option value="email">E-mail</html:option>
                        </html:select>
                        <html:text property="nomeBusca"/>
                        <html:submit><bean:message key="botao.buscar"/></html:submit> 
                        <!-- Begin Validator Javascript Function-->
                        <html:javascript formName="FormConsulta"/>
                        <!-- End Validator Javascript Function-->            
                    </html:form>
                </tr>
            </table>
        </div>
        <div id="conteudo">
            
            <table width="80%" id="tabela_cor" border="0" cellpadding="0" cellspacing="2" bordercolor="#CCCCCC">
                
                <tr class="fonte_texto">
                    <td align="center"><bean:message key="campo.deletar"/></td>
                    <td align="center"><bean:message key="campo.editar"/></td>
                    
                    <td align="center"><bean:message key="campo.nome"/></td>
                    <td align="center"><bean:message key="campo.telefone"/></td>
                    <td align="center"><bean:message key="campo.telefone2"/></td>
                    <td align="center"><bean:message key="campo.celular"/></td>
                    <td align="center"><bean:message key="campo.email"/></td> 
                </tr>
                
                <html:errors/>
                <logic:empty name="consulta">
                    <bean:message key="reg.vazio"/>
                </logic:empty><tr><font class="fonte_pequena">
                <logic:notEmpty name="consulta">
                    
                            Tabela visualizada por:<strong><bean:write name="tipoSelecionado"/></strong> |
                        
                </logic:notEmpty>
        Usuario:<strong><c:out value="${LOGIN_USUARIO}"/></strong></font></tr>
                <c:forEach var="consultas" items="${consulta}">
                    <c:if test="${consultas.tipoSeparador!=null}">
                        <tr class="fonte_corpo">
                            <td align="left"colspan="8" style="background-color:#EBEBEB;font-color:#EBEBEB" >
                                <c:out value="${consultas.tipoSeparador}"/>
                        </td></tr>
                    </c:if>
                    <c:forEach var="lista" items="${consultas.agenda}">
                        <tr class="fonte_corpo">
                            <c:url  var="urlDelecao" value="/Edicao.do?comando=deletar" >
                                <c:param name="idDelecao" value="${lista.id}"/>
                            </c:url><td><a href="${urlDelecao}"><img src="../Img/deletar.gif" border="0"/></a></td>   
                            <c:url  var="urlEdicao" value="/Edicao.do" >
                                <c:param name="idEdicao" value="${lista.id}"/>
                            </c:url><td><a href="${urlEdicao}"><img src="../Img/editar.gif" border="0"/></a></td>  
                            <c:url  var="urlPesqSeletiva" value="/ConsultaSeletiva.do?comando=buscaSeletivaID" >
                                <c:param name="idConsulta" value="${lista.id}"/>
                            </c:url><td><a href="${urlPesqSeletiva}"><c:out value="${lista.nome}"/></a></td> 
                            
                            <td><c:out value="${lista.telefone}"/></td>
                            <td><c:out value="${lista.telefone2}"/></td>
                            <td><c:out value="${lista.celular}"/></td>
                            <td><a href="mailto:${lista.email}"><c:out value="${lista.email}"/></a></td>
                            
                        </tr>
                    </c:forEach>
                </c:forEach>
                
            </table>
            
            
        
        
        <div id="coluna_direita"> 
        <table>
   <th colspan="2" class="fonte_titulo_niver" >Aniversariantes do Mês</th>
  
  <c:forEach var="aniversariante" items="${aniversariantes}">
      <tr></tr>
    <td class="fonte_pequena">
        <c:url var="urlPesquisaSeletiva" value="/ConsultaSeletiva.do?comando=buscaSeletivaID">
        <c:param name="idConsulta" value="${aniversariante.id}"/></c:url>
        <a href="${urlPesquisaSeletiva}"><img alt="foto" height="60" width="60" src="../Fotos/${aniversariante.foto}"/></a><br>        
      <c:out value="${aniversariante.nome}"/><br>       
      <c:out value="${aniversariante.data}"/></td>
  </c:forEach>

  <tr></tr>
</table><p><p></p>


       </div>
        </div>
        
        
    </body>
    
</html:html>
