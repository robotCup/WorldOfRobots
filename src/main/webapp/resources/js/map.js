var map;
var marker;
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

//------- autocomplete
var placeSearch, autocomplete;

function initAutocomplete() {
	// Create the autocomplete object, restricting the search to geographical
	// location types.
	autocomplete = new google.maps.places.Autocomplete(
			/** @type {!HTMLInputElement} */(document.getElementById('autocomplete')),
			{types: ['geocode']});

	// When the user selects an address from the dropdown, populate the address
	// fields in the form.
	autocomplete.addListener('place_changed', fillInAddress);
}

// [START region_fillform]
function fillInAddress() {
	// Get the place details from the autocomplete object.
	var place = autocomplete.getPlace();

	for (var component in componentForm) {
		document.getElementById(component).value = '';
		document.getElementById(component).disabled = false;
	}

	// Get each component of the address from the place details
	// and fill the corresponding field on the form.
	for (var i = 0; i < place.address_components.length; i++) {
		var addressType = place.address_components[i].types[0];
		if (componentForm[addressType]) {
			var val = place.address_components[i][componentForm[addressType]];
			document.getElementById(addressType).value = val;
		}
	}
}
// [END region_fillform]

// [START region_geolocation]
// Bias the autocomplete object to the user's geographical location,
// as supplied by the browser's 'navigator.geolocation' object.
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
