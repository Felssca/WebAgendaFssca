<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html:html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="Css/Fontes.css" rel="stylesheet" type="text/css" />
    <html:base/>
</head>
<body>
    <div id="conteudo">
        <c:out value="${erro}"/><p></p>
        <!--
        Cadastrar novo usuário::<br>
        <html:form action="/Login.do?comando=cadastrar">
        Usuario: <html:text property="usuario" value="felipe"/>
        Senha: <html:password property="senha" value="felipe"/>
       <html:submit><bean:message key="botao.ok"/></html:submit><br>
       <br>Login
        </html:form>-->
        <html:form action="/Login.do?comando=consultar">
            <table border="1"  id="tabela_cadastro" align="center">
            <tbody>
                 <tr>
                     <td colspan="2" align="center"> Login:</td>
                  
                </tr>
                <tr>
                    <td>Usuario:</td>
                    <td><html:text property="usuario" value="felipe"/></td>
                </tr>
                <tr>
                    <td>Senha: </td>
                    <td><html:password property="senha"value="felipe" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><html:submit><bean:message key="botao.ok"/></html:submit></td>
                    
                </tr>

            </tbody>
        </table>
        </html:form>
        
</div>
</body>
</html:html>