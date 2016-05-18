
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
	<title>Configuraci�n</title>
	<link rel=stylesheet type="text/css" href="<c:url value="/resources/admincss/styles.css" />">
	<c:url value="/resources/tinymce/js/tinymce/tinymce.min.js" var="tinyMceJavaScript" />  
  
<!-- TinyMCE -->  
<script  src="${tinyMceJavaScript}" type="text/javascript"></script>  
	
<script>
        tinymce.init({selector:'textarea'});
</script>
<script src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery.validate.js"/>" type="text/javascript"></script>
</head>
<body>

<script type="text/javascript">
$(function(){
	
	$(".foorm").validate({
		rules:{
			name:"required",
			info:"required",
			starProductCode:"required",
			storeEmail:"required",
			payPalAccount:"required"
			/*password:"required"*/
		}, 
		
		messages:{
			name:"este campo es requerido",
			info:"este campo es requerido",
			starProductCode:"este campo es requerido",
			storeEmail:"se required un email de contacto",
			payPalAccount:"se requiere una cuenta de PayPal para recibir pagos"
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
      <h2>Administraci�n</h2>
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
			<h1 class="navhead">Navegaci�n</h1>
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
			  <li><a href="${configLink}">Configuraci�n</a></li>
			    <c:url value="/admin/sales" var="salesLink" />
			  <li><a href="${salesLink}">Ventas</a></li>
			</ul>
		  </div>

	</div>
	

    <div id="midcontent">
	
      <h1 class="posthead">Configuraci�n</h1>
      <form:form method="post" action="updateSettings" class="foorm" >
 
    <table>
    <tr>
        <td><form:label path="name">Nombre(*):</form:label></td>
        <td><form:input path="name" /></td> 
    </tr>
    <tr>
        <td><form:label path="info">Descripci�n(*):</form:label></td>
        <td>
        <form:textarea path="info" id="textarea"/>
       
        </td>
    </tr>
    <tr>
        <td><form:label path="starProductId">Producto principal(*):</form:label></td>
        
        <td>
        
		 <form:select path="starProductId" items="${products}" itemLabel="name" itemValue="id">  
        </form:select>
		</td>
    </tr>
    
    <tr>
        <td><form:label path="storeEmail">Email de contacto(*):</form:label></td>
        <td><form:input path="storeEmail" /></td> 
    </tr>
    
     <tr>
        <td><form:label path="payPalAccount">Cuenta de PayPal(*):</form:label></td>
        <td><form:input path="payPalAccount" /></td> 
    </tr>
   
   
   
    <tr>
        <td colspan="2">
            <input type="submit" value="Guardar" class="button"/>
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
    <a href="${configLink}">Configuraci�n</a> 
	<br />
      &copy; 2014 <a href="#">Cristian Sanabria</a> | Design by <a href=
"http://www.flopsoft.co.nr" target="_blank">FlopSoft Inc.</a></h2>
  </div>
</div>
<!-- new layout -->

	
</body>
</html>




