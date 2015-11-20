<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp" %>

<div id="content_mySpace">
${testUpdate}
<form:form method="post" action="modifyMySpace" commandName="update">
	<table>
	
			<tr>
				<td><h1>Mon espace : </h1></td>
			</tr>
			<tr>
				<td><label>Login :</label></td>
			
				<td>
					<form:input type="text"  path="login"
							id="login" value="${user.login}" />
				</td>
			</tr>
			<tr>
				<td><label>Ancien mot de passe :</label></td>
			
				<td>
					<form:password  path="pwdOld"
							id="pwdOld" value="" />
				</td>
			</tr>
			<tr>
				<td><label>Nouveau mot de passe :</label></td>
			
				<td>
					<form:password  path="pwd"
							id="pwd" value="" />
				</td>
			</tr>
			<tr>
				<td><label>Email :</label></td>
			
				<td>
					<form:input type="email"  path="email"
							id="email" value="${user.email}" />
				</td>
			</tr>

			<tr>
				<td><label>Robot équipe :</label></td>
			
				<td>
					<p>${robot.name}</p>
				</td>
				<td><a href="<%=request.getContextPath()%>/robots/card?id=${robot.id}">Voir la fiche</a></td>
			</tr>
			<tr>
			<form:input type="hidden"  path="id"
							id="id" value="${user.id}" />
			<td><input type="submit" value="Modifier mes informations" /></td>
			</tr>
	</table>
</form:form>
</div>
<%@ include file="/resources/layout/bot.jsp" %>