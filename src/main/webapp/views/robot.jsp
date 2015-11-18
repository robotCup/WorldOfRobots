<%@ include file="/resources/layout/top.jsp"%>




Nom robot : ${robot.name} <br/>
${robot.strong_point} <br/>
<img
					src="<%=request.getContextPath()%>/resources/images/${robot.path_picture}"
					alt="img01" />
${robot.creation_date} <br/>







<%@ include file="/resources/layout/bot.jsp"%>