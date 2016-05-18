<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/admin/users" var="uLink" />
<c:url value="/admin/categories" var="catLink" />
<c:url value="/admin/products" var="prodLink" />
<c:url value="/admin/settings" var="configLink" />
<table border="0" cellpadding="0" cellspacing="0" width="224" height="254">
					      	<tr>
					      		<td valign="top" align="center">
									<div style="padding-top: 18px"><a href="${uLink}" class="menu">Home</a></div>
									<div style="padding-top: 15px"><a href="${uLink}" class="menu">Usuarios</a></div>
									<div style="padding-top: 18px"><a href="${catLink}" class="menu">Categorías</a></div>
									<div style="padding-top: 19px"><a href="${prodLink}" class="menu">Productos</a></div>
									<div style="padding-top: 23px"><a href="${configLink}" class="menu">Configuración</a></div>
									

									
									
								</td>
					      	</tr>
</table>