$(document).ready(function() {

	$("#org").jOrgChart();

	//confirmation de participation
	$('#link_participe').on('click', function(){
		$.confirm({
			title: 'Participer à la compétition',
			content: 'Voulez-vous confirmer la participation de votre robot à cette compétition ?',
			confirmButtonClass: 'btn-success',
			cancelButtonClass: 'btn-danger',
			confirmButton: 'Confirmer',
			cancelButton: 'Annuler',
			animationBounce: 1.5,
			animationSpeed: 1500,
			confirm: function(){
				$.ajax({
					url: $('#url_participate').val(),
					type: "GET",		             
					success: function () {
						//$("#result_participate").text("Votre participation a bien été prise en compte");
						window.location.reload();
												
					},
					error : function(){
						//$("#result_participate").text("Votre participation n'a pas été prise en compte");
						window.location.reload();						
					}
				});
			}
		});
	});
	
	//confirmation de cloture des inscriptions
	$('#close_participe').on('click', function(){
		$.confirm({
			title: 'Clôturer les inscriptions',
			content: 'Etes-vous sûr de vouloir clôturer les inscriptions ?',
			confirmButtonClass: 'btn-success',
			cancelButtonClass: 'btn-danger',
			confirmButton: 'Oui',
			cancelButton: 'Annuler',
			animationBounce: 1.5,
			animationSpeed: 1500,
			confirm: function(){
				$.ajax({
					url: $('#url_close_participate').val(),
					type: "GET",		             
					/*success: function () {
						//$("#result_participate").text("Votre participation a bien été prise en compte");
						window.location.reload();
												
					},
					error : function(){
						//$("#result_participate").text("Votre participation n'a pas été prise en compte");
						window.location.reload();						
					}*/
				});
			}
		});
	});
});