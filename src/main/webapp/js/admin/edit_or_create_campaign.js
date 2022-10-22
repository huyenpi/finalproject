$(document).ready(function() {

	CKEDITOR.replace('c_details');

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

		var c_details = CKEDITOR.instances['c_details'].getData();

		if ($("#c_name").val() == '') {
			$("#c_name").addClass('is-invalid');
			return false;

		} else if ($("#c_name").val()) {
			$("#c_name").removeClass('is-invalid');
			$("#c_name").addClass('is-valid');
		}

		if ($("#charity_fund").val() == '') {
			$("#charity_fund").addClass('is-invalid');
			return false;
		} else if ($("#charity_fund").val()) {
			$("#charity_fund").removeClass('is-invalid');
			$("#charity_fund").addClass('is-valid');
		}
		if ($("#c_picture").val() == '') {
			$("#c_picture").addClass('is-invalid');
			return false;
		} else if ($("#c_picture").val()) {
			$("#c_picture").removeClass('is-invalid');
			$("#c_picture").addClass('is-valid');
		}
		if ($("#c_goal").val() == '') {
			$("#c_goal").addClass('is-invalid');
			return false;
		} else if ($("#c_goal").val()) {
			$("#c_goal").removeClass('is-invalid');
			$("#c_goal").addClass('is-valid');
		}


		return true;

	}



	function sendForm() {
		var formData = new FormData(document.getElementById("formSubmit"));
		var action = $("#action").val();
		var c_details = CKEDITOR.instances['c_details'].getData();
		formData.set("c_details", c_details);
		formData.set("action", action);

		if (validate()) {

			$.ajax({
				url: "chinh-sua-chien-dich",
				type: "POST",
				data: formData,
				contentType: false,
				cache: false,
				processData: false,
				success: function(result) {

					if (result.match('create_success') || result.match('edit_success') || result.match('create_failure') || result.match('edit_failure')) {
						var contextPath = $("#formSubmit").attr('myContextPath');
						window.location.replace(contextPath + "/quan-li-chien-dich?result=" + result);
					} else {

						alert(result);
					}
				},
				error: function() {
					alert("server đang bị lỗi!");
				}
			})
		}
	}

})