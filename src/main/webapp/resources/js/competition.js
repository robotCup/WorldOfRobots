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

	//chart
	/*$("#tree-data").jOrgChart({
		chartElement: $("#tree-view"), 
		nodeClicked: nodeClicked
	});

	// lighting a node in the selection
	function nodeClicked(node, type) {
		node = node || $(this);
		$('.jOrgChart .selected').removeClass('selected');
		node.addClass('selected');
	}*/


});