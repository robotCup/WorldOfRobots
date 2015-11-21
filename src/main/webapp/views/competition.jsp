<%@ include file="/resources/layout/top.jsp"%>

<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/resources/lib/chart/orgchart.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/chart/orgchart.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/chart.js"></script>

<div class="row">
	<table>
		<tr>
			<td><h1>Fiche descriptive de la compétition "
					${competition.name} "</h1></td>
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
					<td><h2>${loop.index+1 }e combat :</h2></td>
				</tr>
				<tr>
					<td><label>Date : </label></td>
					<td>
						<p>${a.date}</p>
					</td>
					<c:forEach var="b" items="${a.robots}">
						<td><label>Nom du robot : </label>
							<p>${b.name}</p></td>
						<td><label>Points forts : </label>
							<p>${b.strong_point}</p></td>
					</c:forEach>
				</tr>
			</table>
		</c:forEach>
	</table>
	
	<div class="col-md-4">
		<ul id="org" style="display:none">
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
