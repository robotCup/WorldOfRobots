<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp" %>
		<form:form method="post" enctype="multipart/form-data" action="#">
			<input placeholder="Login" type="text" name="login" />
			<input placeholder="Mot de passe" type="password" name="mdp" />
			<input type="submit" value="Valider" />
		</form:form>
<%@ include file="/resources/layout/bot.jsp" %>