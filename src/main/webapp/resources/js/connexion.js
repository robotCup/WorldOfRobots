$.validator.addMethod(
        "regex",
        function(value, element, regexp) {
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
        },
        "Please check your input."
);
$(document).ready(function() {

	$("#connexion").validate({
		rules : {
			login : {
				required : true,
				regex : "^([A-Z]|[a-z])[a-z]*(_)?[a-z]+$"
			},
			pwd : {
				required : true,
				regex : "^([A-Z]|[a-z])[a-z]*(_)?[a-z]+$"
			},
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
				minlength: 2,
				regex : "^([A-Z]|[a-z])[a-z]*(_)?[a-z]+$"
			},
			pwd : {
				required : true,
				minlength: 2,
				regex : "^([A-Z]|[a-z])[a-z]*(_)?[a-z]+$"
			},
			pwd_confirm : {
				required : true,
				minlength: 2,
				regex : "^([A-Z]|[a-z])[a-z]*(_)?[a-z]+$"
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