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

		return true;

	}

	function sendForm() {
		var formData = new FormData(document.getElementById("formSubmit"));
		$.ajax({
			url: "dang-nhap",
			type: "POST",
			data: formData,
			contentType: false,
			cache: false,
			processData: false,
			success: function(result) {
				$('#loader').hide();

				if (result.match('user_login')) {

					var contextPath = $("#formSubmit").attr('myContextPath');
					window.location.replace(contextPath + "/trang-nguoi-dung");
				}

				if (result.match('admin_login')) {

					var contextPath = $("#formSubmit").attr('myContextPath');
					window.location.replace(contextPath + "/trang-quan-tri");
				}

				if (result.match('email_not_exist')) {

					$("#u_email").addClass('is-invalid');
					$("#error_email").remove();
					$("#u_email").before("<span id='error_email' class='text-danger'>Email không tồn tại.</span>");

				} else if (!result.match('email_not_exist')) {
					$("#error_email").remove();
					$("#u_email").removeClass('is-invalid');
					$("#u_email").addClass('is-valid');

					if (result.match('wrong_password')) {

						$("#u_password").addClass('is-invalid');
						$("#error_password").remove();
						$("#u_password").before("<span id='error_password'class='text-danger'>Mật khẩu không đúng.</span>");
					} else if (!result.match('wrong_password')) {
						$("#error_password").remove();
						$("#u_password").removeClass('is-invalid');
						$("#u_password").addClass('is-valid');
					}
				}

				if (result.match("not_active")) {
					$("#error_not_active").remove();
					$("#loginForm").before("<p id='error_not_active'class='text-danger'>Tài khoản chưa kích hoạt, chúng tôi đã gửi email kích hoạt tới email của bạn, vui lòng kiểm tra email.</p>");
				} else if (!result.match("not_active")) {
					$("#error_not_active").remove();
				}

				if (result.match("deleted_account")) {
					$("#error_deleted").remove();
					$("#loginForm").before("<p id='error_deleted'class='text-danger'>Tài khoản không tồn tại</p>");
				} else if (!result.match("deleted_account")) {
					$("#error_deleted").remove();
				}


			},
			error: function() {
				alert("server đang bị lỗi!");
			}
		})

	}

})