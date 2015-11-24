<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>
<div class="row">
	<div class="col-md-10">
		<h1>Votre pour la comp�tition</h1>
		<br> <br>
		<p>
			L'organisateur a s�l�ctionn� entre deux et quatre dates de d�but de
			comp�tition.<br> Vous avez la possibilit�, en tant que
			participant, de voter pour la date qui vous convient le mieux.
		</p>
		<form:form method="post" action="vote" commandName="voteCompetiton">
			<table>
				<c:forEach var="date" items="${dates}" begin="0" end="4">
					<tr>
						<td><strong>${french_date[date.id]}</strong> 
						<form:radiobutton path="choose_date" value="${date.id}" /></td>
					</tr>
				</c:forEach>
				<tr>
					<td><input class="btn btn-success btn-lg" type="submit"
						value="Voter" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</div>
<%@ include file="/resources/layout/bot.jsp"%>