<%--
    Document   : compromisso
    Created on : 10/07/2008, 22:54:26
    Author     : Felipe
--%>
<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html:html>
    <head>
        <link href="../Css/Fontes.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Tabela.css" rel="stylesheet" type="text/css" />
        <link href="../Css/Leyout.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript"  language="JavaScript" src="../JS/Mascara.js"></script>
        <script type="text/javascript"  language="JavaScript" src="../JS/Funcoes.js"></script>
        <!-- DWR IMPORTS-->
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/CompromissoAjax.js"></script>
        <!--DWR FUNCOES DE COMBO-->
        <script type="text/javascript"  language="JavaScript">
            function pegarIdTipoCompromisso(){
                var id = document.getElementById('comboTipoCompromisso').value;
                carregaComboSubTipo(id);
            }

            function cleanAddOptions(local){
                DWRUtil.removeAllOptions(local);
            }
            function carregaComboSubTipo(id){
                CompromissoAjax.pesquisaSubTipoCompromisso(id,montaComboSubTipo);
            }
            function montaComboSubTipo(listBeans){
                DWRUtil.removeAllOptions("comboSubTipo");
                DWRUtil.addOptions("comboSubTipo", listBeans, "id","descSubTipoCompromisso");
            }
            function pegarTitulo(){
                var i = document.getElementById("comboSubTipo").selectedIndex;
                var tituloSub = document.getElementById('comboSubTipo').options[i].text;
                document.getElementById('titulo').value=tituloSub;


            }

        </script>




        <script language="JavaScript">
function move(MenuOrigem, MenuDestino){
    var arrMenuOrigem = new Array();
    var arrMenuDestino = new Array();
    var arrLookup = new Array();
    var i;
    for (i = 0; i < MenuDestino.options.length; i++){
        arrLookup[MenuDestino.options[i].text] = MenuDestino.options[i].value;
        arrMenuDestino[i] = MenuDestino.options[i].text;
    }
    var fLength = 0;
    var tLength = arrMenuDestino.length;
    for(i = 0; i < MenuOrigem.options.length; i++){
        arrLookup[MenuOrigem.options[i].text] = MenuOrigem.options[i].value;
        if (MenuOrigem.options[i].selected && MenuOrigem.options[i].value != ""){
            arrMenuDestino[tLength] = MenuOrigem.options[i].text;
            tLength++;
        }
        else{
            arrMenuOrigem[fLength] = MenuOrigem.options[i].text;
            fLength++;
        }
    }
    arrMenuOrigem.sort();
    arrMenuDestino.sort();
    MenuOrigem.length = 0;
    MenuDestino.length = 0;
    var c;
    for(c = 0; c < arrMenuOrigem.length; c++){
        var no = new Option();
        no.value = arrLookup[arrMenuOrigem[c]];
        no.text = arrMenuOrigem[c];
        MenuOrigem[c] = no;
    }
    for(c = 0; c < arrMenuDestino.length; c++){
        var no = new Option();
        no.value = arrLookup[arrMenuDestino[c]];
        no.text = arrMenuDestino[c];
        MenuDestino[c] = no;
        MenuDestino[c].selected = true;
   }
}


</script>



        <title>Relatórios</title>

    </head>
    <body onload="pegarIdTipoCompromisso();pegarTitulo();">

        <c:out value="${ERROR}"/>
        <div id="conteudo">
            <html:form action="CompromissoRelatorio.do?comando=relatorioConfig" >
                <table width="80%%" border="1" id="tabela_cadastro">

                    <tr>
                        <td colspan="2"><div align="center">Relatório Contabil Anual</div></td>
                        <td width="555"><div align="center"></div></td>
                    </tr>
                    <tr>
                        <td width="238">Tipo de compromisso</td>

                        <td width="236">
                            <select name="idTipoCompromisso" onchange="pegarIdTipoCompromisso()" id="comboTipoCompromisso">
                                <c:forEach var="tipoCompromisso" items="${IdTipoCompromisso}">
                                    <option  value="${tipoCompromisso.id}"><c:out value="${tipoCompromisso.desc_tipo_compromisso}"/></option>
                                </c:forEach>
                            </select>

                        </td>
                        <td>Tipo de compromisso</td>
                    </tr>
                    <tr>

                        <td>Subtipo </td>
                        <td>


                            <select id="comboSubTipo" onchange="pegarTitulo()" name="idSubTipoCompromisso"></select>


                        </td>

                    <tr>
                        <td>Data</td><td>
                            Ano
                            <html:text property="ano" name="ano" value="${anoAtual}" /></td>
                    </tr>

                    <tr>

                        <td>        <html:submit><bean:message key="botao.buscar"/> </html:submit>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                </table>

            </html:form>
            <html:form  action="CompromissoRelatorio.do?comando=relatorioConfigMesclado">
            <table width="30%" border="1" id="tabela_cadastro">
                <thead>
                    <tr>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Relatório Mesclado</td>
                        
                    </tr>
                       <tr>
                        <td>Ano:<html:text property="ano" name="ano" value="${anoAtual}" /></td>
                        
                    </tr>
                    <tr>
                        <td><select multiple name="list1"  id="list1" size="25">
                                <c:forEach var="subTipo" items="${IdRelatorioMesclado}">
                                    <option  value="${subTipo.id}"><c:out value="${subTipo.descSubTipoCompromisso}"/></option>
                                </c:forEach>
                            </select>
                            <input type="Button" value=">>" name="buttonSeleciona" onclick="move(this.form.list1,this.form.list2)" />
                            <select multiple name="list2" id="list2" size="25">

                            </select></td>
                    </tr>

                       <tr>
                           <td><input type="submit" value="Enviar" /></td>
                        
                    </tr>
                </tbody>
            </table>


</html:form>
        </div>
    </body>
</html:html>
