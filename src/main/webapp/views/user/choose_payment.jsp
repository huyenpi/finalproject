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
<title>Thực hiện quyên góp</title>
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

	<h5 class="text-center mt-5">THỰC HIỆN QUYÊN GÓP CHO CHIẾN DỊCH</h5>
	<!-- campaign card -->
	<div class="container my-5">

		<div class="row">
			<div class="col-3"></div>
			<div class="col-4">
				<h6>Chọn hình thức quyên góp:</h6>
				<form action="<c:url value='/thuc-hien-quyen-gop'/>">
					<input type="hidden" name="u_email" value="${u_email}">
					<c:if test="${u_name != null && u_id != null}">
						<input type="hidden" name="u_name" value="${u_name}">
						<input type="hidden" name="u_id" value="${u_id}">
					</c:if>
					<input type="hidden" name="target" value="choose_payment">
					<input type="hidden" name="c_id" value="${c.c_id}">
					<div class="form-check">
						<input class="form-check-input" type="radio" name="paymentMethod"
							id="bank" value="bank" checked> <label
							class="form-check-label" for="bank">Quyên góp qua chuyển
							khoản ngân hàng </label>
					</div>


					<div class="form-check">
						<input class="form-check-input" type="radio" name="paymentMethod"
							id="atm" value="bank" disabled="disabled"> <label
							class="form-check-label" for="atm"> Quyên góp qua thẻ ATM
						</label>
					</div>
					<div>
						<input class="btn btn-sm btn-primary mt-3" type="submit"
							value="Tiếp tục">
					</div>
				</form>
			</div>
			<div class="col-3">
				<div class="card">
					<a href="<c:url value='/cau-chuyen?c_id=${c.c_id}'/>"> <img
						src="<c:url value='/pictures/campaign/${c.c_picture}'/>"
						class="card-img-top" alt="...">
					</a>
					<div class="card-body p-4">
						<a href="<c:url value='/cau-chuyen?c_id=${c.c_id}'/>"
							class="text-decoration-none text-dark"><h5 class="card-title">${c.c_name}</h5></a>

						<p class="card-text text-primary">${c.charity_fund}</p>
						<p class="card-text fw-bold text-success">
							Đã góp được: <span class="text-danger"><fmt:formatNumber
									value="${c.c_amount}" type="number" />đ</span>
						</p>
						<p class="card-text fw-bold ">
							Cần huy động: <span class="text-danger"><fmt:formatNumber
									value="${c.c_goal}" type="number" />đ</span>
						</p>
					</div>
				</div>

			</div>

		</div>
	</div>
	<!-- form login -->
	<c:import url="footer.jsp"></c:import>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>