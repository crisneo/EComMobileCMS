<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${info.name}</title>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/endUser/style.css"/>" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="iecss.css" />
<![endif]-->
<script type="text/javascript" src="<c:url value="/resources/endUser/js/boxOver.js"/>"></script>
<script  type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/searchPage.js"/>" />
</head>
<body>
		
<script type="text/javascript">

$(document).ready(function(){
		$("#btnSearch").click(function(e){
			var text = $("#txtSearch").val();
			//findString(text);
			//alert("se busco");
			//FindNext(text);
			var myHilitor = new Hilitor(); 
			myHilitor.apply(text);
			//$("#txtSearch").blur();
			//FindNext(text);
			
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
   <input type="image" src="<c:url value="/resources/endUser/images/search.gif" />" class="search_bt" id="btnSearch"/>
      <!--  <img src="<c:url value="/resources/endUser/images/search.gif" />" class="search_bt" id="btnSearch" ></img>-->
      
    </div>
    
    <div class="languages">
     
      
      <!-- user status -->
      <div class="lang_text">
		    
		 	<c:url value="/j_spring_security_logout" var="logoutUrl" />

			<form action="${logoutUrl}" method="post" id="logoutForm">
			  <input type="hidden" 
				name="${_csrf.parameterName}"
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
    <div id="logo"> <a href="http://www.free-css.com/"><img src="<c:url value="/resources/endUser/images/logo.png" />" alt="" border="0" width="237" height="140" /></a> </div>
    <div class="oferte_content">
      <div class="top_divider"><img src="<c:url value="/resources/endUser/images/header_divider.png" />" alt="" width="1" height="164" /></div>
      <div class="oferta">
        <div class="oferta_content"> 
         <c:url value="/imageProcuct/${starProduct.code}" var="starImage" />
        <img src="${starImage}" width="94" height="92" alt="" border="0" class="oferta_img" />
          <div class="oferta_details">
            <div class="oferta_title">${starProduct.name}</div>
            <div class="oferta_text"> ${starProduct.description}</div>
            <c:url value="/Product/${starProduct.code}" var="starLink" />
            <a href="${starLink}" class="details">Detalles</a> </div>
        </div>
        <div class="oferta_pagination">
        <!--  <span class="current">1</span> 
	         <a href="http://www.free-css.com/">2</a> 
	         <a href="http://www.free-css.com/">3</a> 
	         <a href="http://www.free-css.com/">4</a> 
	         <a href="http://www.free-css.com/">5</a>-->
          </div>
      </div>
      <div class="top_divider"><img src="<c:url value="/resources/endUser/images/header_divider.png" />" alt="" width="1" height="164" /></div>
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
        
         <li><a href="<c:url value="/searchProduct" />" class="nav3">Buscar Productos</a></li>
        <li class="divider"></li>
     
      </ul>
      <div class="right_menu_corner"></div>
    </div>
    <!-- end of menu tab -->
    <div class="crumb_navigation"> Navegacion: <span class="current">Home</span> </div>
    <div class="left_content">
     
      <div class="title_box" style="visibility:hidden;">Categorías</div>
      <ul class="left_menu">
       
      
        
      </ul>
    
      </div>
    </div>
    <!-- end of left content -->
    <div class="center_content">
      <!--  <div class="center_title_bar">Productos</div>-->
      <div class="center_title_bar"></div>
       <div class="prod_box_big">
      <h1>${info.name}</h1>
      <br />
      
      
      <hr style="width:100%;"></hr>
      ${info.info}
      
     
    </div>
    <!-- end of center content -->
    <div class="right_content">
     
      <!--  <div class="title_box">What’s new</div>
      <div class="border_box">
        <div class="product_title"><a href="details.html">Motorola 156 MX-VL</a></div>
        <div class="product_img"><a href="details.html"><img src="images/p2.gif" alt="" border="0" /></a></div>
        <div class="prod_price"><span class="reduce">350$</span> <span class="price">270$</span></div>
      </div>-->
     
    <!-- end of right content -->
  </div>
  </div>
  <!-- end of main content -->
  <div class="footer">
    <div class="left_footer"> <img src="<c:url value="/resources/endUser/images/footer_logo.png"/> " alt="" width="170" height="49"/> </div>
    <div class="center_footer"> Usal. Copyright 2014<br />
     
      <!-- <img src="<c:url value="/resources/endUser/images/payment.gif" />" alt="" /> </div>-->
    <div class="right_footer"> 
    
    <a href="<c:url value="/home" />">Home</a> 
    <a href="<c:url value="/viewproducts" />">Productos</a> 
    <a href="<c:url value="/contact" />">Contacto</a> 
    
  </div>
</div>
<!-- end of main_container -->
</div>
</div>
</body>
</html>

