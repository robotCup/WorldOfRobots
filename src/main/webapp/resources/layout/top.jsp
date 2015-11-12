<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="fr">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<title>World of Robots</title>
	
	<!-- Bootstrap Core CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/resources/theme/css/bootstrap.min.css" />
	
	<!-- Custom CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/resources/theme/css/small-business.css" />
	
	<!-- General CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/resources/css/general.css" />
	
	<!-- jQuery -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/theme/js/jquery.js"></script>
	
	<!-- Menu CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/resources/theme/menu/styles.css" />
	
	<!-- Menu JS -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/theme/menu/script.js" /></script>

</head>

<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div id='cssmenu'>
		<ul>
			<li id="actionLogo">
				<a href='#'>			
					<img src="http://placehold.it/150x50&amp;text=Logo" alt="">
				</a>
			</li>
			<li class='has-sub' id="service"><a href="<%=request.getContextPath()%>/competitions"><span>Compétitons</span></a>
				<ul>
					<li><a href="<%=request.getContextPath()%>/competitions/add"><span>Ajouter une compétition</span></a></li>					
				</ul>
			</li>
			<li class='has-sub' id="service"><a href="<%=request.getContextPath()%>/robots"><span>Robots</span></a>
				<ul>
					<li><a href='<%=request.getContextPath()%>/robots/add'><span>Ajouter un robot</span></a></li>					
				</ul>
			</li>
			<li id="service"> <a href="<%=request.getContextPath()%>/user/mySpace"><span>Mon espace</span></a></li>
			<li class='last' id="service"><a href='#'><span>Contact</span></a></li>
		</ul>
	</div>
	</nav>
	
	<!-- Page Content -->
	<div class="container">