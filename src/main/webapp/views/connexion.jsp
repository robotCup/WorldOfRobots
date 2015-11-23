<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>

<!-- JS de la page -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/connexion.js"></script>

<div class="row">
	<div class="col-md-10">
		<form:form method="post" action="toConnect" commandName="connexion">
			<%
				if (request.getAttribute("testConnexion") != null
							&& (Boolean) request.getAttribute("testConnexion") == false) {
			%>
	Erreur lors de la connexion
	<%
				}
			%>
			<fieldset>
				<legend>Se connecter : </legend>
				<table>
					<tr>
						<td><form:input placeholder="Login" path="login" name="login"
								value="" /></td>
						<td><form:password placeholder="Mot de passe" path="pwd"
								name="pwd" value="" /></td>
						<td><input type="submit" class="btn btn-primary"
							value="Valider" /></td>
					</tr>
				</table>
			</fieldset>
		</form:form>

		<form:form method="post" action="toRegister" commandName="register">
			<%
				if (request.getAttribute("testInscription") != null
							&& (Boolean) request.getAttribute("testInscription") == false) {
			%>
		Erreur lors de l'inscription
	<%
				} else if (request.getAttribute("testInscription") != null) {
			%>
		L'inscription est un succès. Veuillez vous connectez
	<%
				}
			%>
			<fieldset>
				<legend>Inscription : </legend>
				<table>
					<tr>
						<td><form:input type="text" placeholder="Login" path="login"
								id="login" value="" /></td>
						<td><form:input type="email" placeholder="Adresse mail"
								path="email" id="email" value="" /></td>
					</tr>
					<tr>
						<td><form:password placeholder="Mot de passe" path="pwd"
								id="pwd" value="" /></td>
						<td><input type="password"
							placeholder="Confirmez votre mot de passe" id="pwd_confirm"
							name="pwd_confirm" value="" /></td>
					</tr>
					<tr>
						<td><input type="submit" class="btn btn-success"
							value="S'inscrire" /></td>
					</tr>
				</table>
			</fieldset>
		</form:form>
	</div>
</div>
<%@ include file="/resources/layout/bot.jsp"%>