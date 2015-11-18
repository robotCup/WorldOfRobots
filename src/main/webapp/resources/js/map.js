var map;

/*var coords = document.getElementsByClassName('gps');
var adresses = document.getElementsByClassName('address');
var names = document.getElementsByClassName('name');*/

function initMap() {	

	var coords = document.getElementsByClassName('gps');
	var adresses = document.getElementsByClassName('address');
	var names = document.getElementsByClassName('name');
	
	var myLatLng = {lat: 48.8448934, lng: 2.3541811999999998};
	var infowindow = new google.maps.InfoWindow({
		maxWidth: 300
	});
	map = new google.maps.Map(document.getElementById('map'), {
		center: myLatLng,
		zoom: 8,
		mapTypeControl: false,
		scaleControl: false,
		streetViewControl: false,
		rotateControl: false
	});
	//-----géolocalisation

	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {

			var pos = {
					lat: position.coords.latitude,
					lng: position.coords.longitude
			};

			map.setCenter(pos);
		});
	}

	// ajout markers
	for (i = 0 ; i <coords.length; i++) {		
		//addMarker(eval('(' + coords[i].value + ')'));
		marker = new google.maps.Marker({
			'map': map,
			'position': eval('(' + coords[i].value + ')'),
			'title' : map.getCenter().toUrlValue(),
		});	
		marker.setMap(map);
		google.maps.event.addListener(marker, 'click', (function(marker, i) {
			return function() {
				infowindow.setContent("<h1>"+names[i].value+"</h1><p>"  + adresses[i].value +"</p>");
				infowindow.open(map, marker);
			}
		})(marker, i));

	}		
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {

	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeolocation ?
			'Erreur : le service de géolocalisation a échoué.' :
	'Erreur: Le navigateur ne supporte pas la géolocalisation.');
}

//----- fin géolocalisation

//------- autocomplete
var geocoder, autocomplete;

function initAutocomplete() {
	// Create the autocomplete object, restricting the search to geographical
	// location types.
	autocomplete = new google.maps.places.Autocomplete((document.getElementById('autocomplete')),{types: ['geocode']});
	
	autocomplete.addListener('place_changed', function(){
		
		var address = document.getElementById('autocomplete').value;
		
		geocoder = new google.maps.Geocoder();
		geocoder.geocode(
				{
					'address': address
				}, 
				function(results, status) {
					if (status === google.maps.GeocoderStatus.OK) {
						document.getElementById('geolocation').value = "{ lat : " + results[0].geometry.location.lat() + ", lng : " + results[0].geometry.location.lng() + " }";						
					}
					else {
						alert('Geocode was not successful for the following reason: ' + status);
					}
				});
	});
}

function getCoordsFromAddress(){
	
		
		
		/*
		geocoder = new google.maps.Geocoder();
		geocoder.geocode(
				{
					'address': address
				}, 
				function(results, status) {
					if (status === google.maps.GeocoderStatus.OK) {
						document.getElementById('geolocation').value = results[0].geometry.location;						
					}
					else {
						alert('Geocode was not successful for the following reason: ' + status);
					}
				});
*/
	
}

function geolocate() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(function(position) {
			var geolocation = {
					lat: position.coords.latitude,
					lng: position.coords.longitude
			};
			var circle = new google.maps.Circle({
				center: geolocation,
				radius: position.coords.accuracy
			});
			autocomplete.setBounds(circle.getBounds());
		});
	}
}
//------- fin autocomplete