<html xmlns:th="http://www.thymeleaf.org">
<%@ include file="/resources/layout/top.jsp" %>


	<form action="#" th:action="@{toConnect}" th:object="${connexion}" method="post" >
		
						<input placeholder="Login" type="text" th:field="*{login}"/>
				
						<input placeholder="Mot de passe" type="password" th:field="*{pwd}"/>
				
						<input type="submit" value="Valider" />
				
	</form>
			
<%@ include file="/resources/layout/bot.jsp" %>