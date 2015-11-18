<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ include file="/resources/layout/top.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/competition.js"></script>

<form:form method="post" commandName="add">
	<table class="first">
		<tr>
			<td><input type="radio" class="date_choice" name="date_choice"
				value="0" checked />Choix unique de dates de début</td>

			<td><input type="radio" class="date_choice" name="date_choice"
				value="1" />Choix multitple de dates de début (soumis aux votes)</td>
		</tr>

		<tr class="date_choice_unique">
			<td><label>Date :</label></td>
			<td><form:input type="text" class="start_date" path="date_start_1" value="" /></td>
		</tr>
		
		<tr class="date_choice_multiple">
			<td><label>Première date :</label></td>
			<td><form:input type="text" class="start_date" path="date_start_1" value="" /></td>

			<td><label>Deuxième date :</label></td>
			<td><form:input type="text" class="start_date" path="date_start_2" value="" /></td>
		</tr>

		<tr class="date_choice_multiple">
			<td><label>Troisième date :</label></td>
			<td><form:input type="text" class="start_date" path="date_start_3" value="" /></td>

			<td><label>Quatrième date :</label></td>
			<td><form:input type="text" class="start_date" path="date_start_4" value="" /></td>
		</tr>
	</table>

	<table>
		<tr>
			<td><label>Nom : </label></td>
			<td><form:input type="text" name="name" path="name" value="" /></td>
		</tr>
		<tr>
			<td><label>Description : </label></td>
			<td><form:textarea name="description" path="description"></form:textarea></td>
		</tr>
		<tr>
			<td><label>Nombre de robots : </label></td>
			<td><form:input type="number" min="1" name="robot_max" path="robot_max" value="1" /></td>
		</tr>
		<tr>
			<td><label>Lieu : </label></td>
			<td>
				<div id="locationField">
					<form:input id="autocomplete" placeholder="Votre adresse" onFocus="geolocate()" type="text" path="address"/>
					<form:input type="hidden" name="geolocation" path="geolocation" value="" />
				</div>
			</td>
		</tr>
		<tr>
			<td><label>Durée : </label></td>
			<td><form:input type="number" name="duration" path="duration" value="" /> Jours</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="Ajouter" />
			</td>
		</tr>
	</table>
</form:form>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/resources/js/map.js" /></script>
<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAJMQnUVO7WPqS96NqQUObz4RtxuQzADTY&signed_in=true&libraries=places&callback=initAutocomplete"
	async defer></script>
<%@ include file="/resources/layout/bot.jsp"%>