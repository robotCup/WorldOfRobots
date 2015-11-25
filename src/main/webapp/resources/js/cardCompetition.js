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
						window.location.reload();												
					},
					error : function(){
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
					success: function () {
						window.location.reload();												
					},
					error : function(){
						window.location.reload();						
					}
				});
			}
		});
	});
	
	//confirmation de cloture des votes
	$('#close_vote').on('click', function(){
		$.confirm({
			title: 'Clôturer les votes',
			content: 'Etes-vous sûr de vouloir clôturer les votes ? La date séléctionnée sera celle qui aura récoltée le plus de votes des participants',
			confirmButtonClass: 'btn-success',
			cancelButtonClass: 'btn-danger',
			confirmButton: 'Oui',
			cancelButton: 'Annuler',
			animationBounce: 1.5,
			animationSpeed: 1500,
			confirm: function(){
				$.ajax({
					url: $('#url_close_vote').val(),
					type: "GET",		             
					success: function () {
						window.location.reload();												
					},
					error : function(){
						window.location.reload();						
					}
				});
			}
		});
	});
	
	$('#link_win').on('click', function(){
		$.confirm({
			title: 'Vainqueur du match',
			content: 'Voulez-vous confirmer le vainqueur à cette bataille ?',
			confirmButtonClass: 'btn-success',
			cancelButtonClass: 'btn-danger',
			confirmButton: 'Confirmer',
			cancelButton: 'Annuler',
			animationBounce: 1.5,
			animationSpeed: 1500,
			confirm: function(){
				$.ajax({
					url: $('#url_win').val()+$('#robot_winner').val(),
					type: "GET",		             
					success: function () {
						window.location.reload();												
					},
					error : function(){
						window.location.reload();						
					}
				});
			}
		});
	});
	
	$('#link_winCompetition').on('click', function(){
		$.confirm({
			title: 'Vainqueur de la compétition',
			content: 'Voulez-vous confirmer le vainqueur de cette compétition ?',
			confirmButtonClass: 'btn-success',
			cancelButtonClass: 'btn-danger',
			confirmButton: 'Confirmer',
			cancelButton: 'Annuler',
			animationBounce: 1.5,
			animationSpeed: 1500,
			confirm: function(){
				$.ajax({
					url: $('#url_winCompetition').val()+$('#robot_winnerCompetition').val(),
					type: "GET",		             
					success: function () {
						window.location.reload();												
					},
					error : function(){
						window.location.reload();						
					}
				});
			}
		});
	});
});

