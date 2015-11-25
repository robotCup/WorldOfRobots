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
	$('.datetimepicker').datetimepicker({
		format: "DD/MM/YYYY HH:mm",
		locale : 'fr'
	});

	//init bloc battle
	$('#battle1').show();
	$('#battle1').find('.nbEquipes').attr("max", parseInt($('#nbParticipantrestant').text()))
	$('#battle1').find('.nbEquipes').attr("value", parseInt($('#nbParticipantrestant').text()))
	$('#battle1').find('.nbEquipes:not(:first)').attr("value", 0);
	$('#nbParticipantrestant').text(0);

	console.log($('#nbParticipantrestant').text());

	if($('#nbParticipantrestant').text() == 0){
		$("#valid_battles").prop("disabled", false); 
	}

	//show div battle
	$('#select').on('change', function(){
		$('.battle').hide();
		$('#nbParticipantrestant').text(parseInt($('#nbParticipant').val())-(parseInt($('#select').val())*2 ));
		$('.nbEquipes').attr("max",parseInt($('#nbParticipantrestant').text())+2);

		for (var i = 0; i < parseInt($('#select').val()); i++){
			$('#battle'+(i+1)).show();
			$('#battle'+(i+1)).find('.nbEquipes').attr("value",2);
			$('#battle'+(i+1)).find('.nbEquipes').attr("max",parseInt($('#nbParticipantrestant').text())+2);
		}

		if($('#nbParticipantrestant').text() == 0){
			$("#valid_battles").prop("disabled", false); 
		}
		else{
			$("#valid_battles").prop("disabled", "disabled"); 
		}
	});	

	//maj des nb participants
	$('.nbEquipes').on('change',function(){

		var TotalParticipant = 0;

		for (var i =1; i<parseInt($('#select').val())+1; i++){
			TotalParticipant=parseInt($('#battle'+i).find('.nbEquipes').val())+TotalParticipant;
		}

		var restant = parseInt($('#nbParticipant').val())-TotalParticipant;

		for (var i = 1; i < parseInt($('#select').val())+1 ; i++){
			$('#battle'+i).find('.nbEquipes').attr("max", parseInt($('#battle'+i).find('.nbEquipes').val())+restant);
			$('#battle'+i).find('.nbEquipes').attr("min", parseInt($('#battle'+i).find('.nbEquipes').val())-restant);
		}

		$('#nbParticipantrestant').text(restant);

		if($('#nbParticipantrestant').text() == 0){
			$("#valid_battles").prop("disabled", false); 
		}
		else{
			$("#valid_battles").prop("disabled", "disabled"); 
		}
	});	
});

function confirmSubmit(){

	var t = parseInt($('#robotMax').val())+1;

	for(var i = parseInt($('#select').val())+1;i<parseInt($('#robotMax').val())+1;i++){
		$('#battle'+i).remove();
	}
}

