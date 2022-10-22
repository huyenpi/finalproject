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
<title><c:if test="${action == 'edit'}">
						Chỉnh sửa thông tin người dùng
					</c:if> <c:if test="${action != 'edit'}">
						Thêm người dùng mới
					</c:if></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/libraries/ckeditor/ckeditor.js'/>"></script>
<script src="<c:url value='/js/admin/edit_or_create_user.js'/>"></script>
</head>

<body class="bg-light">
	<c:import url="header.jsp"></c:import>

	<c:if test="${action == 'edit'}">
		<div class="mt-md-3">
			<h3 class="px-md-5 text-center text-md-start">Chỉnh sửa thông
				tin người dùng</h3>
		</div>
	</c:if>
	<c:if test="${action == 'create'}">
		<div class="mt-md-3">
			<h3 class="px-md-5 text-center text-md-start">Thêm người dùng
				mới</h3>
		</div>
	</c:if>

	<div class="container mt-5 px-md-5">

		<form action="<c:url value='/tao-nguoi-dung-moi'/>" id="formSubmit"
			method="post" myContextPath="${pageContext.request.contextPath}">
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="u_name">Họ và
					tên <span class="text-danger">*</span>
				</label>
				<div class="col-md-4">
					<input type="text" class="form-control form-control-sm" id="u_name"
						name="u_name" value="${u.u_name}">
				</div>
			</div>
			<c:if test="${action == 'create'}">
				<div class="row mb-2">
					<label class="col-md-2 col-form-label" for="u_email">Email
						<span class="text-danger">*</span>
					</label>
					<div class="col-md-4">
						<input type="text" class="form-control form-control-sm"
							id="u_email" name="u_email" value="${u.u_email}">
					</div>
				</div>

			</c:if>
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="u_address">Địa
					chỉ <span class="text-danger"></span>
				</label>
				<div class="col-md-4">
					<input type="text" class="form-control form-control-sm"
						id="u_address" name="u_address" value="${u.u_address}">
				</div>
			</div>
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="u_phone">Số điện
					thoại<span class="text-danger"></span>
				</label>
				<div class="col-md-2">

					<input type="number" class="form-control form-control-sm"
						id="u_phone" name="u_phone" value="${u.u_phone}" />
				</div>
			</div>
			<c:if test="${action == 'edit'}">
				<div class="row mb-2">
					<label class="col-md-2 col-form-label" for="u_role">Vai trò<span
						class="text-danger">*</span>
					</label>
					<div class="col-md-2">
						<select name="u_role" class="form-select form-select-sm">

							<option value="User"
								<c:if test="${u.u_id== 1}">class="d-none"</c:if>
								<c:if test="${u.u_role =='false'}"> selected</c:if>>User</option>

							<option value="Admin" <c:if test="${u.u_id != 1}">class="d-none"</c:if>
								<c:if test="${u.u_role=='true'}"> selected</c:if>>Admin</option>
						</select>
					</div>
				</div>


				<div class="row mb-2">
					<label class="col-md-2 col-form-label" for="u_status">Trạng
						thái<span class="text-danger">*</span>
					</label>
					<div class="col-md-2">

						<select name="u_status" class="form-select form-select-sm">
							<option value="active"
								<c:if test="${u.u_status =='active'}"> selected</c:if>>active</option>
							<option value="inactive"
								<c:if test="${u.u_id== 1}">class="d-none"</c:if>
								<c:if test="${u.u_status=='inactive'}" > selected</c:if>>inactive</option>
							<option value="deleted"
								<c:if test="${u.u_id== 1}">class="d-none"</c:if>
								<c:if test="${u.u_status=='deleted'}"> selected</c:if>>deleted</option>
						</select>
					</div>
				</div>
			</c:if>


			<div class="row">
				<div class="d-flex justify-content-start">
					<input type="hidden" name="u_id" value="${u.u_id}" /> <input
						type="hidden" id="action" name="action" value="" />

					<c:if test="${action == 'edit'}">
						<div>
							<input type="submit" value="Lưu" id="btnEdit" name="btnName"
								class="btn btn-primary btn-sm m-1 p-1">
						</div>
					</c:if>

					<c:if test="${action == 'create'}">
						<div>
							<input type="submit" value="Lưu" id="btnCreate" name="btnName"
								class="btn btn-primary btn-sm m-1 p-1">
						</div>
					</c:if>
					<div>
						<a href="<c:url value='/quan-li-nguoi-dung'/>" id="btnCancel"
							name="cancel" class="btn btn-secondary btn-sm m-1 p-1">Hủy</a>
					</div>
				</div>
			</div>
		</form>
	</div>
	<c:import url="footer.jsp"></c:import>








	<!-- bootstrap js -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>