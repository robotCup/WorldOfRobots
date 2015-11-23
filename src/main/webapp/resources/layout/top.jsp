<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%@ page import="spring.model.User"%>
<!DOCTYPE html>
<html lang="fr">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<title>World of Robots</title>

<!-- Bootstrap Core CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/theme/css/bootstrap.min.css" />


<!-- Custom CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/theme/css/small-business.css" />

<!-- jQuery -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript"
	src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<!-- Menu CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/theme/menu/styles.css" />

<!-- Menu JS -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/theme/menu/script.js" /></script>

<!-- General CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/general.css" />

<!-- Validate -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/validate/dist/jquery.validate.js"></script>

</head>

<body>
	<!-- Navigation -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div id='cssmenu'>
			<ul>
				<li id="actionLogo"><a href='<%=request.getContextPath()%>'>
						<img
						src="<%=request.getContextPath()%>/resources/images/logos.png"
						alt="" id="image">
				</a></li>

				<li class='has-sub service'><a
					href="<%=request.getContextPath()%>/competitions"><span>Compétitons</span></a>
					<ul>
						<li><a href="<%=request.getContextPath()%>/competitions/past"><span>Compétitons
									terminées</span></a></li>
						<% if (session.getAttribute("user") != null) { %>
						<li><a href="<%=request.getContextPath()%>/competitions/add"><span>Ajouter
									une compétition</span></a></li>
						<li><a
							href="<%=request.getContextPath()%>/competitions/myCompetitions"><span>Mes
									compétitions</span></a></li>
						<% } %>
					</ul></li>
				<% if (session.getAttribute("user") != null) { %>
				<li class='has-sub service'><a
					href="<%=request.getContextPath()%>/robots"><span>Robots</span></a>
					<ul>

						<% if (((User) session.getAttribute("user")).isLeader() != false) { %>
						<li><a
							href="<%=request.getContextPath()%>/robots/card?id=<%=((User)session.getAttribute("user")).getId_robot() %>">
								<span>Mon robot</span>
						</a></li>
						<% } else if (((User) session.getAttribute("user")).getId_robot() == 0) {  %>
						<li><a href="<%=request.getContextPath()%>/robots/add"><span>Ajouter
									un robot</span></a></li>
						<% } %>
					</ul> <% } else { %>
				<li class='service'><a
					href="<%=request.getContextPath()%>/robots"><span>Robots</span></a>
					<%
						}
					%></li>
				<%
					if (session.getAttribute("user") != null) {
				%>
				<li class="service"><a
					href="<%=request.getContextPath()%>/user/mySpace"><span>Mon
							espace</span></a></li>
				<li class="service"><a
					href="<%=request.getContextPath()%>/user/disconnect"><span>Déconnexion</span></a></li>
				<li class="service last"><a><span>Bienvenue <%= ((User)session.getAttribute("user")).getLogin() %> !</span></a></li>
				<%
					} else {
				%>
				<li class="service"><a
					href="<%=request.getContextPath()%>/user/toConnect"><span>Connexion</span></a></li>
				<%
					}
				%>
			</ul>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">
		<% if (request.getAttribute("result") != null && (Boolean)request.getAttribute("result") == false ){ %>
		<div class="alert alert-danger">
			<strong>Echec!</strong><br>${message}
		</div>
		<% } 
		if (request.getAttribute("result") != null && (Boolean)request.getAttribute("result") == true ){ %>
		<div class="alert alert-success">
			<strong>Succès!</strong><br>${message}
		</div>
		<% } %>