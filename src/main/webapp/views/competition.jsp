<%@ include file="/resources/layout/top.jsp"%>

description : <br/>
${competition.description} <br/>
<c:forEach var="a" items="${competition.battles}">
datte battle : ${a.date} <br/>
<c:forEach var="b" items="${a.robots}">
Nom rboot : ${b.name} <br/>
${b.strong_point} <br/>
</c:forEach>
</c:forEach>
