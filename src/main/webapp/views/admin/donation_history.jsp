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
<title>Lịch sử quyên góp</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="header.jsp"></c:import>
	<!-- import top nav -->
	<h3 class="text-center mt-3">LỊCH SỬ QUYÊN GÓP</h3>
	<!-- bảng danh sách lich su quyen gop--->
	<div class="container mt-md-3 px-md-2">
		<div class="row justify-content-center">
			<div class="card shadow-sm border-0 mb-3">
				<table id="tableC"
					class="table table-sm small table-bordered table  align-middle justify-content-center mb-0">
					<tr class="table-secondary">
						<th>Ngày quyên góp</th>
						<th>Tên chiến dịch</th>
						<th>Số tiền quyên góp</th>
						<th>Lời nhắn</th>
					</tr>
					<c:forEach var="d" items="${donations}" varStatus="status">
						<tr>
							<td>${d.d_date}</td>
							<td>${d.c_name}</td>
							<td><fmt:formatNumber value="${d.d_amount}" type="number" /></td>
							<td>${d.d_notes}</td>

						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- Donations List -->
	<!-- Pagination -->
	<div class="d-flex justify-content-center my-5">

		<div class="mb-5">


			<c:if test="${param.index > 1}">
				<a
					href="<c:url value='/quan-li-lich-su-quyen-gop?index=${param.index-1}'/>"
					class="btn btn-sm border text-primary mx-1"><<</a>

			</c:if>
		</div>
		<div class="d-flex mb-5">
			<c:forEach begin="1" end="${endPage}" var="i">
				<div>


					<a
						href="<c:url value='/quan-li-lich-su-quyen-gop?index=${i}'/>"
						<c:if test="${param.index == i}">class="btn btn-sm border mx-1 text-light bg-primary"</c:if>
						<c:if test="${param.index != i}">class="btn btn-sm border mx-1 text-primary bg-light"</c:if>>${i}
					</a>
				</div>
			</c:forEach>
		</div>
		<div class="mb-5">
			<c:if test="${param.index < endPage}">
				<a
					href="<c:url value='/quan-li-lich-su-quyen-gop?index=${param.index+1}'/>"
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