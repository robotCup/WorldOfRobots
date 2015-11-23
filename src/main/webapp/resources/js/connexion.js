$(document).ready(function() {

	$("p.info").remove();
	
	
	$("#connexion").validate({
		rules : {
			login : "required" ,
			pwd : "required" ,
		},
		errorPlacement: function(error, element) {
			element.addClass("invalid");			
		},
		errorClass: "invalid",
	});
	
	$("#register").validate({
		rules : {
			login : {
				required : true,
				minlength: 2
			},
			pwd : {
				required : true,
				minlength: 2,
				equalTo : "#pwd_confirm"
			},
			pwd_confirm : {
				required : true,
				minlength: 2,
				equalTo : "#pwd"
			},
			email : {
				email : true,
				required : true,
				minlength: 2
			},
		},
		errorPlacement: function(error, element) {
			element.addClass("invalid");			
		},
		errorClass: "invalid",	
		
	});
});