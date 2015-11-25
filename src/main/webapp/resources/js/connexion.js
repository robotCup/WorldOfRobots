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
				regex : "^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ._\s-]{2,100}$"
			},
			pwd : {
				required : true,
				regex : "^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ._\s-]{2,100}$"
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
				regex : "^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ._\s-]{2,100}$"
			},
			pwd : {
				required : true,
				minlength: 2,
				regex : "^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ._\s-]{2,100}$"
			},
			pwd_confirm : {
				required : true,
				minlength: 2,
				regex : "^[a-zA-Z0-9áàâäãåçéèêëíìîïñóòôöõúùûüýÿæœÁÀÂÄÃÅÇÉÈÊËÍÌÎÏÑÓÒÔÖÕÚÙÛÜÝŸÆŒ._\s-]{2,100}$"
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