<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="spring.model.User" %>
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
	
	<!-- jQuery -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/theme/js/jquery.js"></script>
	
	<!-- Menu CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/resources/theme/menu/styles.css" />
	
	<!-- Menu JS -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/theme/menu/script.js" /></script>

	<!-- General CSS -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/resources/css/general.css" />
	
</head>

<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div id='cssmenu'>
		<ul>
			<li id="actionLogo">
				<a href='<%=request.getContextPath()%>'>			
					<img src="<%=request.getContextPath()%>/resources/images/logos.png" alt="" id="image">
				</a>
			</li>
			
			<% if(session.getAttribute("user") != null) { %>
				<li class='has-sub service' ><a href="<%=request.getContextPath()%>/competitions"><span>Compétitons</span></a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/competitions/add"><span>Ajouter une compétition</span></a></li>	
						<li><a href="<%=request.getContextPath()%>"><span>Mes compétitions</span></a></li>						
					</ul>
				<% } else { %>	
					<li class='service'><a href="<%=request.getContextPath()%>/competitions"><span>Compétitons</span></a>	
				<% } %>			
			</li>
			<%  if(session.getAttribute("user") != null) { %>
				<li class='has-sub service'><a href="<%=request.getContextPath()%>/robots"><span>Robots</span></a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/robots/add"><span>Ajouter un robot</span></a></li>	
						<%  if( ((User)session.getAttribute("user")).isLeader() != false) { %>
							<li><a href="<%=request.getContextPath()%>"><span>Modifier mon robot</span></a></li>
						<% } %>					
					</ul>
				<% } else { %>	
					<li class='service'><a href="<%=request.getContextPath()%>/robots"><span>Robots</span></a>
				<% } %>				
			</li>
			<%  if(session.getAttribute("user") != null) { %>
				<li class="service"> <a href="<%=request.getContextPath()%>/user/mySpace"><span>Mon espace</span></a></li>			
				<li class="service"> <a href="<%=request.getContextPath()%>/user/disconnect"><span>Déconnexion</span></a></li>
			<% } 
			else{ %>
				<li class="service"> <a href="<%=request.getContextPath()%>/user/disconnect"><span>Connexion</span></a></li>
			<% } %>
			<li class='last service'><a href="#"><span>Contact</span></a></li>
		</ul>
	</div>
	</nav>
	
	<!-- Page Content -->
	<div class="container">