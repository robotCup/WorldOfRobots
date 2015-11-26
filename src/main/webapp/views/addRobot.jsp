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
	
<!-- Selectize  -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/lib/selectize/js/standalone/selectize.min.js"></script>
<link
	href="<%=request.getContextPath()%>/resources/lib/selectize/css/selectize.css"
	rel="stylesheet" type="text/css">

<div class="row">
	<div class="col-md-8">
			<form:form method="post" action="add" commandName="AddRobot"
				enctype="multipart/form-data">
				<fieldset>
					<legend>Ajouter un robot : </legend>
					<table>
						<tr>
							<td><label>Nom : </label></td>
							<td><form:input placeholder="Nom du robot" path="name"
									id="name" /></td>
						</tr>

						<tr>
							<td><label>Photo principale : </label></td>
							<td><form:input type="file" path="image" id="test" accept="image/*"/></td>
						</tr>
						<tr>
							<td><label>Points forts : </label></td>
							<td><form:input placeholder="Points forts"
									path="strong_point" id="strong_point" /></td>
						</tr>
						<tr>
							<td><label>Technologies utilisées : </label></td>
							<td>
							<form:select path="technologies" multiple="true">
									<form:options items="${technologies}" itemValue="name"
										itemLabel="name" />
								</form:select>
								</td>
						</tr>
						<tr>
							<td><label>Date de création : </label></td>
							<td>
								<div class='input-group date datetimepicker'>
									<form:input type='text' class="form-control"
										path="creation_date" id="creation_date" />
									<span class="input-group-addon"> 
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td><input class="btn btn-primary btn-lg" type="submit" value="Ajouter" /></td>
						</tr>
					</table>
				</fieldset>
			</form:form>
	</div>
</div>

<%@ include file="/resources/layout/bot.jsp"%>