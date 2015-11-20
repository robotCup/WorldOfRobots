<%@ include file="/resources/layout/top.jsp"%>
<!-- Import js de la page -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/robots.js"></script>

<!-- Slider -->
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/resources/lib/slider/slick/slick.css" />
	<link rel="stylesheet" type="text/css"
		href="<%=request.getContextPath()%>/resources/lib/slider/slick/slick-theme.css" />
	
<!-- DataTables -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />
	

<!-- Heading Row -->
<div class="row">
	<div class="back_carousel">
		<div class="slider">
		<c:forEach var="a" items="${robots}">
			<div>
				<img
					src="<%=request.getContextPath()%>/resources/images/${a.path_picture}"
					alt="img01" class="images_robots" />
			</div>
				
				
			</c:forEach>
			
			
		</div>
	</div>
</div>
<!-- /.row -->

<hr>

<!-- Tableau de robots -->
<div class="row">
	<table id="tab_robots" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Date création</th>
				<th>Points forts</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="a" items="${robots}">
			<tr>
				<td>${a.name}</td>
				<td>${a.creation_date}</td>
				<td>${a.strong_point}</td>
				<td><a href="<%=request.getContextPath()%>/robots/card?id=${a.id}"> Voir la fiche</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- /.row -->

<!-- Slider -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lib/slider/slick/slick.min.js"></script>

<%@ include file="/resources/layout/bot.jsp"%>
