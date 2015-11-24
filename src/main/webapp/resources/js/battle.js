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
	
	$('#battle1').show();
	$('#battle1').find('.nbEquipes').attr("max", parseInt($('#nbParticipantrestant').val()))
	$('#battle1').find('.nbEquipes').attr("value",2);
	$('#nbParticipantrestant').attr("value",parseInt($('#nbParticipant').val())-(parseInt($('#select').val())*2 ));
	$('#select').on('change', function(){
		$('.battle').hide();
	$('#nbParticipantrestant').attr("value",parseInt($('#nbParticipant').val())-(parseInt($('#select').val())*2 ));
		//var t=1;
		$('.nbEquipes').attr("max",parseInt($('#nbParticipantrestant').val())+2);
		for (var i = 0; i < parseInt($('#select').val()); i++){
			$('#battle'+(i+1)).show();
			$('#battle'+(i+1)).find('.nbEquipes').attr("value",2);
			//t=t+1;
		}

	});	
	$('.nbEquipes').on('change',function(){
		var TotalParticipant=0;
		for (var i =1; i<parseInt($('#select').val())+1; i++){
			TotalParticipant=parseInt($('#battle'+i).find('.nbEquipes').val())+TotalParticipant;
		}
		var restant = parseInt($('#nbParticipant').val())-TotalParticipant;
		for (var i =1; i<parseInt($('#select').val())+1; i++){
			$('#battle'+i).find('.nbEquipes').attr("max", parseInt($('#battle'+i).find('.nbEquipes').val())+restant);
		}
		$('#nbParticipantrestant').attr("value",restant);
		
		/*var index = parseInt($(this).parent().find('.index').val());
		var nbmatch= parseInt($('#select').val()) -(index+1);
		alert("nb match restant = " + (nbmatch+1));
		alert("nb parti "+ $('#nbParticipant').val());
		var modif= parseInt($('#nbParticipant').val())-parseInt($(this).val());
		
		$('#nbParticipant').attr("value",modif )
		alert ("nb parti restant " +$('#nbParticipant').val());
		var nbPersonnesMax = parseInt($('#nbParticipant').val())-(nbmatch*2);
		if (nbPersonnesMax<2){
			nbPersonnesMax=2;
		}
		alert ("nombre de personnes max prochain mactch "+ nbPersonnesMax);
		$('#battle'+index).find('.nbEquipes').attr("max", nbPersonnesMax);
		alert(parseInt($('#nbParticipant').val())-((index-1)*2));*/
		
	});
	/*$("#validate").on("submit",function(){
		var t=parseInt($('#robotMax').val())+1;
		alert(t);
		for(var i = parseInt($('#select').val());i<parseInt($('#robotMax').val());i++){
			alert($('#battle'+t));
			$('#battle'+t).remove();
			t=t+1;}
		
	});*/

});
function confirmSubmit(){
	
	var t=parseInt($('#robotMax').val())+1;
	alert(t);
	alert(parseInt($('#select').val()));
	for(var i = parseInt($('#select').val())+1;i<parseInt($('#robotMax').val())+1;i++){
		$('#battle'+i).remove();
		alert(i);
}
}

