<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html:html>

<head>
       <link rel="shortcut icon" href="Img/icone.gif"type="image/x-icon"/>
       <link href="/Css/Menu.css" rel="stylesheet" type="text/css" />
       <style type="text/css">
       #sddm
{	margin: 0;
	padding: 0;
	z-index: 1}

#sddm li
{	margin: 0;
        z-index:1;
	padding: 0;
	list-style: none;
	float: left;
	font: bold 11px arial}

#sddm li a
{	display: block;
        z-index:1;
	margin: 0 1px 0 0;
	padding: 4px 10px;
	width: 100px;
	background:#7ba2cb;
	color: #FFF;
	text-align: center;
	text-decoration: none}

#sddm li a:hover
{
    background-color: #0d5b83;
}

#sddm div
{	position: absolute;
        z-index:1;
	visibility: hidden;
	margin: 0;
	padding: 0;
	background: #EAEBD8;
	border: 1px solid #5970B2}

	#sddm div a
	{	position: relative;
		display: block;
		margin: 0;
		padding: 5px 10px;
		width: auto;
		white-space: inherit;
		text-align: left;
		text-decoration: none;
		background: #7ba2cb;
		color: #FFF;
		font: 11px arial}

	#sddm div a:hover
	{	background: #0d5b83;
		color: #FFF}

       </style>
       <script type="text/javascript">
       // Copyright 2006-2007 javascript-array.com

var timeout	= 500;
var closetimer	= 0;
var ddmenuitem	= 0;

// open hidden layer
function mopen(id)
{
	// cancel close timer
	mcancelclosetime();

	// close old layer
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';

	// get new layer and show it
	ddmenuitem = document.getElementById(id);
	ddmenuitem.style.visibility = 'visible';

}
// close showed layer
function mclose()
{
	if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';
}

// go close timer
function mclosetime()
{
	closetimer = window.setTimeout(mclose, timeout);
}

// cancel close timer
function mcancelclosetime()
{
	if(closetimer)
	{
		window.clearTimeout(closetimer);
		closetimer = null;
	}
}

// close layer when click-out
document.onclick = mclose;
</script>
</head>

<body>

    
    <div id="menu">
      <img src="Img/agenda_img.jpg" alt="agenda"/>
  </div>
    <div id="menu_menu">
  <ul id="sddm">
    <li><a href="#"
        onmouseover="mopen('m1')"
        onmouseout="mclosetime()">Telefones</a>
        <div id="m1"
            onmouseover="mcancelclosetime()"
            onmouseout="mclosetime()">
        <html:link page="/Consulta.do">Consultar</html:link>
        <html:link page="/CadastroForm.do">Cadastrar</html:link>
        </div>
    </li>
    <li><a href="#"
        onmouseover="mopen('m2')"
        onmouseout="mclosetime()">Contabilidade</a>
        <div id="m2"
            onmouseover="mcancelclosetime()"
            onmouseout="mclosetime()">
       <html:link page="/Compromisso.do?comando=listarCompromissos">Cadastrar</html:link>
       <html:link page="/Compromisso.do?comando=listarCompromissos">Listar</html:link>
       <html:link page="/CompromissoRelatorio.do">Relatórios</html:link>
        </div>
    </li>
    <li><a href="#">Administração</a></li>
    <li><html:link page="/CadastroConfig.do">Configurações</html:link></li> 
    <li><html:link page="/Login.do">Login</html:link></li>
    <li><a href="#">Paginas Senhas</a></li>
</ul>
<div style="clear:both"></div>
</div>
  <!--<div id="menu_menu">_dezembro
  

  <html:link page="/CadastroForm.do">Cadastrar</html:link> |
  <html:link page="/Consulta.do">Consultar</html:link>|
  <html:link page="/CadastroConfig.do">Configuração</html:link> |
  <html:link page="/Login.do">Login</html:link> |
<html:link page="/Compromisso.do">Contabilidade</html:link> |
<html:link page="/Compromisso.do?comando=listarCompromissos">ListaContabil</html:link> |
<html:link page="/Administracao.do">Administração</html:link> 
<html:link page="/Anotacao.do">Anotacoes</html:link>    </div>
-->
</body> 
</html:html>
