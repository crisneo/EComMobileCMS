<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			/* if(!searchAndHighlight(text, 'body', 'highlighted', true)) {
            alert("No results found");
        	}*/
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
      <!-- <div class="lang_text">Languages:</div>
      <a href="http://www.free-css.com/" class="lang">
      <img src="<c:url value="/resources/endUser/images/en.gif" />" alt="" border="0" /></a> 
      <a href="http://www.free-css.com/" class="lang">
      <img src="<c:url value="/resources/endUser/images/de.gif"/>" alt="" border="0" /></a> -->
      
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
       <!--   <li><a href="http://www.free-css.com/" class="nav3">Specials</a></li>
        <li class="divider"></li>
        <li><a href="http://www.free-css.com/" class="nav4">My account</a></li>
        <li class="divider"></li>
        <li><a href="http://www.free-css.com/" class="nav4">Sign Up</a></li>
        <li class="divider"></li>
        <li><a href="http://www.free-css.com/" class="nav5">Shipping</a></li>
        <li class="divider"></li>-->
        <li><a href="<c:url value="/contact" />" class="nav6">Contacto</a></li>
        <li class="divider"></li>
         <li><a href="<c:url value="/searchProduct" />" class="nav3">Buscar Productos</a></li>
        <li class="divider"></li>
       <!--   <li class="currencies">Currencies
          <select>
            <option>US Dollar</option>
            <option>Euro</option>
          </select>
        </li>-->
      </ul>
      <div class="right_menu_corner"></div>
    </div>
    <!-- end of menu tab -->
   <div class="crumb_navigation">
				Navegacion: <span class="current">Busqueda</span>
	</div>
    <div class="left_content">
      <div class="title_box">Categorias</div>
      <ul class="left_menu">
        
        <c:forEach items="${categories}" var="prod">
			<c:choose>
   		 <c:when test="${category.code == prod.code}">
        		<li class="odd" />
    		</c:when>
    		<c:otherwise>
        		<li class="even" />
    		</c:otherwise>
			</c:choose>
			
			<c:url value="/productsByCategory/${prod.code}" var="link" />
					
			<a href="${link}">${prod.name}</a>
		<!--  </li>-->
		
	</c:forEach>
        
      </ul>
      <div class="title_box">Productos ofertados</div>
      <div class="border_box">
        <c:forEach items="${offeredProducts}" var="ofProd">
			 <div class="product_title"><a href="#">${ofProd.name}</a></div>
        <div class="product_img"><a href="#"><c:url value="/imageProcuct/${ofProd.code}" var="cc22" />
			<img src="${cc22}" style="width:100px; height:100px;" />	</a></div>
        <div class="prod_price">
        <!-- <span class="reduce">${ofProd.price}</span>--> 
        <span class="price">${ofProd.price}</span>
        
        </div>
			</c:forEach>
		
	
       
      
      </div>
      
    </div>
    <!-- end of left content -->
    <div class="center_content">
      <div class="center_title_bar">Buscar productos</div>
       <div class="prod_box_big">
      <input type="text" id="txtCriteria"></input>
      <input type="button" id="btnSearchProduct" value="Buscar"></input>
      </div>
       <div class="center_title_bar">Resultados</div>
       <div class="prod_box_big">
        <c:url value="/searchProductResult/" var="srch" />
      <c:url value="/Product/" var="linkProduct" />
      <c:url value="/imageProcuct/" var="imgProduct" />
        <div class="top_prod_box_big"></div>
        <div class="center_prod_box_big" id="divResults">
        <!-- <div style="width:100%; height:200px; background-color:red;" id="divResults">
      </div>-->
        </div>
        </div>

      <script type="text/javascript">
      
      function getElementHtml(product){
    	 
    	  return "<tr class='rowdark'><td>"+product.code+"</td><td>"+product.name+"</td><td>"+"<td>"+product.description+"</td>"+
    	  "<td><img src='${imgProduct}"+product.code+"' style='width:80px; height:80px;'/></td>"+
    	 	"<td><a href='"+"${linkProduct}"+product.code+"'>Ver</a></td></tr>";
      }
      $(document).ready(function(e){
    	  $("#btnSearchProduct").click(function(e){
    		  var text = $("#txtCriteria").val();
    		  if(text.length > 0){
    		  var crit="${srch}"+"name-"+text+"&code-"+text+"&description-"+text;

    		     	$.getJSON(crit, function(result) {
	    			    //alert(result);
	    			    var table = "<table class='custom' style='width:95%;'>";
	    			    for(i in result){
	    			    	table+=getElementHtml(result[i]);

	    			    }
	    			    table+="</table>";
    			    
    			   $("#divResults").html(table);
    			 });
    		  }
    	  });
      });
      </script>
    
    
    
    </div>
    <!-- end of center content -->
    <div class="right_content">
      <div class="shopping_cart">
        <div class="cart_title">Carrito de compras</div>
        <!-- if/else -->
        <c:choose>
		  <c:when test="${pageContext.request.userPrincipal.name != null}">
			  <div class="cart_details">
			  <%= session.getAttribute("cartItemsCount")!=null?session.getAttribute("cartItemsCount"):0 %>  productos <br />
	          <span class="border_cart"></span> Total: <span class="price">
	          <%= session.getAttribute("totalPrice")!=null?session.getAttribute("totalPrice"):0 %>Euros</span> 
	          </div>
		        <div class="cart_icon">
		        <a href="<c:url value="/loggedUser/viewCart"/>" title="header=[Checkout] body=[&nbsp;] fade=[on]">
		        <img src="<c:url value="/resources/endUser/images/shoppingcart.png" />" alt="" width="48" height="48" border="0" />
		        </a>
		       </div>
		      <!--  </div>-->
		     
		  </c:when>
		  
		  <c:otherwise>
		    <div class="cart_details">Usuario no identificado <br />
	          <span class="border_cart"></span>
	          <a href="<c:url value="/login"/>" class="loginJoin">Ingresar</a>
	          <span class="price"></span> 
	          </div>
		        <div class="cart_icon">
		        <!-- <a href="<c:url value="/loggedUser/viewCart"/>" title="header=[Checkout] body=[&nbsp;] fade=[on]">
		        <img src="<c:url value="/resources/endUser/images/shoppingcart.png" />" alt="" width="48" height="48" border="0" />
		        </a>-->
		        
		       </div>
		      <!--  </div>-->
		   
		  </c:otherwise>
		</c:choose>
		</div>
      <!--  <div class="title_box">What’s new</div>
      <div class="border_box">
        <div class="product_title">Motorola 156 MX-VL</div>
        <div class="product_img"><a href="http://www.free-css.com/"><img src="images/p2.gif" alt="" border="0" /></a></div>
        <div class="prod_price"><span class="reduce">350$</span> <span class="price">270$</span></div>
      </div>-->
      <!--  <div class="title_box">Manufacturers</div>
      <ul class="left_menu">
        <li class="odd"><a href="http://www.free-css.com/">Sony</a></li>
        <li class="even"><a href="http://www.free-css.com/">Samsung</a></li>
        <li class="odd"><a href="http://www.free-css.com/">Daewoo</a></li>
        <li class="even"><a href="http://www.free-css.com/">LG</a></li>
        <li class="odd"><a href="http://www.free-css.com/">Fujitsu Siemens</a></li>
        <li class="even"><a href="http://www.free-css.com/">Motorola</a></li>
        <li class="odd"><a href="http://www.free-css.com/">Phillips</a></li>
        <li class="even"><a href="http://www.free-css.com/">Beko</a></li>
      </ul>-->
      <!-- <div class="banner_adds"> <a href="http://www.free-css.com/"><img src="images/bann1.jpg" alt="" border="0" /></a> 
      </div>-->
    </div>
    <!-- end of right content -->
  </div>
  <!-- end of main content -->
  <div class="footer">
    <div class="left_footer">
     <img src="<c:url value="/resources/endUser/images/footer_logo.png"/>" alt="" width="170" height="49"/> </div>
    <div class="center_footer"> Usal copyright 2014<br />
      <a href="http://csscreme.com">
      <!-- <img src="images/csscreme.jpg" alt="csscreme" border="0" />-->
      </a>
      <br />
      <!-- <img src="<c:url value="/resources/endUser/images/payment.gif" />" alt="" /> </div>-->
    <div class="right_footer"> 
    <a href="<c:url value="/home" />">Home</a> 
    <a href="<c:url value="/viewproducts" />">Productos</a> 
    <a href="<c:url value="/contact" />">Contacto</a> 
  </div>
</div>
<!-- end of main_container -->
</div></div>

<!-- end of main_container -->
</body>
</html>
