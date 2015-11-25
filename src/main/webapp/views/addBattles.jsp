<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>
<!-- diagramme pour le classement -->

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/datetimepicker/js/moment.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/datetimepicker/js/fr.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/battle.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/lib/datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" type="text/css">

<%
	int robotmax = (Integer) request.getAttribute("nbBattles");
%>

<div class="row">
	<div class="col-md-10">
		<h1>Administration des combats de " ${competition.name} "</h1>

		<form:form method="post" action="toAddBattles" commandName="addBattles" id="addBattles" onSubmit="return confirmSubmit();">
			<input type="hidden" value="<%=robotmax%>" id="robotMax" />
			<input type="hidden" value="${nbParticipant}" id="nbParticipant" />
			
			<table>
				<fieldset>
					<legend>Données générales de la compétition</legend>
					<tr>
						<td>Nombre de robots restant :</td>
						<td><label id="nbParticipantrestant">${nbParticipant}</label></td>
					</tr>
					<tr>
						<td>
							<form:hidden value="${competition.id}" path="idCompetition" />
						</td>					
					</tr>
					<tr>
						<td>Nombre de combats :</td>

						<td><form:select path="nbMatch" id="select" type="number">
								<%
									for (int i = 1; i < robotmax + 1; i++) {
								%>
								<form:option value="<%=i%>" id="nbBattles">
									<%=i%></form:option>
								<%
									}
								%>
							</form:select></td>
					</tr>
				</fieldset>

			</table>
			<div id="battles">
				<%
					for (int i = 1; i < robotmax + 1; i++) {
				%>
				<div class="battle" id='battle<%=i%>'>
					<table>
						<fieldset>
							<legend>Données d'un combat</legend>
						<tr>
							<td>Date :</td>
							<td>
								<div class='input-group date datetimepicker'>
									<form:input type='text' class="form-control"
										path="datesBattles" />
									<span class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>

							</td>
						</tr>
						<tr>
							<td>Nombre d'équipes maximum :</td>
							<td><input type="hidden" class="index" value="<%=i%>">
								<form:input type="number" class="nbEquipes" path='nbEquipes'
									min="0" max="0" value="0" /></td>
						</tr>
						
						</fieldset>
					</table>
				</div>
				<%
					}
				%>
			</div>

			<input type="submit" id="valid_battles" class="btn btn-primary" value="Valider" disabled/>

		</form:form>
	</div>
</div>
<%@ include file="/resources/layout/bot.jsp"%>
