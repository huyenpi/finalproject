
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
<title>Quên mật khẩu</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/web/forgot_password.js'/>"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->

	<h5 class="text-center mt-3">QUÊN MẬT KHẨU</h5>
	<!-- form login -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card shadow-lg">

					<div class="card-body">
						<p>Chúng tôi sẽ gửi mật khẩu mới tới email của bạn. Vui lòng
							nhập email.</p>
						<form action="<c:url value='/quen-mat-khau'/>" id="formSubmit"
							method="post" myContextPath="${pageContext.request.contextPath}">
							<div class=" mb-3">
								<input type="text" class="form-control" id="u_email"
									name="u_email" placeholder="Email" autofocus>
							</div>
							<div class="d-grid">
								<button class="btn btn-primary" id="btnSubmit" type="submit">Gửi
									mật khẩu</button>
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