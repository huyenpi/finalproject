$(document).ready(function() {

	$("#formSubmit").on('submit', function(event) {
		event.preventDefault();
		sendForm();
	});

	function validate() {

		let pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
		if ($("#u_name").val() == '') {
			$("#u_name").addClass('is-invalid');
			return false;

		} else if ($("#u_name").val()) {
			$("#u_name").removeClass('is-invalid');
			$("#u_name").addClass('is-valid');
		}

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

	function sendForm() {
		var formData = new FormData(document.getElementById("formSubmit"));

		if (validate()) {

			$.ajax({
				url: "cap-nhat-thong-tin-ca-nhan",
				type: "POST",
				data: formData,
				contentType: false,
				cache: false,
				processData: false,
				success: function(result) {
					if (result.match('update_success')) {
						var contextPath = $("#formSubmit").attr('myContextPath');
						window.location.replace(contextPath + "/thong-tin-ca-nhan?result=" + result);
					}

					if (result.match('email_exist')) {

						$("#u_email").addClass('is-invalid');
						$("#error_email").remove();
						$("#u_email").before("<span id='error_email' class='text-danger'>Email đã tồn tại.</span>");

					} else if (!result.match('email_exist')) {
						$("#error_email").remove();
						$("#u_email").removeClass('is-invalid');
						$("#u_email").addClass('is-valid');
					}

				},
				error: function() {
					alert("server đang bị lỗi!");
				}
			})
		}
	}

})

