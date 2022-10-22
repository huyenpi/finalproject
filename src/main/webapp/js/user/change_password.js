$(document).ready(function() {



	$("#formSubmit").on('submit', function(event) {
		event.preventDefault();
		sendForm();
	});

	function validate() {

		if ($("#u_password").val() == '') {
			$("#u_password").addClass('is-invalid');
			return false;
		} else if ($("#u_password").val()) {
			if ($("#u_password").val().length < 6) {
				$("#u_password").addClass('is-invalid');
				$("#error_password").remove();
				$("#u_password").before("<span id='error_password' class='text-danger'>Mật khẩu gồm ít nhất 6 kí tự.</span>");
				return false;
			} else {
				$("#error_password").remove();
				$("#u_password").removeClass('is-invalid');
				$("#u_password").addClass('is-valid');
			}
		}

		if ($("#new_password").val() == '') {
			$("#new_password").addClass('is-invalid');
			return false;
		} else if ($("#new_password").val()) {
			if ($("#new_password").val().length < 6) {
				$("#new_password").addClass('is-invalid');
				$("#error_password").remove();
				$("#new_password").before("<span id='error_password' class='text-danger'>Mật khẩu gồm ít nhất 6 kí tự.</span>");
				return false;
			} else {
				$("#error_password").remove();
				$("#new_password").removeClass('is-invalid');
				$("#new_password").addClass('is-valid');
			}
		}

		if ($("#re_new_password").val() == '') {
			$("#re_new_password").addClass('is-invalid');
			return false;
		} else if ($("#re_new_password").val()) {
			if ($("#re_new_password").val() != $("#new_password").val()) {
				$("#re_new_password").addClass('is-invalid');
				$("#error_password").remove();
				$("#re_new_password").before("<span id='error_password' class='text-danger'>Mật khẩu không khớp.</span>");
				return false;
			} else {
				$("#error_password").remove();
				$("#re_new_password").removeClass('is-invalid');
				$("#re_new_password").addClass('is-valid');
			}
		}

		return true;

	}

	function sendForm() {
		var formData = new FormData(document.getElementById("formSubmit"));
		if (validate()) {

			$.ajax({
				url: "thay-doi-mat-khau",
				type: "POST",
				data: formData,
				contentType: false,
				cache: false,
				processData: false,
				success: function(result) {
					
					if (result.match('change_password_success')) {

						var contextPath = $("#formSubmit").attr('myContextPath');
						window.location.replace(contextPath + "/views/user/change_password_notice.jsp");
					}

					if (result.match('wrong_password')) {

						$("#u_password").addClass('is-invalid');
						$("#error_password").remove();
						$("#u_password").before("<span id='error_password'class='text-danger'>Mật khẩu không đúng.</span>");
					} else if (!result.match('wrong_password')) {
						$("#error_password").remove();
						$("#u_password").removeClass('is-invalid');
						$("#u_password").addClass('is-valid');
					}

				},
				error: function() {
					alert("server đang bị lỗi!");
				}
			})
		}
	}

})