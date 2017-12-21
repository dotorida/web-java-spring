<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<DOCTYPE html>
<html>
<head>
    <title>${titulo}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:set var="path" value="${pageContext.request.contextPath}" scope="request"/>
		<title>${titulo} - ${pageContext.request.contextPath}</title>
        
        <style type="text/css">
            @IMPORT url("${path}/static/bootstrap/css/bootstrap.min.css");
            @IMPORT url("${path}/static/bootstrap/css/bootstrap-theme.min.css");
        </style>
        <script type="text/javascript" src="${path}/static/js/jquery-2.1.3.min.js"></script>
            <script type="text/javascript" src="${path}/static/bootstrap/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="${path}/static/js/pizzas.js"></script>
               
</head>
<body>
   <div class="container">
        <jsp:include page="../menu.jsp"/>
        
    
        <c:if test="${not empty mensagemErro}">
            <div>
                <div class="alert alert-danger">${mensagemErro}</div>
            </div>
        </c:if>
        
        <c:if test="${not empty mensagemInfo}">
            <div>
                <div class="alert alert-info">${mensagemInfo}</div>
            </div>
        </c:if>
        
         <section  id="secao-pizza">
            <jsp:include page="tabela-pizza.jsp" />
        </setion>
            
            <jsp:include page="modal-pizza.jsp" />
         
    </div>
        
            
</body>
</html>