//calendirer en francais
$.datepicker.regional['fr'] = {
		closeText: 'Fermer',
		prevText: '&#x3c;Préc',
		nextText: 'Suiv&#x3e;',
		currentText: 'Courant',
		monthNames: ['Janvier','Février','Mars','Avril','Mai','Juin',
		             'Juillet','Août','Septembre','Octobre','Novembre','Décembre'],
		             monthNamesShort: ['Jan','Fév','Mar','Avr','Mai','Jun',
		                               'Jul','Aoû','Sep','Oct','Nov','Déc'],
		                               dayNames: ['Dimanche','Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi'],
		                               dayNamesShort: ['Dim','Lun','Mar','Mer','Jeu','Ven','Sam'],
		                               dayNamesMin: ['Di','Lu','Ma','Me','Je','Ve','Sa'],
		                               weekHeader: 'Sm',
		                               //dateFormat: 'dd/mm/yy',
		                               dateFormat: 'dd/mm/yy',
		                               firstDay: 1,
		                               isRTL: false,
		                               showMonthAfterYear: false,
		                               yearSuffix: ''
};

$(document).ready(function() {
	
	$('.datetimepicker').datetimepicker({
		format: "DD/MM/YYYY HH:mm",
		locale : 'fr'
	});
	
	$("#add").validate({
		rules : {
			name : {
				required : true,
				minlength : 2
			},
			image : {
				required : false,
				minlength : 2
			},
			strong_point : {
				required : false,
				minlength : 2
			}
		},
		errorPlacement: function(error, element) {
			element.addClass(errorClass);			
		},
		errorClass: "invalid",
		onkeyup: true
	});
});