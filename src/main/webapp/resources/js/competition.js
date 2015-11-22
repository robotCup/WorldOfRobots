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

	//datetimepicker
	$('.date_choice_multiple').hide();
	$('.date_choice_unique').show();

	$('.datetimepicker').datetimepicker({
		format: "DD/MM/YYYY HH:mm",
		locale : 'fr'
	});

	$('.date_choice').on('change', function(){		

		if($(this).val() == "0"){
			$('.date_choice_multiple').toggle();
			$('.date_choice_unique').toggle();			
		}			
		if($(this).val() == "1"){
			$('.date_choice_multiple').toggle();
			$('.date_choice_unique').toggle();
		}
	});
	//ajout d'une compet
	$("#add").validate({
		rules : {
			'date_start' : {
				required : {
					depends : function(element){
						return $("#date_choice_unique").is(':checked');
					}
				}				
			},
			'name' : {
				required : true,
				minlength: 2
			},
			'description' : {
				required : true,
				minlength: 2
			},
			'robot_max' : {
				required : true,
				min: 1,
				number: true
			},
			'address' : {
				required : true,
				minlength: 2
			},
			'duration' : {
				required : true,
				min: 1,
				number: true
			},
			'date_start_1' : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}				
			},
			'date_start_2' : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}	
			},
			'date_start_3' : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}	
			},
			'date_start_4' : {
				required : {
					depends : function(element){
						return $("#date_choice_multiple").is(':checked');
					}
				}	
			}			
		},
		errorPlacement: function(error, element) {
			element.addClass(errorClass);			
		},
		errorClass: "invalid",
		onkeyup: true
	});
	
});