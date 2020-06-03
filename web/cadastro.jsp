<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html:html>

    <head>
        <link href="Css/Tabela.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="JS/Mascara.js"></script>
           <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <html:base/>
    </head>
    <body>
        <c:out value="${ERRO}"/>
      
        <html:form action="/CadastroFormValida.do?comando=cadastrar" enctype="multipart/form-data" acceptCharset="UTF-8"
                   onsubmit="return validateFormCadastro(this)">
          
        
            <table width="740" border="1" id="tabela_cadastro">
                <th colspan="5">Cadastrar novo contato.</th>
                <tr>
                    <td width="128"><bean:message key="campo.nome" />*</td>
                    <td width="154">
                        <html:text property="nome"/>
                    </td>
                    <td width="20" rowspan="12">&nbsp;</td>
                    <td width="148">Data de Aniversário*</td>
                    <td width="242"><html:text property="aniversario" onkeypress="Mascara('DATA',this,event);" maxlength="10"/></td>
                </tr>
                <tr>
                    <td><bean:message key="campo.telefone"/>*</td>
                    <td> 
                    <html:text property="telefone" onkeypress="Mascara('TEL',this,event);"/></td>
                    <td>Endereço</td>
                    <td><html:text property="endereco"/></td>
                </tr>
                <tr>
                    <td><bean:message key="campo.telefone2" /></td>
                    <td>
                        <html:text property="telefone2" onkeypress="Mascara('TEL',this,event);" maxlength="14"/>
                    </td>
                    <td>Endereço comercial</td>
                    <td><label>
                            <html:text property="endereco2"/>
                    </label></td>
                </tr>
                <tr>
                    <td> Telefone 3</td>
                    <td><html:text property="telefone3" onkeypress="Mascara('TEL',this,event);" maxlength="14"/></td>
                    <td>Endereço 3</td>
                    <td><html:text property="endereco3"/></td>
                </tr>
                <tr>
                    <td><bean:message key="campo.celular"/></td>
                    <td><html:text property="celular"/></td> 
                    <td>Categoria:</td>
                    <td>
                        <select name="tipo">
        <logic:iterate id="lista" name="listaCategoria">
            <option value="<bean:write name="lista" property="tipo"/>">
                <bean:write name="lista" property="tipo"/></option>
            </logic:iterate>
                </select><br/>
                        <a href="/categoria.jsp" target="_blank"
                         onclick="window.open(this.href, this.target,'width=400,height=100');return false;">
                      Adicionar item</a>
                     
                    </td>
                </tr>
                <tr>
                    <td>Celular 2</td>
                    <td><html:text property="celular2"/></td>
                    <td>Região:</td>
                    <td><select name="categoria2" id="categoria2">
                    </select></td>
                </tr>
                <tr>
                    <td>Celular 3</td>
                    <td><html:text property="celular3"/></td>
                    <td>Msn</td>
                    <td><html:text property="msn"/></td>
                </tr>
                <tr>
                    <td><bean:message key="campo.email"/></td>
                    <td><html:text property="email"/></td>
                    <td>Skype</td>
                    <td><html:text property="skype"/></td>
                </tr>
                <tr>
                    <td>E-mail 2</td>
                    <td><html:text property="email2"/></td>
                    <td>Orkut</td>
                    <td><html:text property="orkut"/></td>
                    
                </tr>
                <tr>
                    <td>E-mail 3</td>
                    <td><html:text property="email3"/></td>
                    <td>Foto</td>
                    <td><html:file property="fotoFile"/></td>
                    
                </tr>
                <tr>
                    <td>Site</td>
                    <td><html:text property="site"/></td>
                </tr>
                <tr c>
                    <td colspan="2"><div align="center">Observações</div></td>
                    <td colspan="2" rowspan="2"></td>
                </tr>
                <tr>
                    <td colspan="2"><label>
                            <html:textarea property="carac"/>
                    </label></td>
                </tr>
                <tr>
                    <td colspan="5" align="center"><html:submit><bean:message key="botao.salvar"/> </html:submit>
                    <html:reset><bean:message key="botao.limpar"/></html:reset></td>
                </tr>
            </table>
            <html:javascript formName="FormCadastro"/>
        </html:form>
    </body>
    </html:html>
    