<%@ include file="/resources/layout/top.jsp" %>
<script type="text/javascript"
			src="<%=request.getContextPath()%>/resources/js/competition.js"></script>
<table>
	<tr>
		<td><input type="checkbox" class="date_choice" name="date_choice" value="0" checked/>Choix unique de dates de début</td>
		<td><input type="checkbox" class="date_choice" name="date_choice" value="1" />Choix multitple de dates de début (soumis aux votes)</td>
	</tr>
	<tr id="date_choice_multiple">
	</tr>
	<tr>
		<td><label>Name : </label></td>
		<td><input type="text" name="name" value="" /></td>
	</tr>
	<tr>
		<td><label>Description : </label></td>
		<td><textarea name="description"></textarea></td>
	</tr>
	<tr>
		<td><label>Nombre de robots : </label></td>
		<td><input type="number" min="1" name="robot_max" value="1" /></td>
	</tr>
	<tr>
		<td><label>Lieu : </label></td>
		<td>
			<div id="locationField">
      <input id="autocomplete" placeholder="Enter your address"
             onFocus="geolocate()" type="text"></input>
    </div>
		</td>
	</tr>
	<tr>
		<td><label>Durée : </label></td>
		<td><input type="number" name="robot_max" value="" /> Jours</td>
		
	</tr>	
</table>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/resources/js/map.js" /></script>		

<%@ include file="/resources/layout/bot.jsp" %>