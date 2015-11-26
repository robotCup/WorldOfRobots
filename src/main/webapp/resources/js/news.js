$(document).ready(function() {	

	$('#tab_news').DataTable({
		colReorder: true,		 
		language: {
			"sProcessing":     "Traitement en cours...",
			"sSearch":         "Rechercher&nbsp;:",
			"sLengthMenu":     "Afficher _MENU_ &eacute;l&eacute;ments",
			"sInfo":           "Affichage de l'&eacute;l&eacute;ment _START_ &agrave; _END_ sur _TOTAL_ &eacute;l&eacute;ments",
			"sInfoEmpty":      "Affichage de l'&eacute;l&eacute;ment 0 &agrave; 0 sur 0 &eacute;l&eacute;ment",
			"sInfoFiltered":   "(filtr&eacute; de _MAX_ &eacute;l&eacute;ments au total)",
			"sInfoPostFix":    "",
			"sLoadingRecords": "Chargement en cours...",
			"sZeroRecords":    "Aucun message &agrave; afficher",
			"sEmptyTable":     "Aucun message disponible sur cette liste",
			"oPaginate": {
				"sFirst":      "Premier",
				"sPrevious":   "Pr&eacute;c&eacute;dent",
				"sNext":       "Suivant",
				"sLast":       "Dernier"
			},
			"oAria": {
				"sSortAscending":  ": activer pour trier la colonne par ordre croissant",
				"sSortDescending": ": activer pour trier la colonne par ordre d&eacute;croissant"
			}
		},
		"order": [[ 0, 'desc' ]],
		"columnDefs": [
		               { "width": "20%", "targets": 0 }
		               ]
	});
	

	$('.delete_msg').on('click', function(){
		alert($(this).parent().find('.url_delete_msg').val());
		$.confirm({
			title: 'Supprimer Message',
			content: 'Etes-vous sûr de vouloir supprimer ce message ?',
			confirmButtonClass: 'btn-success',
			cancelButtonClass: 'btn-danger',
			confirmButton: 'Oui',
			cancelButton: 'Annuler',
			animationBounce: 1.5,
			animationSpeed: 1500,
			confirm: function(){
				$.ajax({
					url: $(this).parent().find('.url_delete_msg').val(),
					type: "GET",		             
					success: function () {
						alert("oui");
						window.location.reload();												
					},
					error : function(){
						alert("oui");
						window.location.reload();						
					}
				});
			}
		});
	});
});