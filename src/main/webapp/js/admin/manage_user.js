$(document)
	.ready(
		function() {

			//checked,unchecked all
			$('#select_all').click(function(event) { //on click
				if (this.checked) { // check select status
					$('.checkbox').each(function() { //loop through each checkbox
						this.checked = true; //select all checkboxes with class "checkbox"            
					});

				} else {
					$('.checkbox').each(function() { //loop through each checkbox
						this.checked = false; //deselect all checkboxes with class "checkbox"                    
					});
				}

			});

			
			$('.btnDeleteOne')
				.click(
					function(event) {
						return confirm('Chú ý: Tất cả các thông tin liên quan của người dùng sẽ bị xóa vĩnh viễn. Tiếp tục xóa?');
					});
					
			
			$('#btnDeleteMany')
				.click(
					function(event) {

						var count = 0;
						$('.u_check').each(function() {
							if (this.checked) {
								count++;
							}
						});
						if (count == 0) {

						} else {
							return confirm('Chú ý: Tất cả các thông tin liên quan của người dùng sẽ bị xóa vĩnh viễn. Tiếp tục xóa?');
						}

					});

		});