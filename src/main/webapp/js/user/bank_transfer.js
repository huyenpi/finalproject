$(document).ready(function() {

	$('#loader').hide();

	$("#formSubmit").on('submit', function(event) {
		event.preventDefault();
		if (validate()) {
			$('#loader').show();
			sendForm();
		}
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

		if ($("#d_amount").val() == '') {
			$("#d_amount").addClass('is-invalid');
			return false;

		} else if ($("#d_amount").val()) {
			$("#d_amount").removeClass('is-invalid');
			$("#d_amount").addClass('is-valid');
		}

		return true;

	}

	function sendForm() {
		var formData = new FormData(document.getElementById("formSubmit"));


		$.ajax({
			url: "luu-quyen-gop",
			type: "POST",
			data: formData,
			contentType: false,
			cache: false,
			processData: false,
			success: function(result) {
				$('#loader').hide();

				if (result.match('email_exist')) {

					$("#u_email").addClass('is-invalid');
					$("#error_email").remove();
					$("#u_email").before("<span id='error_email' class='text-danger'>Email đã tồn tại.</span>");

				} else if (!result.match('email_exist')) {
					$("#error_email").remove();
					$("#u_email").removeClass('is-invalid');
					$("#u_email").addClass('is-valid');

				}

				if (result.match('donation_success')) {

					var contextPath = $("#formSubmit").attr('myContextPath');
					window.location.replace(contextPath + "/views/user/thanks_for_donation.jsp");

				}

			},
			error: function() {
				alert("server đang bị lỗi!");
			}
		})
	}


})