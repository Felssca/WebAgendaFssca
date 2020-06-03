<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="br.com.agenda.action.vo.VOCompromisso" %>
<html:html>
<head>
    <link href="Css/Fontes.css" rel="stylesheet" type="text/css" />
    <link href="Css/Layout.css" rel="stylesheet" type="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<html:base/>
</head>

<body>
    <div id="conteudo">
    <logic:empty name="consultaSeletiva">
   <bean:message key="reg.vazio"/>
</logic:empty>
<div id="titulo_busca">
    <logic:iterate id="consultas" name="consultaSeletiva">

 <html:link page="/Edicao.do?comando=deletar"
              paramName="consultas" paramProperty="id" paramId="idDelecao">
              <html:img src="../Img/deletar.gif" border="0"/>
              </html:link>
          <html:link page="/Edicao.do"
                   paramName="consultas" paramProperty="id" paramId="idEdicao" >
                   <html:img src="../Img/editar.gif" border="0"/>
           </html:link></div>
        <div id="imagem"><img src="../Fotos/<bean:write name="consultas" property="foto" />"
             alt="Foto" width="250" height="200"/></div>
<div id="tabela_conteudo">
    <fieldset id="fieldset_seletivo">
      <table border="0"  cellspacing="2">
<tbody>
    <thead>
        <th class="fonte_pequena">Informações Gerais </th>
        </thead>
<tr>
    <td><b>Nome:</b></td>
    <td><b><bean:write name="consultas" property="nome" /></b></td>
</tr>
<tr>
    <td><b>Categoria:</b></td>
<td><bean:write name="consultas" property="tipo"/></td>
</tr>
<tr>
    <td><b>Aniversario:</b></td>
<td><bean:write name="consultas" property="aniversario" format="dd/MM/yyyy"/></td>
</tr>
<tr>
    <td><b>Telefone Res:</b></td>
<td><bean:write name="consultas" property="telefone"/></td>
</tr>
<tr>
    <td><b>Telefone Com:</b></td>
<td><bean:write name="consultas" property="telefone2"/></td>
</tr>
<tr>
    <td><b>Telefone Out:</b></td>
<td><bean:write name="consultas" property="telefone3"/></td>
</tr>
<tr>
<td><b>Celular:</b></td>
<td><bean:write name="consultas" property="celular"/></td>
</tr>
<tr>
<td><b>Celular 2:</b></td>
<td><bean:write name="consultas" property="celular2"/></td>
</tr>
<tr>
<td><b>Celular 3</b></td>
<td> <bean:write name="consultas" property="celular3"/></td>
</tr>
<tr>
<td><b>Email:</b></td>
<td><bean:write name="consultas" property="email"/></td>
</tr>
<tr>
<td><b>Email 2:</b></td>
<td><bean:write name="consultas" property="email2"/></td>
</tr>
<tr>
<td><b>Email 3:</b></td>
<td><bean:write name="consultas" property="email3"/></td>
</tr>
<tr>
    <td><b>Endereço:</b></td>
<td><bean:write name="consultas" property="endereco"/></td>
</tr>

<tr>
<td><img src="../Img/msn.gif"/><b>Msn:</b></td>
<td><bean:write name="consultas" property="msn"/></td>
</tr>
<tr>
<td><img src="../Img/orkut.gif"/><b>Orkut:</b></td>
<td><bean:write name="consultas" property="orkut"/></td>
</tr>
<tr>
<td><img src="../Img/skype.gif"/><b>Skype:</b>
</td>
<td><bean:write name="consultas" property="skype"/></td>
<tr>
    <td><bean:write name="consultas" property="skype"/></td>
<td><b>Detalhes:</b></td>
<td><bean:write name="consultas" property="carac"/></td>
</tr>
</tbody>
</table>
</fieldset>
</div>
<div id="titulo_busca">fim</div>

</div><p>
</logic:iterate>
</body>
</html:html>
