<%--
    Document   : compromissoLista
    Created on : 03/10/2008, 00:51:14
    Author     : Felipe

<c:url  var="comprovante" value="/Compromisso.do?comando=exibirArquivoComprovante" >
                    <c:param name="nome" value="${lista.titulo}"/>
                       </c:url>
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
        <link rel="shortcut icon" href="../Img/icone.gif"type="image/x-icon"/>
        <link href="../Css/Fontes.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Tabela.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Layout.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="../JS/Mascara.js"></script>
        <script language="JavaScript" src="../JS/Funcoes.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      

        <title>Visualizar Compromisso</title>
    </head>
    <body><p></p>
        <html:form action="Compromisso.do?comando=replicarCompromisso">
 <c:forEach var="consulta" items="${consulta}">
   <c:forEach var="lista" items="${consulta.compromisso}">
       <fmt:parseDate var="dataFormatada" value="${lista.dtData}" pattern="yyyy-mm-dd" type="date"/>
         <fmt:formatDate value="${dataFormatada}" pattern="dd'/'mm'/'yyyy" var="data2"/>
         <table border="0"  align="center" class="fonte_pequena" id="tabela_cadastro" cellspacing="2">
           <html:hidden property="id" value="${lista.id}"/>
           <thead>
               <tr>
                   <th align="center" colspan="2">Visualização compromisso</th>
                   <th></th>
               </tr>
           </thead>
           <tbody>
               <tr>
                   <td><b>Titulo:</b></td>
                   <td><c:out value="${lista.titulo}"/></td>
               </tr>
               <tr>
                   <td><b> Data Pagamento:</b></td>
                   <td><c:out value="${data2}"/></td>
               </tr>
               <tr>
                   <td><b>Valor do pagamento: R$</b></td>
                   <td><c:out value="${lista.valor}"/></td>
               </tr>
               <tr>
                   <td> <b> Forma de pagamento:</b> </td>
                   <td><c:out value="${lista.descFormaPagamento}"/></td>
               </tr>
                <tr>
                   <td> <b>Número de Prestações:</b></td>
                   <td><c:out value="${lista.prestacoes }"/></td>
               </tr>
                <tr>
                   <td> <b>Número de Prestações Quitadas:</b></td>
                   <td><c:out value="${lista.prestacoesPagas }"/></td>
               </tr>
               <tr>
                   <td> <b>Descrição:</b></td>
                   <td><c:out value="${lista.descricao}"/></td>
               </tr>
               <tr bgcolor="geen">
                   <td></td>
                   <td></td>
               </tr>
             
          

               <c:if test="${replicado==false}" >
                <tr>
                   <td> Replicar compromisso para os próximos </td>

                   <td ><select name="meses">
                                <c:forEach var="repeticao" begin="1" end="12">
                                    <option value="${repeticao}"><c:out value="${repeticao}"/></option>
                                </c:forEach>
                       </select> meses: <html:submit onclick="mensagem('Registro replicado!')">Replicar</html:submit></td>
               </tr>
               </c:if>
               <tr bgcolor="geen">
                   <td></td>
                   <td></td>
               </tr>
                    <tr>
                   <td>  </tr>
                    <tr><td class="input">
          <a href="../Comprovantes/${lista.localComprovantes}" target="_blank">
               Exibir Comprovante de pagamento</a></tr>
        
                   </td>
               
           </tbody>
       </table>

         
   </c:forEach>

 </c:forEach>
        
         
        

    </body>
    </html:form>
</html:html>
