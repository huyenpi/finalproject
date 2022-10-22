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
						Chỉnh sửa chiến dịch
					</c:if> <c:if test="${action != 'edit'}">
						Tạo chiến dịch mới
					</c:if></title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/libraries/ckeditor/ckeditor.js'/>"></script>
<script src="<c:url value='/js/admin/edit_or_create_campaign.js'/>"></script>
</head>

<body class="bg-light">
	<c:import url="header.jsp"></c:import>

	<c:if test="${action == 'edit'}">
		<div class="mt-md-3">
			<h3 class="text-center">Chỉnh sửa chiến dịch</h3>
		</div>
	</c:if>
	<c:if test="${action != 'edit'}">
		<div class="mt-md-3">
			<h3 class="text-center">Tạo chiến dịch mới</h3>
		</div>
	</c:if>

	<div class="container mt-5 mb-5">
		<form action="<c:url value='/tao-chien-dich-moi'/>" id="formSubmit"
			enctype='multipart/form-data' method="post"
			myContextPath="${pageContext.request.contextPath}">
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="c_name">Tên
					chiến dịch <span class="text-danger">*</span>
				</label>
				<div class="col-md-10">
					<input type="text" class="form-control form-control-sm" id="c_name"
						name="c_name" value="${c.c_name}">
				</div>
			</div>
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="charity_fund">Qũy
					từ thiện <span class="text-danger">*</span>
				</label>
				<div class="col-md-10">
					<input type="text" class="form-control form-control-sm"
						id="charity_fund" name="charity_fund" value="${c.charity_fund}">
				</div>
			</div>
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="c_picture">Ảnh
					đại diện <span class="text-danger">*</span>
				</label>
				<div class="col-md-4">

					<input type="file" class="form-control form-control-sm"
						id="c_picture" name="c_picture" value="${c.c_picture}">


				</div>

			</div>

			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="c_goal">Số tiền
					cần quyên góp <span class="text-danger">*</span>
				</label>
				<div class="col-md-2">
					<fmt:formatNumber value='${c.c_goal}' type='number' var="c_goal"
						groupingUsed="false" />
					<input type="number" min="0" class="form-control form-control-sm"
						id="c_goal" name="c_goal" value="${c_goal}" />
				</div>
			</div>
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="c_goal">Trạng
					thái <span class="text-danger">*</span>
				</label>
				<div class="col-md-2">
					<select name="c_status" class="form-select form-select-sm">
						<option value="active"
							<c:if test="${text=='active'}"> selected</c:if>>active</option>
						<option value="inactive"
							<c:if test="${text =='inactive'}"> selected</c:if>>inactive</option>
						<option value="completed"
							<c:if test="${text =='completed'}"> selected</c:if>>completed</option>
					</select>

				</div>
			</div>
			<div class="row mb-2">
				<label class="col-md-2 col-form-label" for="c_details">Câu
					chuyện <span class="text-danger"></span>
				</label>
				<div class="col-md-10">
					<textarea class="form-control form-control-sm" id="c_details"
						name="c_details" cols="30" rows="10" value="${c.c_details}"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="d-flex justify-content-center">
					<input type="hidden" name="c_id" value="${c.c_id}" /> <input
						type="hidden" id="action" name="action" value="" />

					<c:if test="${action == 'edit'}">
						<div>
							<input type="submit" value="Lưu" id="btnEdit" name="btnName"
								class="btn btn-primary btn-sm m-1 p-1">
						</div>
					</c:if>

					<c:if test="${action != 'edit'}">
						<div class="mb-5">
							<input type="submit" value="Tạo" id="btnCreate" name="btnName"
								class="btn btn-primary btn-sm m-1 p-1">
						</div>
					</c:if>
					<div class="mb-5">
						<a href="<c:url value='/quan-li-chien-dich'/>" id="btnCancel"
							name="cancel" class="btn btn-secondary btn-sm m-1 p-1">Hủy </a>
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