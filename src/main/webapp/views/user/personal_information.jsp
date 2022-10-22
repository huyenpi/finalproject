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
<title>Thông tin cá nhân</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/user/personal_information.js'/>"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->

	<h5 id="title" class="text-center mt-3">THÔNG TIN CÁ NHÂN</h5>

	<!-- thong bao ket qua cap nhat -->
	<div class="row">
		<div class="col-4"></div>
		<div class="col-4 mt-3">
			<c:if test="${param.result == 'update_success' }">
				<div class="alert alert-success alert-dismissible">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<strong>Cập nhật thành công!</strong>
				</div>

			</c:if>
			<c:if test="${param.result == 'update_failure' }">
				<div class="alert alert-danger alert-dismissible">
					<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					<strong>Cập nhật thất bại!</strong>
				</div>
			</c:if>
		</div>
		<div class="col-4"></div>
	</div>
	<!-- thong bao ket qua cap nhat -->
	<!-- campaign card -->
	<div class="container mt-2 mb-5">

		<div class="row">
			<div class="d-flex justify-content-center">
				<div class="col-4"></div>
				<div class="col-4">
					<form action="<c:url value='/cap-nhat-thong-tin-ca-nhan'/>"
						id="formSubmit" method="post"
						myContextPath="${pageContext.request.contextPath}">

						<input type="hidden" name="u_id" value="${u_id}"> <input
							type="hidden" name="u_role" value="${u.u_role}">
						<div class="row mb-2">
							<label class="col-md-4 col-form-label" for="u_name">Họ và
								tên <span class="text-danger">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control form-control-sm"
									id="u_name" name="u_name" value="${u.u_name}">
							</div>
						</div>

						<div class="row mb-2">
							<label class="col-md-4 col-form-label" for="u_email">Email
								<span class="text-danger">*</span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control form-control-sm"
									id="u_email" name="u_email" value="${u.u_email}"
									readonly="readonly">
							</div>
						</div>

						<div class="row mb-2">
							<label class="col-md-4 col-form-label" for="u_address">Địa
								chỉ <span class="text-danger"></span>
							</label>
							<div class="col-md-8">
								<input type="text" class="form-control form-control-sm"
									id="u_address" name="u_address" value="${u.u_address}">
							</div>
						</div>

						<div class="row mb-2">
							<label class="col-md-4 col-form-label" for="u_phone">Số
								điện thoại<span class="text-danger"></span>
							</label>
							<div class="col-md-8">

								<input type="number" class="form-control form-control-sm"
									id="u_phone" name="u_phone" value="${u.u_phone}" />
							</div>
						</div>

						<div class="row mb-2">
							<label class="col-md-4 col-form-label" for="u_status">Trạng
								thái<span class="text-danger">*</span>
							</label>
							<div class="col-md-3">
								<input type="text" class="form-control form-control-sm"
									id="u_status" name="u_status" value="${u.u_status}"
									readonly="readonly" />
							</div>
						</div>

						<div class="d-flex justify-content-center">
							<button class="btn btn-sm btn-primary mt-3" type="submit">Lưu
								thay đổi</button>
						</div>

					</form>
				</div>
				<div class="col-4"></div>
			</div>
		</div>
	</div>
	<!-- form login -->


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>