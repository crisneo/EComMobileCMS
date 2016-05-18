<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">

<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>${info.name}</title>

<link rel="stylesheet"
	href="<c:url value="/resources/mobile/jquery.mobile-1.3.1.min.css"/>" />
<script src="<c:url value="/resources/mobile/jquery-1.9.1.min.js"/>"></script>
<script
	src="<c:url value="/resources/mobile/jquery.mobile-1.3.1.min.js"/>"></script>
<link rel="stylesheet"
	href="<c:url value="/resources/endUser/mobile/css/style.css"/>" />



</head>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	<!-- tested -->
	<div data-role="page" id="pageAbout">
		<div data-theme="c" data-role="header" class="generalContent">
			<div
				style="width: 100%; background:url('<c:url value="/resources/endUser/images/header_bg.jpg" />') no-repeat center;">
				<img src="<c:url value="/resources/endUser/images/logo.png" />"
					alt="image">
			</div>
			<h3 style="text-align: right; margin-right: 10px;">
				<!-- user status -->


				<c:choose>
					<c:when test="${pageContext.request.userPrincipal.name != null}">
			  	Bienvenido : ${pageContext.request.userPrincipal.name} | <a
							href="javascript:formSubmit()"> Salir</a>
					</c:when>
					<c:otherwise>
						<a href="<c:url value="/login"/>" class="join" rel="external">Ingresar</a>
					</c:otherwise>
				</c:choose>
				<!-- /user status -->


			</h3>


		</div>
		<div data-role="navbar">
			<ul>
				<li><a href="<c:url value="/home"  />" data-theme="c"
					rel="external">Tienda</a></li>
				<li><a href="<c:url value="/"  />" data-theme="c"
					rel="external">Productos</a></li>
				<li><a href="<c:url value="/contact"  />" data-theme="c"
					rel="external">Contacto</a></li>
				<li><a href="<c:url value="/loggedUser/viewCart"  />"
					rel="external" class="ui-btn-active ui-state-persist"
					data-theme="c">Carrito</a></li>
				<li><a href="<c:url value="/searchProduct"  />" data-theme="c"
					rel="external">Buscar</a></li>
			</ul>
		</div>

		<div data-role="content" class="generalContent">

			<div class="ui-corner-all custom-corners">
				<div class="ui-bar ui-bar-d">
					<h3>Carrito de compras</h3>
				</div>
				<div class="ui-body ui-body-d">
					<table class="gradienttable" id="rounded-corner" data-role="table"
						data-mode="columntoggle">
						<thead>
							<tr>
								<td>Producto</td>
								<td>Precio</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productsInCart}" var="prod">
								<tr>

									<td>${prod.productName}</td>
									<td>${prod.price}</td>
								</tr>
							</c:forEach>
							<tr>
								<td>Total:</td>
								<td><%=session.getAttribute("totalPrice") != null ? session
					.getAttribute("totalPrice") : 0%>
									Euros</td>
							</tr>
						</tbody>
						<tfoot></tfoot>
					</table>
					<br></br> Realizar pago: <br /> <img
						src="<c:url value="/resources/endUser/images/payment.gif" />"
						alt="" /><br />
					<form name="_xclick" action="https://www.paypal.com/cgi-bin/webscr"
						method="post" id="paypal">
						<input type="hidden" name="cmd" value="_xclick"> <input
							type="hidden" name="business" value="${payPalAccount}"> <input
							type="hidden" name="currency_code" value="EUR"> <input
							type="hidden" name="item_name" value="shopping cart"> <input
							type="hidden" name="amount"
							value="<%=session.getAttribute("totalPrice") != null ? session
					.getAttribute("totalPrice") : 0%>">
						<!-- <input type="image" src="http://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif" border="0" name="submit" alt="Make payments with PayPal - it's fast, free and secure!">-->
					</form>
					<input type="image" onclick="sendConfirm();"
						src="http://www.paypalobjects.com/en_US/i/btn/btn_buynow_LG.gif"
						border="0" id="pay" name="pay"
						alt="Make payments with PayPal - it's fast, free and secure!">
				</div>
				<c:url value="/loggedUser/commitCart" var="link" />
				<script type="text/javascript">
					function sendConfirm() {
						$('#popupDialog').popup('open');
						$.ajax({
							data : {},
							url : "${link}",
							type : 'post',
							beforeSend : function() {

							},
							success : function(response) {
								
								sleep(5000);
								$('#popupDialog').popup('close');
								$("#paypal").submit();
							},
							error : function(err) {
								//$('#popupDialog').popup('open');
								sleep(5000);
								$('#popupDialog').popup('close');
								$("#paypal").submit();
							}
						});

					}

					function sleep(milliseconds) {
						var start = new Date().getTime();
						for (var i = 0; i < 1e7; i++) {
							if ((new Date().getTime() - start) > milliseconds) {
								break;
							}
						}
					}
				</script>
			</div>
		</div>
		<div data-role="popup" id="popupDialog" data-overlay-theme="b" data-dismissible="false" style="height:100px;">
			<h4></h4>
			<p>Procesando la compra, sera redireccionado en un momento....</p>
		</div>


		<c:url value="/resources/endUser/mobile/images/header_bg.jpg"
			var="bgfoot" />
		<div data-role="footer" data-theme="b" data-position="fixed"
			style="text-align:center; background:url('${bgfoot}') no-repeat center;">
			<ul class="footermenu">
				<li><a href="<c:url value="/home" />" data-role="none"
					rel="external">Tienda</a>|</li>
				<li><a href="<c:url value="/"   />" data-role="none"
					rel="external">Productos</a>|</li>
				<li><a href="<c:url value="/contact"   />" data-role="none"
					rel="external">Contacto</a></li>
			</ul>
		</div>

	</div>
	<!--  -->





</body>
</html>
