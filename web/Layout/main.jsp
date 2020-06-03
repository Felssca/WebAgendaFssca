<%@ taglib uri="struts-bean.tld" prefix="bean" %>
<%@ taglib uri="struts-html.tld" prefix="html" %>
<%@ taglib uri="struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link href="../Css/Layout.css" rel="stylesheet" type="text/css" />
       <link href="../Css/Fontes.css" rel="stylesheet" type="text/css" />
       <link href="../Css/Tabela.css" rel="stylesheet" type="text/css" />
        <title>AgendaWebFssca 1.5</title>
    </head>
    <body>
         <tiles:insert attribute="cabecalho"/>     
         <tiles:insert attribute="corpo"/>  
          <tiles:insert attribute="rodape"/>  
    </body>
</html>
