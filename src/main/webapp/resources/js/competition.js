$(document).ready(function() {

	$('.date_choice_multiple').hide();
	$('.date_choice_unique').show();
	 
	$( ".start_date" ).datepicker();
	 
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
});