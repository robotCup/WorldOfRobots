<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>

<div id="content_mySpace">
	<h1>Modifier mes informations personnelles :</h1>

	<form:form method="post" action="modifyMySpace" commandName="update">
		<table>
			<tr>
				<td><label>Login :</label></td>

				<td><form:input type="text" path="login" id="login"
						value="${user.login}" /></td>
			</tr>
			<tr>
				<td><label>Ancien mot de passe :</label></td>

				<td><form:password path="pwdOld" id="pwdOld" value="" /></td>
			</tr>
			<tr>
				<td><label>Nouveau mot de passe :</label></td>

				<td><form:password path="pwd" id="pwd" value="" /></td>
			</tr>
			<tr>
				<td><label>Email :</label></td>

				<td><form:input type="email" path="email" id="email"
						value="${user.email}" /></td>
			</tr>
			<% if(request.getAttribute("robot") !=null){ %>
				<tr>
				
					<td><label>Mon robot :</label></td>
					<td><a
						href="<%=request.getContextPath()%>/robots/card?id=${robot.id}">${robot.name}</a></td>
				</tr>
				<tr>
			<% } %>
				<form:input type="hidden" path="id" id="id" value="${user.id}" />
				<td><input class="btn btn-primary btn-lg" type="submit"
					value="Modifier" /></td>
			</tr>
		</table>
	</form:form>
</div>
<%@ include file="/resources/layout/bot.jsp"%>