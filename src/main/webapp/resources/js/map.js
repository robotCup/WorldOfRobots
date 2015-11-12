var map;
var marker
var coords = document.getElementsByClassName('gps');

function addMarker(coord){
	
	marker = new google.maps.Marker({
		'map': map,
		'draggable': true,
		'animation': google.maps.Animation.DROP,
		'position': coord,
		'title' : map.getCenter().toUrlValue(),
	});	
	 marker.setMap(map);
}
function initMap() {

	var myLatLng = {lat: 48.8448934, lng: 2.3541811999999998};
	
	map = new google.maps.Map(document.getElementById('map'), {
		center: myLatLng,
		zoom: 8,
		mapTypeControl: false,
		scaleControl: false,
		streetViewControl: false,
		rotateControl: false
	});
	
	
	// ajout markers
	for (i = 0 ; i <coords.length; i++) {
		addMarker(eval('(' + coords[i].value + ')'));
	}
	

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
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeolocation ?
			'Erreur : le service de géolocalisation a échoué.' :
	'Erreur: Le navigateur ne supporte pas la géolocalisation.');
}

//----- fin géolocalisation
