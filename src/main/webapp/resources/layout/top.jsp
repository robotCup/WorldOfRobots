<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>World of Robots</title>

    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/theme/css/bootstrap.min.css"/>

    <!-- Custom CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/theme/css/small-business.css"/>

	<!-- General CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/general.css"/>
</head>

<body>
  <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<%=request.getContextPath()%>">
                    <img src="http://placehold.it/150x50&text=Logo" alt="logo">
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="<%=request.getContextPath()%>/competitions">Compétitions</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/robots">Robots</a>
                    </li>
                    <li>
                        <a href="#">Mon espace</a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/user/connexion">
                        	<img id="connexion" alt="connexion" src="<%=request.getContextPath()%>/resources/images/icon_homme.png"/>
                        </a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <!-- Page Content -->
    <div class="container">

        