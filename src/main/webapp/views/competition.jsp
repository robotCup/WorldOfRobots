<%@ include file="/resources/layout/top.jsp"%>

<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/resources/lib/chart/orgchart.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/chart/orgchart.js"></script>


<div class="row">
description : <br/>
${competition.description} <br/>
<c:forEach var="a" items="${competition.battles}">
datte battle : ${a.date} <br/>
<c:forEach var="b" items="${a.robots}">
Nom rboot : ${b.name} <br/>
${b.strong_point} <br/>
</c:forEach>
</c:forEach>
	<ul id="tree-data" style="display: none">
		<li id="root">root
			<ul>
				<li id="node1">node1</li>
				<li id="node2">node2
					<ul>
						<li id="node3">node3
							<ul>
								<li id="node4">node4</li>
								<li id="node11">node11</li>
							</ul>
						</li>
						<li id="node18" class="last">node18
							<ul>
								<li id="node19">node19</li>

								<li id="node20">node20</li>
							</ul>
						</li>
					</ul>
				</li>

			</ul>
		</li>
	</ul>
</div>

<%@ include file="/resources/layout/bot.jsp"%>

