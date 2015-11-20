$(document).ready(function() {

	$("#connexion").validate({
		rules : {
			'login' : {
				required : true,
				minlength: 2
			},
			'pwd' : {
				required : true,
				minlength: 2
			}
		},
		errorPlacement: function(error, element) {
			element.addClass("error");			
		},
		errorClass: "invalid",
		onkeyup: true
	});
	
	$("#register").validate({
		rules : {
			'login' : {
				required : true,
				minlength: 2
			},
			'pwd' : {
				required : true,
				minlength: 2
			},
			'pwd_confirm' : {
				required : true,
				minlength: 2,
				equalTo : "#pwd"
			},
			'email' : {
				email : true,
				required : true,
				minlength: 2
			}
		},
		errorPlacement: function(error, element) {
			element.addClass("error");			
		},
		errorClass: "invalid",
		onkeyup: true
	});
});