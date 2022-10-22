$(document).ready(function() {

	$("#btnCreate").click(function() {
		$("#action").val("create");

	});

	$("#btnEdit").click(function() {
		$("#action").val("edit");
	});

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

		if ($("#u_password").val() == '') {
			$("#u_password").addClass('is-invalid');
			return false;
		} else if ($("#u_password").val()) {
			if ($("#u_password").val().length < 6) {
				$("#u_password").addClass('is-invalid');
				$("#error_email").remove();
				$("#u_password").before("<span id='error_password' class='text-danger'>Mật khẩu gồm ít nhất 6 kí tự.</span>");
				return false;
			} else {
				$("#error_password").remove();
				$("#u_password").removeClass('is-invalid');
				$("#u_password").addClass('is-valid');
			}
		}
		if ($("#u_repassword").val() == '') {
			$("#u_repassword").addClass('is-invalid');
			return false;
		} else if ($("#u_repassword").val()) {
			$("#u_repassword").removeClass('is-invalid');
			$("#u_repassword").addClass('is-valid');
		}

		if ($("#u_password").val() != $("#u_repassword").val()) {
			$("#u_repassword").addClass('is-invalid');
			return false;
		} else if ($("#u_password").val() == $("#u_repassword").val()) {
			$("#u_repassword").removeClass('is-invalid');
			$("#u_repassword").addClass('is-valid');
		}

		return true;

	}

	function sendForm() {
		var formData = new FormData(document.getElementById("formSubmit"));
		var action = $("#action").val();
		formData.set("action", action);

		if (validate()) {

			$.ajax({
				url: "chinh-sua-thong-tin-nguoi-dung",
				type: "POST",
				data: formData,
				contentType: false,
				cache: false,
				processData: false,
				success: function(result) {
					if (result.match('create_success') || result.match('edit_success') || result.match('create_failure') || result.match('edit_failure')) {

						var contextPath = $("#formSubmit").attr('myContextPath');
						window.location.replace(contextPath + "/quan-li-nguoi-dung?result=" + result);
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