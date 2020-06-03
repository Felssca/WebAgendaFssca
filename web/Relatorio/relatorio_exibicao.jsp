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
<html:html>
    <head>
        <link rel="shortcut icon" href="Img/icone.gif"type="image/x-icon"/>
        <link href="../Css/Fontes.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Tabela.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Layout.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="../JS/Funcoes.js"></script>
        <script language="JavaScript" src="../JS/Mascara.js"></script>
     


       

        <title>Relatorio Exibição</title>
    </head>
    <body>
       
 <div id="conteudo">
        <table width="80%" id="tabela_cor" border="0" cellspacing="1"  bgcolor="#CCCCCC">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                      <tr class="fonte_texto" bgcolor="#BFCBE0">
              <td><bean:message key="compromisso.titulo"/></td>
            <td align="center"><bean:message key="compromisso.data"/></td>
            <td align="center"><bean:message key="compromisso.desc"/></td>
            <td align="center"><bean:message key="compromisso.valor"/></td>
                <c:forEach var="lista" items="${lista}">
          
            
                </tr><tr class="fonte_corpo" bgcolor="#FFFFFF">
                    <td> <c:out value="${lista.titulo}"/></td>
                    <td> <fmt:parseDate var="dataFormatada" value="${lista.dtData}" pattern="yyyy-mm-dd" type="date"/>
                <fmt:formatDate value="${dataFormatada}" pattern="dd '/' mm '/' yyyy"/></td>
                    <td> <c:out value="${lista.descricao}"/></td>
                    <td> <c:out value="${lista.valorConvertido}"/></td>
                    
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                </c:forEach>
                <tr class="fonte_texto_total">
                    <td></td><td></td>
                    <td>TOTAL GASTO:</td>
                    <td >R$ <c:out value="${balancoDespesas}"/></td>
                </tr>
            </tbody>
        </table>

        
     
   

   
            </div>
    
    </body>
</html:html>
