
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
<title>Đăng nhập</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value='/css/login.css'/>">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/web/login.js'/>"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->

	<h5 class="text-center mt-3">ĐĂNG NHẬP</h5>




	<!-- QUEN MAT KHAU, change_password_success -->
	<div
		class="container mt-3 <c:if test="${param.change_password != 'change_password'}">d-none</c:if>">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<p class="p-3">Chúng tôi đã gửi mật khẩu tới email của bạn. Vui
					lòng kiểm tra email và đăng nhập. Xin cảm ơn!</p>
			</div>
		</div>
	</div>
	<!-- change_password_success -->

	<!-- form login -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div id="loginForm" class="shadow-lg">

					<div class="">
						<form action="<c:url value='/dang-nhap'/>" id="formSubmit"
							method="post" myContextPath="${pageContext.request.contextPath}">
							<div class=" mb-3">
								<input type="text" class="form-control" id="u_email"
									name="u_email" placeholder="Email" value="${param.u_email}"
									autofocus>
							</div>
							<div class=" mb-3">
								<input type="password" class="form-control" id="u_password"
									name="u_password" placeholder="Mật khẩu" autofocus> <a
									href="<c:url value='/quen-mat-khau'/>"
									class="small text-decoration-none px-2">Quên mật khẩu?</a>
							</div>
							<div class="form-check mb-3">
								<input class="form-check-input" type="checkbox" value=""
									id="remember_me" name="remember_me"> <label
									class="form-check-label" for="remember_me"> Nhớ Tài
									Khoản </label>
							</div>
							<div class="d-grid">
								<button class="btn btn-primary" type="submit">Đăng nhập</button>

							</div>

						</form>
						<!-- loader -->
						<div class="d-flex justify-content-center">
							<div id="loader" class="my-3"></div>
						</div>
						<!-- loader -->
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