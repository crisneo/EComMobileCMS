<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset="UTF-8" /> 
    <title>
       Login
    </title>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
     <link rel="stylesheet" type="text/css" href="<c:url value="/resources/admincss/loginStyle.css" />" media="all" />
     <script src="<c:url value="/resources/mobile/jquery.mobile-1.3.1.min.js" />"></script>
</head>
<c:url value="/resources/admincss/images/login/bg.jpg" var="bg" />



<body>
<!--<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
<form name="loginForm" action="<c:url value='j_spring_security_check' />" method="post">
  <h1>Log In</h1>
  <div class="inset">
  <p>
    <label for="email">USUARIO</label>
    <input type='text' name='username' value=''>
  </p>
  <p>
    <label for="password">PASSWORD</label>
    <input type='password' name='password' />
  </p>
 
  </div>
  <p class="p-container">
   
    <input type="submit" name="submit" id="submit" value="Log in">
  </p>
   <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
</form>-->



<!-- new loginn -->
<!-- contact-form -->	
<div class="message warning">
<div class="contact-form">
	<div class="logo">
		<h1>Ingresar</h1>
	</div>	
<!--- form --->
<!-- <form class="form" action="#" method="post" name="contact_form">-->
<form name="loginForm" class="form" action="<c:url value='j_spring_security_check' />" method="post">
	<ul>
		<li>
			 <label><img src="<c:url value="/resources/admincss/images/login/contact.png"/>" alt=""></label>
			 <input type="text" name="username" class="email" placeholder="usuario" required />		            
		 </li>
		 <li>
			 <label><img src="<c:url value="/resources/admincss/images/login/lock.png" />" alt=""></label>
			 <input type="Password" name="password" placeholder="Password" required />		         
		 </li>
		 <p>
		 <c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
		</p>
		 <li class="style">
		    <!--  <input type="Submit" value="Submit"> -->
		     <input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		      <input type="submit" name="submit" id="submit" value="Log in">
		 </li>
	</ul>	
	<div class="clear"></div>	   	
</form>
</div>
<div class="alert-close"></div>
</div>
<div class="clear"></div>
<!--- footer --->
<div class="footer">
	<p></p>
</div>
<!-- new -->
</body>
</html>



