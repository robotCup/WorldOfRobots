<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/competition.js"></script>

<form:form method="post" action="add" commandName="AddRobot" enctype="multipart/form-data">
	<fieldset>
		<legend>Ajouter un robot : </legend>
		<table>
				<tr>
					<td>
						<form:input placeholder="Nom" path="name" id="name"/>
					</td>
				</tr>

								<tr>
					<td>
				<form:input type="file"  path="image"  id="test"/>
					</td>
				</tr>
				<tr>
					<td>
				<form:input type="text" class="start_date" path="creation_date" value="" />		
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Ajouter" />
					</td>
				</tr>	
	
			</table>
	</fieldset>
</form:form>
<%@ include file="/resources/layout/bot.jsp"%>