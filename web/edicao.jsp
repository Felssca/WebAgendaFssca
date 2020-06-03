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
        <link href="Css/Tabela.css" rel="stylesheet" type="text/css" />
        <link href="Css/Layout.css" rel="stylesheet" type="text/css" />
        <script language="JavaScript" src="JS/Mascara.js"></script>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    </head>
    <body>
        <html:form action="/Edicao.do?comando=editar"
                   enctype="multipart/form-data" method="POST" acceptCharset="UTF-8">
            <logic:iterate id="edicao" name="consultaSeletiva">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Edição</th>
                            <th></th>
                            <th></th>
                            <th></th>
                   
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><bean:message key="campo.nome" /></td>
                            <td><html:text name="edicao" property="nome"/>
                            <html:hidden name="edicao" property="id"/></td>
                            <td>Data Aniversário</td>
                            <td><html:text name="edicao" property="aniversario" onkeypress="Mascara('DATA',this,event);" maxlength="10" /></td>

                        </tr>
                        <tr>
                            <td><bean:message key="campo.telefone"/></td>
                            <td><html:text name="edicao"property="telefone"/></td>
                            <td>Endereço</td>
                            <td><html:text name="edicao" property="endereco"/></td>
                      
                        </tr>
                        <tr>
                            <td><bean:message key="campo.telefone2"/></td>
                            <td><html:text name="edicao" property="telefone2"/></td>
                            <td>Endereço comercial</td>
                            <td><html:text name="edicao"property="endereco2"/></td>
                           
                        </tr>
                        <tr>
                            <td>Telefone 3</td>
                            <td><html:text name="edicao" property="telefone3"/></td>
                            <td>Endereço 3</td>
                            <td><html:text name="edicao" property="endereco3"/></td>
                          
                        </tr>
                        <tr>
                            <td><bean:message key="campo.celular"/></td>
                            <td><html:text name="edicao"property="celular"/></td>
                            <td>Categoria:</td>
                            <td><select name="tipo">
                                    <logic:iterate id="lista" name="listaCategoria">
                                        <option value="<bean:write name="lista" property="tipo" />">
                                        <bean:write name="lista" property="tipo"/></option>
                                    </logic:iterate>

                                </select></td>
                           
                        </tr>
                        <tr>
                            <td>Celular 2</td>
                            <td><html:text name="edicao" property="celular2"/></td>
                            <td>MSN</td>
                            <td><html:text name="edicao" property="msn"/></td>
                         
                        </tr>
                        <tr>
                            <td>Celular 3</td>
                            <td><html:text name="edicao" property="celular3"/></td>
                               <td>Skype</td>
                            <td><html:text name="edicao" property="skype"/></td>
                         
                        </tr>
                        <tr>
                            <td><bean:message key="campo.email"/></td>
                            <td><html:text name="edicao" property="email"/></td>
                             <td>Orkut</td>
                            <td><html:text name="edicao" property="orkut"/></td>
                         
                        </tr>
                        <tr>
                            <td>E-mail 2</td>
                            <td><html:text name="edicao" property="email2"/></td>
                            <td>Site</td>
                            <td><html:text name="edicao" property="site"/></td>
                         
                        </tr>
                        <tr>
                            <td>E-mail 3</td>
                            <td><html:text name="edicao" property="email3"/></td>
                            <td></td>
                            <td></td>
                        
                        </tr>
                        <tr>
                            <td colspan="4">Detalhes</td>
                            <td></td>
                            <td></td>
                            <td></td>
                        
                        </tr>

                          <tr>
                            <td colspan="4"><html:textarea property="carac"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                           
                        </tr>

                      <tr>
                          <td colspan="5"><html:submit><bean:message key="botao.salvar"/></html:submit>
                          <html:reset><bean:message key="botao.limpar"/></html:reset></td>
                          <td></td>
                            <td></td>
                            <td></td>
                         
                        </tr>
                   
                    </tbody>
                </table>
                          <div id="imagem_edicao">
                              <img src="../Fotos/<bean:write name="edicao" property="foto"/>"
                            alt="Foto" width="250" height="200"/><p>
                              <html:file name="edicao" property="fotoFile" />
                            <html:hidden name="edicao" property="foto" />
                          </div>
            </logic:iterate>
        </html:form>

    </body>
</html:html>
