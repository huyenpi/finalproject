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
<title>Vui lòng đăng nhập</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/user/check_login.js'/>"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->

	<!-- registered_notification -->
	<div class="container mt-5 ">
		<div class="row justify-content-center">
			<div class="col-md-5">
				<div class="p-3 border border-success  mb-3">
					<form id="formSubmit"
						action="<c:url value='/thuc-hien-quyen-gop'/>">
						<input type="hidden" name="target" value="make_anonymous_donation">
						<input type="hidden" name="c_id" value="${param.c_id}">
						<p class="fw-bold">Quyên góp ngay:</p>

						<label class="form-label" for="u_email">Vui lòng nhập
							email của bạn:</label> <input class="form-control" type="text"
							id="u_email" name="u_email"> <input
							class="btn btn-sm btn-primary mt-2" id="btnSubmit" type="submit"
							value="Tiếp tục">

					</form>

				</div>
				<div class="p-3 border border-success">
					<p>
						Hoặc vui lòng <a href="<c:url value='/dang-nhap'/>"
							class="text-decoration-none">Đăng nhập</a> / <a
							href="<c:url value='/dang-ky'/>" class="text-decoration-none">Đăng
							ký</a> tài khoản để thực hiện quyên góp cho chiến dịch!
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- registered_notification -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>