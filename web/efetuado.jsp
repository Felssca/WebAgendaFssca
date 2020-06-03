<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page import="br.com.agenda.action.vo.VOCompromisso" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html:html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Css/Fontes.css" rel="stylesheet" type="text/css" />
    <html:base/>
</head>
<body>
    <div id="conteudo">
    <bean:write name="msg"/><br><c:out value="${msg}"/>
    <html:link page="/CadastroForm.do?comando=cadInicial">Incluir novo cadastro</html:link><br>
    <html:link page="/Consulta.do">Retornar</html:link>
</div>
</body>
</html:html>