$(document).ready(function() {

	$("#btnSubmit").on('click', function(event) {
		event.preventDefault();
		if (validate()) {
			$("#formSubmit").submit();
		}
	});

	function validate() {

		let pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;

		if ($("#u_email").val() == '') {
			$("#u_email").addClass('is-invalid');
			return false;
		} else if ($("#u_email").val()) {
			if (!pattern.test($("#u_email").val())) {
				$("#u_email").addClass('is-invalid');
				$("#error_email").remove();
				$("#u_email").before("<span id='error_email' class='text-danger'>Email không đúng định dạng.</span>");
				return false;
			} else if (pattern.test($("#u_email").val())) {
				$("#error_email").remove();
				$("#u_email").removeClass('is-invalid');
				$("#u_email").addClass('is-valid');
			}
		}
		return true;
	}
})