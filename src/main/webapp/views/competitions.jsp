
<%@ include file="/resources/layout/top.jsp"%>

<!-- Heading Row -->
<div class="row">
	<div class="col-md-8">
		<div id="map"></div>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/map.js"></script>
		<script async defer
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJMQnUVO7WPqS96NqQUObz4RtxuQzADTY&callback=initMap"></script>
		<!-- <img class="img-responsive img-rounded" src="http://placehold.it/900x350" alt=""> -->
	</div>
	<!-- /.col-md-8 -->
	<div class="col-md-4">
		<h1>Titre de la derni�re comp�tition</h1>
		<p>Description du robot avec ses caract�ristiques</p>
		<a class="btn btn-primary btn-lg" href="#">Voir la fiche</a>
	</div>
	<!-- /.col-md-4 -->
</div>
<!-- /.row -->

<hr>

<div class="row">
	<c:forEach var="a" items="${competitions}">
		<div class="col-md-4">
			<h2>${a.name}</h2>
			<p>${a.description}</p>
			<a class="btn btn-default" href="#">Voir la fiche</a>
		</div>
	</c:forEach>	
</div>
<!-- /.row -->

<%@ include file="/resources/layout/bot.jsp"%>
