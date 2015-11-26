<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>

<!-- JS de la page -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/connexion.js"></script>

<div class="row">
	<div class="col-md-10">
		<form:form method="post" action="../user/toConnect" commandName="connexion"
			id="connexion" accept-charset="UTF-8">
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

		<form:form method="post" action="toRegister" commandName="register"
			id="register">
			<fieldset>
				<legend>Inscription : </legend>
				<table>
					<tr>
						<td><form:input type="text" placeholder="Login" path="login"
								name="login" id="login" value="" /></td>
						<td><form:input type="email" placeholder="Adresse mail"
								path="email" id="email" value="" /></td>
					</tr>
					<tr>
						<td><form:password placeholder="Mot de passe" path="pwd"
								name="pwd" value="" /></td>
						<td><form:password path="pwd_confirm"
								placeholder="Confirmez votre mot de passe" name="pwd_confirm"
								value="" /></td>
					</tr>
					<tr>
						<td><img
							src="<%=request.getContextPath()%>/resources/images/captcha/${captcha.image}"
							alt="" id="captcha"></td>
						<td><form:input type="text" placeholder="Saisir le mot affiché"
								path="captcha" id="captcha" value="" /></td>

					</tr>
					<tr>
						<td><input type="submit" class="btn btn-success"
							id="btn_register" value="S'inscrire" /></td>
					</tr>
				</table>
				<form:input type="hidden" path="idCaptcha" id="idCaptcha"
					value="${captcha.id}" />
				</td>
			</fieldset>
		</form:form>
	</div>
</div>
<%@ include file="/resources/layout/bot.jsp"%>