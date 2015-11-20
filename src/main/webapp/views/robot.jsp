<%@ include file="/resources/layout/top.jsp"%>

<div class="row">
	<div class="col-md-8">
		<img
			src="<%=request.getContextPath()%>/resources/images/${robot.path_picture}"
			alt="image_robot" />
	</div>
	<div class="col-md-4">
		<table>
			<fieldset>
				<legend>Fiche du robot " ${robot.name}"</legend>
				<tr>
					<td><label>Date de cr�ation :</label></td>
					<td>${robot.creation_date}</td>
				</tr>
				<tr>
					<td><label>Point forts :</label></td>
					<td>${robot.strong_point}</td>
				</tr>
				<tr>
					<td><label>Technologies utilis�es :</label></td>
					<td></td>
				</tr>
				<tr>
					<td><label>Exp�rience des comp�titions :</label></td>
					<td></td>
				</tr>
				<tr>
					<td><label>Nombre de victoires :</label></td>
					<td></td>
				</tr>
				<tr>
					<td><label>Nombre de d�faites :</label></td>
					<td></td>
				</tr>
			</fieldset>
		</table>
	</div>
	<a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/robots">Modifier</a>
</div>

<%@ include file="/resources/layout/bot.jsp"%>