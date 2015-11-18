<%@ include file="/resources/layout/top.jsp"%>
<!-- Import js de la page -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/competitions.js"></script>	

<!-- DataTables -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/lib/DataTables-1.10.10/media/css/jquery.dataTables.min.css" />

<!-- Tableau de robots -->
<div class="row">
	<table id="tab_competitions" class="display" cellspacing="0"
		width="100%">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Lieu</th>
				<th>Début</th>
				<th>Fin</th>
				<th>Nombre maximum de robots</th>
				<th>Action</th>

			</tr>
		</thead>
		<tbody>
	<c:forEach var="a" items="${competitions}">
			<tr>
				<td>${a.name}</td>
				<td>${a.address}</td>
				<td>${a.start_date}</td>
				<td>${a.end_date}</td>
				<td>${a.robot_max}</td>
				<td><a href="<%=request.getContextPath()%>/competitions/card?id=${a.id}"> Voir la fiche</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- /.row -->
<%@ include file="/resources/layout/bot.jsp"%>
