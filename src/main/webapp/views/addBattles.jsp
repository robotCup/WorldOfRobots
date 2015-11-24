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
<div class="row">
	<div class="col-md-8">
		<form:form method="post" action="toAddBattles"
			commandName="addBattles" id="connexion" onSubmit="return confirmSubmit();" >
			<%
								int robotmax = (Integer) request.getAttribute("nbBattles");
							%>
					<input type="hidden" value="<%= robotmax %>" id="robotMax" />
					<input type="hidden" value="${nbParticipant}" id="nbParticipant" />
						<input type="text" value="${nbParticipant}" id="nbParticipantrestant" />
			<table>
				<tr>
					<td><h1>Fiche de la compétition " ${competition.name} "</h1></td>
				</tr>
				<tr>
				<td>
				<form:hidden  value="${competition.id}" path ="idCompetition" />
				
				</td>
				</tr>
				<tr>
					<td><label>Nombre de Matchs :</label></td>
					
					<td><form:select path="nbMatch" id="select" type="number">
							
							
							<%
								for (int i = 1; i < robotmax + 1; i++) {
							%>
							<form:option value="<%=i%>" id="nbBattles"> <%=i%></form:option>
							<%}%>
							
							
						</form:select></td>
				</tr>
			</table>
			<div id="battles">
			<%
								for (int i = 1; i < robotmax + 1; i++) {
							%>
				<div class="battle" id='battle<%= i %>'>
					<table>
					<tr>
						<td>
						Date Match
						</td>
						<td>
<div class='input-group date datetimepicker'>
									<form:input type='text' class="form-control"
										path="datesBattles" id="creation_date" />
									<span class="input-group-addon"> 
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>

						</td>
						</tr>
						<tr>
							<td>Nombre d'équipes max participantes:</td>
							<td>
							<input type="hidden" class="index" value="<%=i%>">
							<form:input type="number" class ="nbEquipes"  path='nbEquipes' min="2" max="" value="0"/> 
							</td>
						</tr>
						<tr>
							<td>
							<input type="button" value="valider battle" class="validateBattle<%= i  %>" onClick="function test(<%= i  %>)"/>
							</td>
						</tr>
					</table>
										
				</div>
				<%
								}
							%>
			</div>
			
			<input type="submit" class="btn btn-primary" 
							value="Valider" />

		</form:form>
	</div>
</div>
<%@ include file="/resources/layout/bot.jsp"%>
