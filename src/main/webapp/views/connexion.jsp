<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>
<div class="row">
	<form:form method="post" action="toConnect" commandName="connexion">
	<% if (request.getAttribute("testConnexion") != null && (Boolean)request.getAttribute("testConnexion")==false){%>
	erreur pas bon login
	<% } %>
		<fieldset>
			<legend>Se connecter : </legend>
			<table>
				<tr>
					<td><form:input placeholder="Login" path="login" id="login" value=""/>
					</td>
					<td><form:password placeholder="Mot de passe" path="pwd" id="pwd" value=""/></td>
					<td><input type="submit" value="Valider" /></td>
				</tr>
			</table>
		</fieldset>
	</form:form>

	<form:form method="post" action="toRegister" commandName="register">
	<% if (request.getAttribute("testInscription") != null && (Boolean)request.getAttribute("testInscription")==false){%>
	erreur login existe deja
	<% } else if (request.getAttribute("testInscription") != null){%>
	ok inscription veuillez vous connecter
	<% } %>
		<fieldset>
			<legend>S'inscrire : </legend>
			<table>
				<tr>
					<td><form:input type="text" placeholder="Login" path="login"
							id="login" value=""/></td>
				</tr>
				<tr>
					<td><form:password placeholder="Mot de passe" path="pwd"
							id="pwd" value=""/></td>
				</tr>
				<tr>
					<td><input type="password"
						placeholder="Confirmation mot de passe" id="pwd_confirm" value="" /></td>
				</tr>
				<tr>
					<td><form:input placeholder="Adresse mail" path="email"
							id="email" value=""/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Valider" /></td>
				</tr>
			</table>
		</fieldset>
	</form:form>
</div>
<%@ include file="/resources/layout/bot.jsp"%>