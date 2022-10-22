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
<title>Đăng ký</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value='/css/register.css'/>">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/web/register.js'/>"></script>

</head>

<body class="bg-light">

	<c:import url="top_nav.jsp"></c:import>
	<h5 class="text-center mt-3">ĐĂNG KÝ</h5>



	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card shadow-lg">

					<div class="card-body">
						<form action="<c:url value='/dang-ky'/>" id="formSubmit"
							method="post" myContextPath="${pageContext.request.contextPath}">
							<div class="mb-3">
								<label for="u_name">Họ tên<span class="text-danger">*</span></label>
								<input type="text" class="form-control" id="u_name"
									name="u_name" autofocus>
							</div>
							<div class="mb-3">
								<label for="u_email">Email<span class="text-danger">*</span></label>
								<input type="text" class="form-control" id="u_email"
									name="u_email" autofocus>
							</div>
							<div class="mb-3">
								<label for="u_address">Địa chỉ</label> <input type="text"
									class="form-control" id="u_address" name="u_address" autofocus>
							</div>
							<div class="mb-3">
								<label for="u_phone">Số điện thoại</label> <input type="text"
									class="form-control" id="u_phone" name="u_phone" autofocus>
							</div>

							<div class="d-grid">
								<button class="btn btn-primary" type="submit">Đăng Ký</button>
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>