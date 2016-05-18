
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="javax.servlet.jsp.PageContext" %>
<%@page session="true"%> 
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title>Categorías</title>
	<link rel=stylesheet type="text/css" href="<c:url value="/resources/admincss/styles.css" />">
	<script src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery.validate.js"/>" type="text/javascript"></script>
</head>
<body>

<script type="text/javascript">
$(function(){
	
	$(".foorm").validate({
		rules:{
			code:"required",
			name:"required"
			/*password:"required"*/
		}, 
		
		messages:{
			code:"el código es requerido",
			name:"el nombre es requerido"
		},
		submitHandler: function(form) {
            form.submit();
        }
	});
});
</script>
<!-- new layout -->

<div id="container">

 
  <div id="header">
    <div id="headleft">
      <h1>EcomMobile<span style="color:#66CCFF">CMS</span></h1>
      <h2>Administración</h2>
    </div>
    <div id="headright">
      <div id="logininfo"><%
      Date date = new Date();
   out.print(date.toString());
%><br />
<!-- csrt for log out-->
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
 
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
      <c:if test="${pageContext.request.userPrincipal.name != null}">
		
			Bienvenido : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		
	</c:if>
	</div>
        <!-- Welcome Administrator|<a href="#">(Logout)</a></div>-->
      <div style="padding:3px 0 0 0;">
       <!--  <form method="post" action="">
          <input class="inputbutton" type="button" value="Search" />
          <input class="inputtext" type="text" />
        </form>-->
      </div>
    </div>
  </div>
  
  
	<div id="content">
  
		<div id="leftcontent">
		  <div class="navigation">
			<h1 class="navhead">Navegación</h1>
			<ul class="menu">
			<c:url value="/admin/home" var="hLink" />
			<c:url value="/admin/users" var="uLink" />
			<c:url value="/admin/categories" var="catLink" />
			<c:url value="/admin/products" var="prodLink" />
			<c:url value="/admin/settings" var="configLink" />
			  <li><a href="${hLink}">Home</a></li>
			  <li><a href="${uLink}">Usuarios</a></li>
			  <li><a href="${catLink}">Categorias</a></li>
			  <li><a href="${prodLink}">Productos</a></li>
			  <li><a href="${configLink}">Configuración</a></li>
			    <c:url value="/admin/sales" var="salesLink" />
			  <li><a href="${salesLink}">Ventas</a></li>
			</ul>
		  </div>

	</div>
	

    <div id="midcontent">
	
      <h1 class="posthead">Modificar categoría</h1>
      <form:form method="post" class="foorm" action="${pageContext.request.contextPath}/admin/updateCategory">
 
    <table>
    <tr>
        <td><form:label path="code">Código:</form:label></td>
        <td><form:input path="code"  /></td> 
    </tr>
    <tr>
        <td><form:label path="name">Nombre:</form:label></td>
        <td><form:input path="name" /></td>
    </tr>
    <tr>
        <td><form:label path="description">Descripción:</form:label></td>
        <td><form:textarea rows="6" path="description" /></td>
    </tr>
    <!-- <tr>
        <td><form:label path="imageUrl">Imagen:</form:label></td>
        <td><form:input path="imageUrl" /></td>
    </tr>-->
     <tr>
    <td colspan="2">
    <font color="red"> <form:errors path="code"></form:errors></font><br />
     <font color="red"> <form:errors path="name"></form:errors></font>
    </td>
     
    </tr>
    
    <tr>
        <td colspan="2">
            <form:input type="hidden" path="id" />
            <input type="submit" value="guardar categoría" class="button"/>
        </td>
    </tr>
</table>  


     
</form:form>  
	  
	  
    </div>
  </div>
  
  
  <div id="footer">
    <h2><a href="${hLink}">Home</a> | 
    <a href="${uLink}">Usuarios</a> | 
    <a href="${catLink}">Categorias</a> | 
    <a href="${prodLink}">Productos</a> | 
    <a href="${configLink}">Configuración</a> 
	<br />
      &copy; 2014 <a href="#">Cristian Sanabria</a> | Design by <a href=
"http://www.flopsoft.co.nr" target="_blank">FlopSoft Inc.</a></h2>
  </div>
</div>
<!-- new layout -->

	
</body>
</html>




