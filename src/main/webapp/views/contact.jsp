<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp" %>

	<form:form method="get"  action="contact" commandName="contact">
		<fieldset>
			<legend>Envoyer un message : </legend>
			<table>
				<tr>
					<td>
						<form:input placeholder="Adresse mail" path="email" id="email"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:input placeholder="Objet du message" path="objet" id="objet"/>
					</td>
				</tr>
				<tr>
					<td>
						<form:textarea placeholder="Message"  path="message" id="message" rows="5" cols="30"></form:textarea>
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Envoyer" />
					</td>
				</tr>			
			</table>
		</fieldset>
	</form:form>
			
<%@ include file="/resources/layout/bot.jsp" %>