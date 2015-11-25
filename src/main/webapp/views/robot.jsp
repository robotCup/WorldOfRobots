<%@ include file="/resources/layout/top.jsp"%>

<div class="row">
	<div class="col-md-4">
		<img id="picture_robot"
			src="<%=request.getContextPath()%>/resources/images/robots/${name_file}"
			alt="image_robot" />
	</div>
	<div class="col-md-8">
		<table>
			<fieldset>
				<legend>Fiche du robot " ${robot.name}"</legend>
				<tr>
					<td><label>Date de création :</label></td>
					<td>${french_dates[robot.id]}</td>
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
					<td><label>Nombre de competitions :</label></td>
					<td>${participate_competition}</td>
				</tr>
				<tr>
					<td><label>Nombre de combats :</label></td>
					<td>${participate_battle}</td>
				</tr>
				<tr>
					<td><label>Nombre de victoires :</label></td>
					<td>${win}</td>
				</tr>
				<tr>
					<td><label>Nombre de défaites :</label></td>
					<td>${lose}</td>
				</tr>
			</fieldset>
		</table>


		<% if(session.getAttribute("user") != null && ((User)session.getAttribute("user")).getId_robot() == 0)  { %>
			  <a class="btn btn-default" href="<%=request.getContextPath()%>/robots/join?id=${robot.id}">Rejoindre</a>
		<% }%>
	</div>
</div>

<%@ include file="/resources/layout/bot.jsp"%>