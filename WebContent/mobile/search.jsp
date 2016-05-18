<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
  
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <title>${info.name}</title>
  
  <link rel="stylesheet" href="<c:url value="/resources/mobile/jquery.mobile-1.3.1.min.css"/>" />
<script src="<c:url value="/resources/mobile/jquery-1.9.1.min.js"/>"></script>
<script src="<c:url value="/resources/mobile/jquery.mobile-1.3.1.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/resources/endUser/mobile/css/style.css"/>" />


   
</head>
<body>
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
		 	
<!-- tested -->


<div data-role="page" id="pageAbout">
	<div data-theme="c" data-role="header" class="generalContent">
	 <div style="width: 100%; background:url('<c:url value="/resources/endUser/images/header_bg.jpg" />') no-repeat center;">
            <img src="<c:url value="/resources/endUser/images/logo.png" />" alt="image">
        </div>
       <h3 style="text-align:right; margin-right:10px;font-family: Helvetica,Arial,sans-serif;" >
        <!-- user status -->

			 <c:choose>
			  <c:when test="${pageContext.request.userPrincipal.name != null}">
			  	Bienvenido : ${pageContext.request.userPrincipal.name} | <a
						href="javascript:formSubmit()" rel="external"> Salir</a>
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
			<li><a href="<c:url value="/home"  />" data-theme="c" rel="external"  >Tienda</a></li>
			<li><a href="<c:url value="/"  />" data-theme="c" rel="external" class="ui-btn-active ui-state-persist" >Productos</a></li>
			<li><a href="<c:url value="/contact"  />" data-theme="c" rel="external">Contacto</a></li>
			<li><a href="<c:url value="/loggedUser/viewCart"  />" data-theme="c" rel="external">Carrito</a></li>
			<li><a href="<c:url value="/searchProduct"  />" data-theme="c" rel="external" >Buscar</a></li>
		</ul>
	</div>
	
	<div data-role="content" class="generalContent" >
	
	<!-- div carrito de compras -->
	<div style="width:100%; height:30px; text-align:right; padding-right:5px; ">
	<c:choose>
		  <c:when test="${pageContext.request.userPrincipal.name != null}">
			 <%= session.getAttribute("cartItemsCount")!=null?session.getAttribute("cartItemsCount"):0 %>  productos 
	          Total: <%= session.getAttribute("totalPrice")!=null?session.getAttribute("totalPrice"):0 %>Euros 
	         
		        <a href="<c:url value="/loggedUser/viewCart"/>" rel="external" title="header=[Checkout] body=[&nbsp;] fade=[on]">
		        <img src="<c:url value="/resources/endUser/images/shoppingcart.png" />" alt="" width="24" height="24" border="0" />
		        </a>
		     
		  </c:when>
		  
		  <c:otherwise>
		    Usuario no identificado 
	          
	          <!-- <a href="<c:url value="/login"/>" class="loginJoin">Ingresar</a>-->
	         
		       
		  </c:otherwise>
		</c:choose>
	</div>
	<!-- end div carrito de compras -->
	<c:url value="/searchProductResult/" var="srch" />
	<c:url value="/Product/" var="prodLinkV" />
	<c:url value="/imageProcuct/" var="imgProdV" />
	<!-- div search form -->
	<div style="width:100%; height:60px; text-align:right; padding-right:5px; ">
	<table style="width:100%;">
	<tr><td>
	<input type="text" id="txtCriteria"></input>
	</td>
	<td> <input type="button" id="btnSearchProduct" value="Buscar"></input>
	</td></tr>
	</table>
	 
     
	</div>
	
	<script type="text/javascript">
	 function getElementHtml(product){
   		 return "<li data-theme='d' data-icon='info'>"+
		"<a href='"+"${prodLinkV}"+product.code+"' rel='external'>"+
			"<img src='${imgProdV}"+product.code+"'  />"+
    		"<h2>"+product.name+"</h2>"+
    		"<p>"+product.price+"</p></a>"+

   		 "</li>";
   	 } 
	  
	$(document).ready(function(e){
	   	
		 $("#btnSearchProduct").click(function(e){
	   		  var text = $("#txtCriteria").val();
	   		  if(text.length > 0){
	   			  
	   		  
	   		  var crit="${srch}"+"name-"+text+"&code-"+text+"&description-"+text;
	
	   		     	$.getJSON(crit, function(result) {
		    			    
		    			    var table = "";
		    			    for(i in result){
		    			    	table+=getElementHtml(result[i]);
	
		    			    }
		    			    
	   			    
	   			   $("#myList").html(table);
	   			   $("#myList").listview("refresh");
	   			 });
	   		  }
	   	  });
		 
		 //disable collapse panel
		 $("#myList h3").on("tap", function (event, ui) {
			    return false;
			});
     });
	</script>
	
	<div data-role="collapsible-set" data-theme="d" data-content-theme="d" class="generalContent" >
	           
			 <div data-role="collapsible"  data-collapsed="false">
	                <h3>
	                    Resultados
	                </h3>
	          <ul data-role="listview" id="myList" data-divider-theme="d" data-inset="true"  style="width:100%;">
	          </ul>
	          
	        </div>
	        </div>
			
		</div>
		<c:url value="/resources/endUser/mobile/images/header_bg.jpg" var="bgfoot" />
		<div data-role="footer" data-theme="b" data-position="fixed" style="text-align:center; background:url('${bgfoot}') no-repeat center;">
		<ul class="footermenu">
		<li ><a href="<c:url value="/home" />"  data-role="none" rel="external" >Tienda</a>|</li>
		<li><a href="<c:url value="/"   />" data-role="none" rel="external">Productos</a>|</li>
		<li><a href="<c:url value="/contact"   />" data-role="none" rel="external">Contacto</a></li>
		</ul>
		</div>
	
</div>


</body>
</html>
