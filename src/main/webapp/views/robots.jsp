<%@ include file="/resources/layout/top.jsp" %>

<!-- Import lib dataTables -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/css/jquery.dataTables.min.css"/>

<!-- Import js de la page -->    
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/robots.js"></script>   
    <!-- Heading Row -->
        <div class="row">
            <div class="col-md-8">
                <img class="img-responsive img-rounded" src="<%=request.getContextPath()%>/resources/images/slider_robots.png" alt="">
            </div>
            <!-- /.col-md-8 -->
            <!--<div class="col-md-4">
                <h1>Titre du dernier robot</h1>
                <p>Description du robot avec ses caractéristiques</p>
                <a class="btn btn-primary btn-lg" href="#">Voir la fiche</a>
            </div>-->
            <!-- /.col-md-4 -->
        </div>
        <!-- /.row -->

        <hr>

        <!-- Tableau de robots -->
        <div class="row">
            <table id="tab_robots" class="display" cellspacing="0" width="100%">
            	 <thead>
		            <tr>
		                <th>Nom</th>
		                <th>Age</th>
		                <th>Expérience</th>
		                <th>Action</th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr>
		                <td>Robot 1</td>
		                <td>3 ans</td>
		                <td>Compet 1, Compet 2</td>
		                <td><a href="#">Voir la fiche</a></td>
		            </tr>
	            </tbody>
            </table>
        </div>
        <!-- /.row -->

<%@ include file="/resources/layout/bot.jsp" %>
   