<%@ include file="/resources/layout/top.jsp"%>

<div class="row">
	<div class="col-md-4">
		<img
		id="picture_robot"
			src="<%=request.getContextPath()%>/resources/images/robots/${name_file}"
			alt="image_robot" />
	</div>
	<div class="col-md-8">
		<table>
			<fieldset>
				<legend>Fiche du robot " ${robot.name}"</legend>
				<tr>
					<td><label>Date de création :</label></td>
					<td>${robot.creation_date}</td>
				</tr>
				<tr>
					<td><label>Point forts :</label></td>
					<td>${robot.strong_point}</td>
				</tr>
				<tr>
					<td><label>Technologies utilisées :</label></td>
					<td>${technologies}</td>
				</tr>
				<tr>
					<td><label>Expérience des compétitions :</label></td>
					<td></td>
				</tr>
				<tr>
					<td><label>Nombre de victoires :</label></td>
					<td></td>
				</tr>
				<tr>
					<td><label>Nombre de défaites :</label></td>
					<td></td>
				</tr>
			</fieldset>
		</table>
		<a class="btn btn-default" href="<%=request.getContextPath()%>/robots">Modifier</a>
		<a class="btn btn-danger" href="<%=request.getContextPath()%>/robots/remove?id=${robot.id}">Supprimer</a>		
	</div>	
</div>

<%@ include file="/resources/layout/bot.jsp"%>