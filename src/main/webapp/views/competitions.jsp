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
<%@ include file="/resources/layout/bot.jsp"%>
