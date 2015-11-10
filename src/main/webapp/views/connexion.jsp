<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp" %>


	<form:form method="post"  action="toConnect" commandName="connexion">
		<fieldset>
			<legend>Se connecter : </legend>
			<table>
				<tr>
					<td>
						<form:input placeholder="Login" path="login" id="login"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:password placeholder="Mot de passe"  path="pwd" id="pwd"/>
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