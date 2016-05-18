<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${info.name}</title>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/endUser/style.css"/>" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript"
	src="<c:url value="/resources/endUser/js/boxOver.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/searchPage.js"/>" />


</head>
<body>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSearch").click(function(e) {
				var text = $("#txtSearch").val();

				FindNext(text);
				var myHilitor = new Hilitor();
				myHilitor.apply(text);

			});

		});
	</script>
	<div id="main_container">
		<div class="top_bar">


			<div class="top_search">
				<div class="search_text">
					<!-- <a href="http://www.free-css.com/">Advanced Search</a>-->
					Buscar:
				</div>
				<input type="text" class="search_input" name="search" id="txtSearch" />
				<input type="image"
					src="<c:url value="/resources/endUser/images/search.gif" />"
					class="search_bt" id="btnSearch" />
				<!--  <img src="<c:url value="/resources/endUser/images/search.gif" />" class="search_bt" id="btnSearch" ></img>-->

			</div>

			<div class="languages">
				<!-- <div class="lang_text">Languages:</div>
      <a href="http://www.free-css.com/" class="lang">
      <img src="<c:url value="/resources/endUser/images/en.gif" />" alt="" border="0" /></a> 
      <a href="http://www.free-css.com/" class="lang">
      <img src="<c:url value="/resources/endUser/images/de.gif"/>" alt="" border="0" /></a> -->

				<!-- user status -->
				<div class="lang_text">


					<c:url value="/j_spring_security_logout" var="logoutUrl" />


					<form action="${logoutUrl}" method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>

					<script type="text/javascript">
						function formSubmit() {
							document.getElementById("logoutForm").submit();
						}
					</script>
					<c:choose>
						<c:when test="${pageContext.request.userPrincipal.name != null}">
			  	Bienvenido : ${pageContext.request.userPrincipal.name} | <a
								href="javascript:formSubmit()"> Salir</a>
						</c:when>
						<c:otherwise>
							<a href="<c:url value="/login"/>">Ingresar</a>
						</c:otherwise>
					</c:choose>
				</div>
				<!-- /user status -->


			</div>

		</div>
		<div id="header">
			<div id="logo">
				<img src="<c:url value="/resources/endUser/images/logo.png" />"
					alt="" border="0" width="237" height="140" />
			</div>
			<div class="oferte_content">
				<div class="top_divider">
					<img
						src="<c:url value="/resources/endUser/images/header_divider.png" />"
						alt="" width="1" height="164" />
				</div>
				<div class="oferta">
					<div class="oferta_content">
						<c:url value="/imageProcuct/${starProduct.code}" var="starImage" />
						<img src="${starImage}" width="94" height="92" alt="" border="0"
							class="oferta_img" />
						<div class="oferta_details">
							<div class="oferta_title">${starProduct.name}</div>
							<div class="oferta_text">${starProduct.description}</div>
							<c:url value="/Product/${starProduct.code}" var="starLink" />
							<a href="${starLink}" class="details">Detalles</a>
						</div>
					</div>
					<div class="oferta_pagination"></div>
				</div>
				<div class="top_divider">
					<img
						src="<c:url value="/resources/endUser/images/header_divider.png" />"
						alt="" width="1" height="164" />
				</div>
			</div>
			<!-- end of oferte_content-->
		</div>
		<div id="main_content">
			<div id="menu_tab">
				<div class="left_menu_corner"></div>
				<ul class="menu">
					<li><a href="<c:url value="/home" />" class="nav1"> Tienda</a></li>
					<li class="divider"></li>
					<li><a href="<c:url value="/" />" class="nav2">Productos</a></li>
					<li class="divider"></li>

					<li><a href="<c:url value="/contact" />" class="nav6">Contacto</a></li>
					<li class="divider"></li>
					<li><a href="<c:url value="/searchProduct" />" class="nav3">Buscar
							Productos</a></li>
					<li class="divider"></li>

				</ul>
				<div class="right_menu_corner"></div>
			</div>
			<!-- end of menu tab -->
			<div class="crumb_navigation">
				Navegacion: <span class="current">Carrito de compras</span>
			</div>
			<div class="left_content">
				<div class="title_box" style="visibility: hidden;">Categorías</div>
				<ul class="left_menu">



				</ul>
			</div>
			<!-- end of left content -->
			<div class="center_content" style="text-align: center;">
				<c:url value="/loggedUser/commitCart" var="link" />

				<div class="center_title_bar">Productos Seleccionados</div>
				<div class="prod_box_big">
					<table class="custom" id="rounded-corner">

						<tbody>
							<tr>
								<th>Producto</th>
								<th>Precio</th>
							</tr>

							<c:forEach items="${productsInCart}" var="prod">
								<tr class="rowdark">

									<td>${prod.productName}</td>
									<td>${prod.price}</td>
								</tr>
							</c:forEach>
							<tr>
								<td>Total:</td>
								<td><%=session.getAttribute("totalPrice") != null ? session
					.getAttribute("totalPrice") : 0%> Euros</td>
							</tr>
						</tbody>
						<tfoot></tfoot>
					</table>

					<br></br> Realizar pago:<br></br> <img
						src="<c:url value="/resources/endUser/images/payment.gif" />"
						alt="" /> <br></br>
					<form name="_xclick" action="https://www.paypal.com/cgi-bin/webscr"
						method="post" id="paypal">
						<input type="hidden" name="cmd" value="_xclick"> <input
							type="hidden" name="business" value="${payPalAccount}"><input
							type="hidden" name="currency_code" value="EUR"><input
							type="hidden" name="item_name" value="shopping cart"><input
							type="hidden" name="amount"
							value="<%=session.getAttribute("totalPrice") != null ? session
					.getAttribute("totalPrice") : 0%>">
							<!-- <input type="image" src="http://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif" border="0"  name="submit" alt="Make payments with PayPal - it's fast, free and secure!">-->
					</form>
					<input type="image" onclick="sendConfirm();"
						src="http://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
						border="0" id="pay" name="pay"
						alt="Make payments with PayPal - it's fast, free and secure!">
				</div>

				<script type="text/javascript">
					function sleep(milliseconds) {
						var start = new Date().getTime();
						for (var i = 0; i < 1e7; i++) {
							if ((new Date().getTime() - start) > milliseconds) {
								break;
							}
						}
					}

					function showModalDialg() {
						var el = document.getElementById("overlay");
						el.style.visibility = (el.style.visibility == "visible") ? "hidden"
								: "visible";
					}

					function closeModalDialog() {
						document.getElementById("overlay").style.visibility = 'hidden';
					}
					
					function sendConfirm() {
						showModalDialg();
						$.ajax({
							data : {},
							url : "${link}",
							type : 'post',
							beforeSend : function() {

							},
							success : function(response) {
								
								sleep(3000);
								closeModalDialog();
								$("#paypal").submit();
							},
							error : function(err) {
								//showModalDialg();
								sleep(3000);
								closeModalDialog();
								$("#paypal").submit();
							}
						});
					}
				</script>
				<div id="overlay">
					<div>
						<p>Procesando la compra, sera redireccionado en un momento...</p>

					</div>
				</div>
			</div>
			<!-- end of center content -->
			<div class="right_content">



				<!-- end of right content -->
			</div>
		</div>
		<!-- end of main content -->
		<div class="footer">
			<div class="left_footer">
				<img
					src="<c:url value="/resources/endUser/images/footer_logo.png"/>"
					alt="" width="170" height="49" />
			</div>
			<div class="center_footer">
				Usal copyright 2014<br />
				<!--  <a href="http://csscreme.com">
      <img src="images/csscreme.jpg" alt="csscreme" border="0" />
      </a>-->
				<br />
				<!-- <img src="<c:url value="/resources/endUser/images/payment.gif" />" alt="" />-->
			</div>
			<div class="right_footer">
				<a href="<c:url value="/home" />">Home</a> <a
					href="<c:url value="/viewproducts" />">Productos</a> <a
					href="<c:url value="/contact" />">Contacto</a>
			</div>


		</div>
		<!-- end of main_container -->
	</div>
</body>
</html>

