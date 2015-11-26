<%@ include file="/resources/layout/top.jsp"%>
<!-- Import js de la page -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/news.js"></script>

<!-- DataTables -->
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/colreorder/1.3.0/js/dataTables.colReorder.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css" />
<link
	href="<%=request.getContextPath()%>/resources/lib/confirm/jquery-confirm.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/confirm/jquery-confirm.min.js"></script>
<!-- Tableau de robots -->
<div class="row">
	<h1>Mes messages</h1>
	<br> <br>
	<table id="tab_news" class="display" cellspacing="0" width="100%">
		<thead>
			<tr>
				<th>Date</th>
				<th>Message</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="m" items="${messages}">
				<tr>
					<td>${french_dates[m.id]}</td>
					<td>${m.message}</td>
					<td>
						<button class="btn btn-danger delete_msg">Supprimer</button>
						<input type="hidden" class="url_delete_msg"
						value="<%=request.getContextPath()%>/user/deleteMsg?id=${m.id}" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<!-- /.row -->
<%@ include file="/resources/layout/bot.jsp"%>
