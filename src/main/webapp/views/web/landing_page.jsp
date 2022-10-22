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
<title>Quỹ Từ Thiện Ban Mai</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='js/admin/manage_campaign.js'/>"></script>

<style>
.title {
	display: block;
	width: 100%;
	height: 120px;
	overflow: hidden;
}
</style>

</head>

<body class="bg-light">

	<c:import url="header.jsp"></c:import>

	<div class="container px-5">
		<h2 class="text-center mb-5">CÁC CHIẾN DỊCH QUYÊN GÓP</h2>
		<div class="row g-4 px-5">

			<c:if test="${empty campaigns}">
				<p class="text-center fw-bold">Không tìm thấy chiến dịch phù hợp.</p>
			</c:if>
			<c:forEach var="c" items="${campaigns}">
				<div class="col-md-4">
					<div class="border rounded shadow">
						<a href="<c:url value='/cau-chuyen?c_id=${c.c_id}'/>"> <img
							src="<c:url value='/pictures/campaign/${c.c_picture}'/>"
							class="img-fluid" alt="..."></a>
						<div class="p-4">
							<a href="<c:url value='/cau-chuyen?c_id=${c.c_id}'/>"
								class="text-decoration-none text-dark"><h5 class="title">${c.c_name}</h5></a>

							<p class="text-primary">${c.charity_fund}</p>
							<p class="fw-bold text-success">
								Đã góp được: <span class="text-danger"><fmt:formatNumber
										value="${c.c_amount}" type="number" />đ</span>
							</p>
							<p class="fw-bold ">
								Cần huy động: <span class="text-danger"><fmt:formatNumber
										value="${c.c_goal}" type="number" />đ</span>
							</p>
							<a
								href="<c:url value='/thuc-hien-quyen-gop?target=make_donation&c_id=${c.c_id}'/>"
								class="btn btn-danger">QUYÊN GÓP</a>
						</div>


					</div>
				</div>

			</c:forEach>

		</div>
	</div>

	<!-- Pagination -->
	<div class="d-flex justify-content-center my-5">

		<div class="mb-5">


			<c:if test="${param.index > 1}">
				<a
					href="<c:url value='/quy-tu-thien-ban-mai?index=${param.index-1}&search=${search}'/>"
					class="btn btn-sm border text-primary mx-1"><<</a>

			</c:if>
		</div>
		<div class="d-flex mb-5">
			<c:forEach begin="1" end="${endPage}" var="i">
				<div>


					<a
						href="<c:url value='/quy-tu-thien-ban-mai?index=${i}&search=${search}'/>"
						<c:if test="${param.index == i}">class="btn btn-sm border mx-1 text-light bg-primary"</c:if>
						<c:if test="${param.index != i}">class="btn btn-sm border mx-1 text-primary bg-light"</c:if>>${i}
					</a>
				</div>
			</c:forEach>
		</div>
		<div class="mb-5">
			<c:if test="${param.index < endPage}">
				<a
					href="<c:url value='/quy-tu-thien-ban-mai?index=${param.index+1}&search=${search}'/>"
					class="btn btn-sm border mx-1 text-primary">>> </a>
			</c:if>
		</div>
	</div>
	<!-- Pagination -->

	<c:import url="footer.jsp"></c:import>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>