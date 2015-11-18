<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/robot.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/datetimepicker/js/moment.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/datetimepicker/js/fr.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/lib/datetimepicker/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" type="text/css">	
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
					<div class='input-group date datetimepicker'>
					<form:input type='text' class="form-control" path="creation_date" id="creation_date"/>
					<span class="input-group-addon"> <span
						class="glyphicon glyphicon-calendar"></span>
					</span>
				</div>
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