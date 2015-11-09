<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp" %>


	<form:form method="post" enctype="multipart/form-data" action="#">
		<fieldset>
			<legend>Se connecter : </legend>
			<table>
				<tr>
					<td>
						<input placeholder="Login" type="text" name="login" />
					</td>
				</tr>
				<tr>
					<td>
						<input placeholder="Mot de passe" type="password" name="mdp" />
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Valider" />
					</td>
				</tr>			
			</table>
		</fieldset>
	</form:form>
			
<%@ include file="/resources/layout/bot.jsp" %>