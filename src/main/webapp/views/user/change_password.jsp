<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thay đổi mật khẩu</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/user/change_password.js'/>"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->

	<h5 class="text-center mt-3">THAY ĐỔI MẬT KHẨU</h5>
	<!-- form login -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card shadow-lg">

					<div class="card-body">
						<form action="<c:url value='/thay-doi-mat-khau'/>" id="formSubmit"
							method="post" myContextPath="${pageContext.request.contextPath}">
							<div class=" mb-3">
								<label for="u_password">Mật khẩu hiện tại:</label> <input
									type="password" class="form-control" id="u_password"
									name="u_password" autofocus>
							</div>
							<div class=" mb-3">
								<label for="new_password">Mật khẩu mới:</label> <input
									type="password" class="form-control" id="new_password"
									name="new_password" autofocus>
							</div>
							<div class=" mb-3">
								<label for="re_new_password">Xác nhận lại mật khẩu:</label> <input
									type="password" class="form-control" id="re_new_password"
									name="re_new_password" autofocus>
							</div>

							<div class="d-grid">
								<button class="btn btn-primary" id="btnSubmit" type="submit">Gửi
									yêu cầu</button>

							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- form login -->
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>