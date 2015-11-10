<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>

<form:form method="post" action="toConnect" commandName="connexion">
	<fieldset>
		<legend>Ajouter un robot : </legend>
		<table>
				<tr>
					<td>
						<form:input placeholder="Nom" path="name" id="name"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:password placeholder="Age"  path="age" id="age"/>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Ajouter" />
					</td>
				</tr>			
			</table>
	</fieldset>
</form:form>
<%@ include file="/resources/layout/bot.jsp"%>