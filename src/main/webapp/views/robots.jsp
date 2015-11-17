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
	src="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/css/jquery.dataTables.min.css" />
	

<!-- Heading Row -->
<div class="row">
	<div class="back_carousel">
		<div class="slider">
			<div>
				<img
					src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
					alt="img01" />
			</div>
			<div>
				<img
					src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
					alt="img01" />
			</div>
			<div>
				<img
					src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
					alt="img01" />
			</div>
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

<!-- Slider -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lib/slider/slick/slick.min.js"></script>

<%@ include file="/resources/layout/bot.jsp"%>
