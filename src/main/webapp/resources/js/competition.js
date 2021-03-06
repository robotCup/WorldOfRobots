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
		                               dateFormat: 'dd/mm/yy',
		                               firstDay: 1,
		                               isRTL: false,
		                               showMonthAfterYear: false,
		                               yearSuffix: ''
};

$(document).ready(function() {

	//datetimepicker
	$('.date_choice_multiple').hide();
	$('.date_choice_unique').show();

	$('#date_choice_unique').prop( "checked", true );
	
	$('.datetimepicker').datetimepicker({
		format: "DD/MM/YYYY HH:mm",
		locale : 'fr',
		//minDate: new Date(new Date().getTime() + 24 * 60 * 60 * 1000)
	});

	$('.date_choice').on('change', function(){		

		if($('#date_choice_unique').is(':checked')){
			$('.date_choice_multiple').hide();
			$('.date_choice_unique').show();	
		}			
		else if($('#date_choice_multiple').is(':checked')){
			$('.date_choice_multiple').show();
			$('.date_choice_unique').hide();
		}
	});
	
	//ajout d'une compet
	$("#add").validate({
		rules : {
			date_start : {
				required : {
					depends : function(element){
						return $("#date_choice_unique").is(':checked');
					}
				}				
			},
			name : {
				required : true,
				minlength: 2
			},
			description : {
				required : true,
				minlength: 2
			},
			robot_max : {
				required : true,
				min: 1,
				number: true
			},
			address : {
				required : true,
				minlength: 2
			},
			duration : {
				required : true,
				min: 1,
				number: true
			},
			date_start_1 : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}				
			},
			date_start_2 : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}	
			},
			date_start_3 : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}	
			},
			date_start_4 : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}	
			}			
		},
		errorPlacement: function(error, element) {
			element.addClass("invalid");			
		},
		errorClass: "invalid",
	});
	
});