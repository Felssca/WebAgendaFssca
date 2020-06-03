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
    <table border="0">
        <thead>
            <tr>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Ocorreu o seguinte erro:</td>
                <td><font color="red"><c:out value="${ERRO}"/></font></td>
            </tr>
            <tr>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>

   <html:errors/><br>
   Clique <a href="#" onclick="javascript:history.back(-1)"> aqui</a> para retornar!
</body>
</html:html>