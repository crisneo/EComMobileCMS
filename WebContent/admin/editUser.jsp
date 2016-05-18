
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.jsp.PageContext" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page session="true"%> 
<%@ page import="java.io.*,java.util.*, javax.servlet.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<title>Editar Usuario</title>
	<link rel=stylesheet type="text/css" href="<c:url value="/resources/admincss/styles.css" />">
	<script src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery.validate.js"/>" type="text/javascript"></script>
</head>
<body>

<script type="text/javascript">
$(function(){
	
	$(".foorm").validate({
		rules:{
			name:"required",
			password: {
	            required: true,
	            minlength: 5
	        }
			/*password:"required"*/
		}, 
		
		messages:{
			name:"el nombre es requerido",
			password:"debe introducir una contraseña con 5 caracteres como minimo"
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
	
      <h1 class="posthead">Modificar datos del usuario</h1>
      <form:form method="post" action="${pageContext.request.contextPath}/admin/updateUser" class="foorm">
 
    <table>
    <tr>
        <td><form:label path="name">Nombre(*):</form:label></td>
        <td><form:input path="name" readonly="true" /></td> 
    </tr>
    <tr>
        <td><form:label path="password">Password(*):</form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    <tr>
        <td><form:label path="role">Rol(*):</form:label></td>
        
        <td><form:select path="role" name="role">
		   <form:option value="ROLE_ADMIN">Administrador</form:option>
		   <form:option value="ROLE_USER">Usuario</form:option>
  
		</form:select></td>
    </tr>
   
    <tr>
        <td><form:label path="enabled">Habilitado:</form:label></td>
        <td><form:checkbox path="enabled" /></td>
    </tr>
     <tr>
    <td colspan="2">
     <font color="red"> <form:errors path="name"></form:errors></font><br/>
     <font color="red"> <form:errors path="password"></form:errors></font>
     </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="guardar usuario" class="button"/>
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




