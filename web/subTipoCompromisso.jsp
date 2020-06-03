<%-- 
    Document   : categoria
    Created on : 29/11/2007, 23:46:21
    Author     : kabelera
--%>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html:html>
    <head>
        <script language="JavaScript" src="JS/Funcoes.js"></script>
        
        <title>TipoCompromisso</title>
    </head>
    <body>
        <html:form action="/SubTipoCompromisso.do?comando=cadastrarSubTipoCompromisso">
         Vincular Tipo de Compromisso:<br>
         <select name="idTipoCompromisso">
       <option value="1">Despesas</option>
       <option value="3">Despesas Fixas</option>
       <option value="2">Receitas</option>
       <option value="4">Receitas Fixas</option>
    </select><p></p>
        Adicionar Sub categoria de Compromisso:
            <html:text property="descSubTipoCompromisso"/>
            <input type="submit" name="button" id="button" value="Gravar" onclick="atualiza_Pai();"/><p>
        
          
        </html:form>
    
    </body>
</html:html>
