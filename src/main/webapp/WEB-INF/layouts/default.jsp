<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<title><tiles:insertAttribute name="title" /></title>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!-- Bootstrap -->
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextRoot}/css/main.css" rel="stylesheet">

<!-- This is only for javascript tagging on the profile -->
<link href="${contextRoot}/css/jquery.tagit.css" rel="stylesheet">

<script
	src="${contextRoot}/js/jquery-ui.min.js"></script>

<script
	src="${contextRoot}/js/tag-it.min.js"></script>
<!-- doesnt work for the moment
<script src="${contextRoot}/js/connectionmanager.js"></script>	
<script src="/webjars/sockjs-client/sockjs.min.js"></script>
<script src="/webjars/stomp-websocket/stomp.min.js"></script>

<tiles:insertAttribute name="chatnotifications"></tiles:insertAttribute>
<tiles:insertAttribute name="chatviewscript" ignore="true"></tiles:insertAttribute>
-->

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<!-- Static navbar -->
	<nav class="navbar navbar-expand-lg navbar-light"> <img
		src="/img/Download.png" alt="logo" style="height: 30px; width: auto" />
	<a class="navbar-brand" href="/">GoatBook </a>

	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link"
				href="${contextRoot}/" style="color: #FDB827">Home <span
					class="sr-only">(current)</span></a></li>
			<sec:authorize access="isAuthenticated()">
			<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false" style="color: #FDB827"> Status </a>
					<div class="dropdown-menu" style="background: #552084"
						aria-labelledby="navbarDropdown">
						<a class="dropdown-item" style="color: #FDB827"
							href="${contextRoot}/addstatus">Add Status</a> <a
							class="dropdown-item" style="color: #FDB827"
							href="${contextRoot}/viewstatus">View Status Updates</a>
					</div></li>		
			
			</sec:authorize>



		</ul>

		<ul class="nav navbar-nav navbar-right">

			<sec:authorize access="!isAuthenticated()">
				<li><a href="${contextRoot}/login" style="color: #FDB827;margin-right:5px">Login</a></li>
				
				<li><a href="${contextRoot}/register" style="color: #FDB827">Register</a></li>
			</sec:authorize>
			
					
			<sec:authorize access="isAuthenticated()">
				
				<li><a href="${contextRoot}/profile"style="color: #FDB827;margin:10px">Profile</a></li>
			
			
				<li><a href="javascript:$('#logoutForm').submit();"
					style="color: #FDB827">Logout</a></li>
			</sec:authorize>
		</ul>

	</div>
	</nav>

	<c:url var="logoutLink" value="/logout" />
	<form id="logoutForm" method="post" action="${logoutLink}">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

	<div class="container" style="margin-top: 20px">
		<tiles:insertAttribute name="content" />
	</div>


	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${contextRoot}/js/bootstrap.min.js"></script>
	
	<!-- chat doesnt work for the moment
	<sec:authorize access="isAuthenticated()">
		<script>
			connectionManager.connect();
		</script>
	</sec:authorize>
	 -->
	

</body>
</html>