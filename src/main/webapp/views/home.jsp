<%@ include file="/resources/layout/top.jsp"%>

<!-- Heading Row -->
<div class="row">
	<div class="col-md-8">
		<div id="map"></div>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/map.js"></script>
		<script async defer
			src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJMQnUVO7WPqS96NqQUObz4RtxuQzADTY&callback=initMap&v=3"></script>
		<!-- <img class="img-responsive img-rounded" src="http://placehold.it/900x350" alt=""> -->
	</div>
	<!-- /.col-md-8 -->
	<c:forEach var="c" items="${competitions}" begin="0" end="0">
		<div class="col-md-4">
			<h2>
				<c:out value="${c.name}" />
			</h2>
			<p>${c.description}</p>
			<input type="hidden" class="gps" value="${c.geolocation}" />
			<input type="hidden" class="address" value="${c.address}" />
			<input type="hidden" class="name" value="${c.name}" /> 
			<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/competitions/card?id=${c.id}">Voir la fiche</a>
		</div>
	</c:forEach>

	<!-- /.col-md-4 -->
</div>
<!-- /.row -->

<hr>

<div class="row">
	<c:forEach var="c" items="${competitions}" begin="1" end="3">
		<div class="col-md-4">
			<h2><c:out value="${c.name}"/></h2>
			<p>${c.description}</p>
			<input type="hidden" class="gps" value="${c.geolocation}" />
			<input type="hidden" class="address" value="${c.address}" />
			<input type="hidden" class="name" value="${c.name}" /> 
			<a class="btn btn-default" href="<%=request.getContextPath()%>/competitions/card?id=${c.id}">Voir la fiche</a>
		</div>
	</c:forEach>
</div>
<!-- /.row -->

<%@ include file="/resources/layout/bot.jsp"%>
