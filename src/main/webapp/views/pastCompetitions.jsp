<%@ include file="/resources/layout/top.jsp"%>
<!-- Import js de la page -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/competitions.js"></script>	

<!-- DataTables -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/colreorder/1.3.0/js/dataTables.colReorder.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />

<!-- Tableau de robots -->
<div class="row">
	<h1>${title}</h1>
	<br><br>
	<table id="past_competitions" class="display" cellspacing="0"
		width="100%">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Organisateur</th>
				<th>Lieu</th>
				<th>Début</th>
				<th>Fin</th>
				<th>Nombre de participants</th>
				<th>Vainqueur</th>
			</tr>
		</thead>
		<tbody>
	<c:forEach var="c" items="${competitions}">
			<tr>
				<td>${c.name}</td>
				<td>${users_names[c.id]}</td>
				<td>${c.address}</td>
				<td>${dates_start[c.id]}</td>
				<td>${dates_end[c.id]}</td>
				<td>${fighters[c.id]}</td>
				<td> </td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- /.row -->
<%@ include file="/resources/layout/bot.jsp"%>
