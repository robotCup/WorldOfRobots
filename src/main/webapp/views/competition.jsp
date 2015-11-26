<%@ include file="/resources/layout/top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- diagramme pour le classement -->
<link
	href="<%=request.getContextPath()%>/resources/lib/chart/jquery.orgchart.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/chart/jquery.orgchart.min.js"></script>

<!-- Confirm Jquery -->
<link
	href="<%=request.getContextPath()%>/resources/lib/confirm/jquery-confirm.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/confirm/jquery-confirm.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/cardCompetition.js"></script>

<div class="row">
	<h1>Fiche de la compétition " ${competition.name} "</h1>
	<c:if test="${fn:length(competition.battles) >1}">
		
	<div class="col-md-8">
	
		<div id="chart">
			<ul id="basic-chart-source" class="hide">
				<li>${users[competition.id_winner]}
					<ul>
					
						<c:forEach var="a" items="${competition.battles}" varStatus="loop">

							<li>${users[a.id_winner]}
								<ul>
									<c:forEach var="b" items="${a.robots}">
										<li>${b.name}</li>
									</c:forEach>
								</ul>
							</li>

						</c:forEach>
					</ul>
				</li>
			</ul>
		</div>
	</div>
					</c:if>
	<div class="col-md-8">
		<table>
		<fieldset>
		<legend>Données générales de la compétition</legend>
			<tr>
				<td><label>Description :</label></td>
			</tr>			
			<tr>
				<td>
					<p>${competition.description}</p>
				</td>
			</tr>
			<c:if test="${competition.id_winner!=0}">
				<tr>
					<td>
						<label>Vainqueur : </label> <c:forEach var="r"
							items="${robots}">
							<c:if test="${r.id==competition.id_winner}">
								<a href="<%=request.getContextPath()%>/robots/card?id=${r.id}">${r.name}</a>
							</c:if>
						</c:forEach>
					</td>
				</tr>
			</c:if>
		</fieldset>		
		</table>
		
		<table>
			<fieldset>
			<legend>Les combats</legend>		
			<c:forEach var="a" items="${competition.battles}" varStatus="loop">
				<table>
					<tr>
						<td><h2>${loop.index+1 }e combat:</h2></td>
					</tr>
					<tr>
						<td><label>Date : </label> ${french_date[a.id]}</td>
					</tr>
					<c:forEach var="b" items="${a.robots}">
						<tr>
							<td><label>Nom du robot : </label> ${b.name}</td>
						</tr>
						<tr>
							<td><label>Points forts : </label> ${b.strong_point}</td>
						</tr>
					</c:forEach>
					<tr>
						<td>
							<%
								int i = 0;
							%> <c:if test="${a.id_winner != 0}">
								<label>Vainqueur du combat : </label>
								<c:forEach var="b" items="${a.robots}">
									<c:if test="${b.id==a.id_winner}">
										<a href="<%=request.getContextPath()%>/robots/card?id=${b.id}">${b.name}</a>
									</c:if>
								</c:forEach>
							</c:if>
						</td>
					</tr>
					<c:if test="${a.id_winner==0}">
						<tr>
							<td> 
								<%
									if ((Boolean) request.getAttribute("creator_battle") != null
													&& (Boolean) request.getAttribute("creator_battle") == true) {
								%>
								<label>Vainqueur de la compétition : </label>
								<select id="robot_winner" type="number">
									<c:forEach var="b" items="${a.robots}">
										<option value="${b.id}" id="nbBattles">${b.name}</option>
									</c:forEach>
								</select>
								<button class="btn btn-primary" id="link_win">Valider
									le vainqueur</button>
								<input type="hidden" id="url_win"
									value="<%=request.getContextPath()%>/competition/win?idBattle=${a.id}&id=" />
								<%
									}
								%>
							</td>
						</tr>
					</c:if>					
				</table>
			</c:forEach>
			
			
			
			<c:if test="${competition.id_winner==0}">
				<% if ((Boolean) request.getAttribute("creator_battle") != null && (Boolean) request.getAttribute("creator_battle") == true) { %>
				<tr>
					<td>
						<label>Vainqueur de la compétition : </label>
						<select id="robot_winnerCompetition" type="number">
								<c:forEach var="r" items="${robots}">
									<option value="${r.id}">${r.name}</option>
								</c:forEach>
						</select>
							<button class="btn btn-primary" id="link_winCompetition">Valider
								le vainqueur</button>
							<input type="hidden" id="url_winCompetition"
							value="<%=request.getContextPath()%>/competition/winCompetition?idCompetition=${competition.id}&id=" />
					</td>
				</tr>
				<%
						}
					%>
			</c:if>
			</fieldset>
		</table>
		<table>
			<tr>
				<td>
					<%
						if (session.getAttribute("user") == null) {
					%> <a class="btn btn-primary"
					href="<%=request.getContextPath()%>/user/toConnect">Participer</a>
					<%
						} else if (((User) session.getAttribute("user")).getId_robot() != 0
								&& ((User) session.getAttribute("user")).isLeader() == true
								&& (Boolean) request.getAttribute("isParticiped") == true) {
					%>
					<button class="btn btn-primary" id="link_participe">Participer</button>
					<input type="hidden" id="url_participate"
					value="<%=request.getContextPath()%>/competitions/participate?id=${competition.id}" />
					<%
						} else if ((Boolean) request.getAttribute("boolean_inscription") != null
								&& (Integer) request.getAttribute("id_user_competition") == ((User) session.getAttribute("user"))
										.getId()
								&& (Boolean) request.getAttribute("boolean_inscription") == true) {
					%> <a class="btn btn-warning-outline"
					href="<%=request.getContextPath()%>/competition/toAddBattles?id=${competition.id}">Administration des combats
						Battle</a> <%
 	if ((Boolean) request.getAttribute("cloture_vote") != null
 				&& (Boolean) request.getAttribute("cloture_vote") == false) {
 %>


					<button class="btn btn-warning-outline" id="close_vote">Clôturer
						les votes</button> <input type="hidden" id="url_close_vote"
					value="<%=request.getContextPath()%>/competitions/closeVote?id=${competition.id}" />

					<%
						}
						} else if ((Boolean) request.getAttribute("vote") == true
								&& (Boolean) request.getAttribute("isParticiped") == true) {
					%> <a class="btn btn-warning-outline"
					href="<%=request.getContextPath()%>/competitions/vote?id=${competition.id}">Voter</a>
					<%
						}
					%>
				</td>
			</tr>
		</table>
	</div>
	<span id="result_participate"></span>
</div>

<%@ include file="/resources/layout/bot.jsp"%>
