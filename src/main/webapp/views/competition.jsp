<%@ include file="/resources/layout/top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!-- diagramme pour le classement -->
<link
	href="<%=request.getContextPath()%>/resources/lib/chart/orgchart.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/chart/orgchart.js"></script>
	
<!-- Confirm Jquery -->
<link
	href="<%=request.getContextPath()%>/resources/lib/confirm/jquery-confirm.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/confirm/jquery-confirm.min.js"></script>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/cardCompetition.js"></script>

<div class="row">
	<div class="col-md-8">
		<table>
			<tr>
				<td><h1>Fiche de la compétition " ${competition.name} "</h1></td>
			</tr>
			<tr>
				<td><label>Description :</label></td>
			</tr>
			<tr>
				<td>
					<p>${competition.description}</p>
				</td>
			</tr>
			<c:forEach var="a" items="${competition.battles}" varStatus="loop">
				<table>
					<tr>
						<td><h2>${loop.index+1 }e combat:</h2></td>
					</tr>
					<tr>
						<td><label>Date : </label></td>
						<td>
							<p>${a.date}</p>
						</td>
						</tr>
						<c:forEach var="b" items="${a.robots}">
							<tr><td><label>Nom du robot : </label>
								<p>${b.name}</p></td>
							<td><label>Points forts : </label>
								<p>${b.strong_point}</p></td></tr>
						</c:forEach>
						
					
					<tr>
						<td>
						<% int i=0; %>
						<c:if test="${a.id_winner!=0}">
						 Vainqueur  = 
						 <c:forEach var="b" items="${a.robots}">
										<c:if test="${b.id==a.id_winner}">
										
										<a href="<%=request.getContextPath()%>/robots/card?id=${b.id}">${b.name}</a>
										</c:if>
									</c:forEach>
						 </c:if>
						 <c:if test="${a.id_winner==0}">
							<% if( (Boolean)request.getAttribute("creator_battle") != null && (Boolean)request.getAttribute("creator_battle") == true) { %>
								Vainqueur :
								<select  id="robot_winner" type="number">
									<c:forEach var="b" items="${a.robots}">
										<option value="${b.id}" id="nbBattles">
										${b.name}
										</option>
									</c:forEach>
								</select>
								<button class="btn btn-primary" id="link_win">Valider le vainqueur</button>
								<input type="hidden" id="url_win" value="<%=request.getContextPath()%>/competition/win?idBattle=${a.id}&id=" />
							<% }%>
						 </c:if>
						</td>
						</tr>

						
				</table>
			</c:forEach>
									<tr>
							<td>
							<c:if test="${competition.id_winner!=0}">
						 Vainqueur  = 
								 <c:forEach var="r" items="${robots}">
												<c:if test="${r.id==competition.id_winner}">
													<a href="<%=request.getContextPath()%>/robots/card?id=${r.id}">${r.name}</a>
												</c:if>
									</c:forEach>
							 </c:if>
									
						 <c:if test="${competition.id_winner==0}">
							<% if( (Boolean)request.getAttribute("creator_battle") != null && (Boolean)request.getAttribute("creator_battle") == true) { %>
								Vainqueur :
								<select  id="robot_winnerCompetition" type="number">
									<c:forEach var="r" items="${robots}">
										<option value="${r.id}">
										${r.name}
										</option>
									</c:forEach>
								</select>
								<button class="btn btn-primary" id="link_winCompetition">Valider le vainqueur</button>
								<input type="hidden" id="url_winCompetition" value="<%=request.getContextPath()%>/competition/winCompetition?idCompetition=${competition.id}&id=" />
							<% }%>
						 </c:if>
							</td>
						</tr>
		</table>
		<%
			if (session.getAttribute("user") == null) {
		%>
		<a class="btn btn-primary"
			href="<%=request.getContextPath()%>/user/toConnect">Participer</a>
		<%
			} else if(((User)session.getAttribute("user")).getId_robot() != 0 && ((User)session.getAttribute("user")).isLeader() == true && (Boolean) request.getAttribute("isParticiped") == true) {
		%>
		<button class="btn btn-primary" id="link_participe">Participer</button>
		<input type="hidden" id="url_participate" value="<%=request.getContextPath()%>/competitions/participate?id=${competition.id}" />
		<%
			} else if((Boolean)request.getAttribute("boolean_inscription") != null && (Integer) request.getAttribute("id_user_competition") == ((User)session.getAttribute("user")).getId() && (Boolean)request.getAttribute("boolean_inscription") == false) {
		%>
			<a href="<%=request.getContextPath()%>/competition/toAddBattles?id=${competition.id}">Admin Battle</a>
						
			<% if((Boolean)request.getAttribute("cloture_vote") != null && (Boolean)request.getAttribute("cloture_vote") == true) { %>
		
			<button class="btn btn-warning-outline" id="close_vote">Clôturer les votes</button>
			<input type="hidden" id="url_close_vote" value="<%=request.getContextPath()%>/competitions/closeVote?id=${competition.id}" />
			
		<%	}
		 } 
			else if((Boolean) request.getAttribute("vote") == true && (Boolean) request.getAttribute("isParticiped") == true) { %>
			<a class="btn btn-warning-outline" href="<%=request.getContextPath()%>/competitions/vote?id=${competition.id}">Voter</a>
					<% } %>
		<span id="result_participate"></span>		
		<ul id="org" style="display: none">
			<li>Food:
				<ul>
					<li>Beer</li>
					<li class=collapsed>Vegetables
						<ul>
							<li>Carrot</li>
							<li>Pea</li>
						</ul>
					</li>
					<li>Chocolate</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
<%@ include file="/resources/layout/bot.jsp"%>
