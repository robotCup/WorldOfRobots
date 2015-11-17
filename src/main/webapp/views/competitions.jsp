<%@ include file="/resources/layout/top.jsp"%>

<!-- DataTables -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/css/jquery.dataTables.min.css" />
<!-- Slider -->
	<link rel="stylesheet" type="text/css"
		href="//cdn.jsdelivr.net/jquery.slick/1.5.8/slick.css" />
	<link rel="stylesheet" type="text/css"
		href="//cdn.jsdelivr.net/jquery.slick/1.5.8/slick-theme.css" />


<!-- Heading Row -->
<div class="row">
	<div class="col-md-8">
		<div class="slider">
			<div>
				<a href="#"><img
					src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
					alt="img01" /></a>
			</div>
			<div>
				<a href="#"><img
					src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
					alt="img01" /></a>
			</div>
			<div>
				<a href="#"><img
					src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
					alt="img01" /></a>
			</div>
		</div>
		<!-- <div id="cbp-fwslider" class="cbp-fwslider">
			<ul>
				<li><a href="#"><img
						src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
						alt="img01" /></a></li>
				<li><a href="#"><img
						src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
						alt="img02" /></a></li>
				<li><a href="#"><img
						src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
						alt="img03" /></a></li>
				<li><a href="#"><img
						src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
						alt="img04" /></a></li>
				<li><a href="#"><img
						src="<%=request.getContextPath()%>/resources/images/slider_robots.png"
						alt="img05" /></a></li>
			</ul>
		</div> -->
	</div>
</div>
<!-- /.row -->

<hr>

<!-- Tableau de robots -->
<div class="row">
	<table id="tab_competitions" class="display" cellspacing="0"
		width="100%">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Robots</th>
				<th>Lieu</th>
				<th>Début</th>
				<th>Fin</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Compet 1</td>
				<td>Robot</td>
				<td>123445 rue Linné Paris</td>
				<td>01/01/2010</td>
				<td>01/01/2010</td>
				<td><a href="#">Voir la fiche</a></td>
			</tr>
		</tbody>
	</table>
</div>
<!-- /.row -->

<!-- Slider -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/lib/slider/slick/slick.min.js"></script>

<!-- Import js de la page -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/competitions.js"></script>
	
<%@ include file="/resources/layout/bot.jsp"%>
