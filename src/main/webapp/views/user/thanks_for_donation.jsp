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
<title>Lời cảm ơn</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->

	<!-- registered_notification -->
	<div class="container mt-3 ">
		<div class="row justify-content-center">
			<!-- thonong báo dành cho người dùng có tài khoản -->
			<c:if test="${check_login == 'logged' }">
				<div class="col-md-4 border border-success p-3">
					<p class="">
						Xin cảm ơn bạn đã quyên góp cho chiến dịch! Nếu sau vài giờ lượt
						quyên góp của bạn chưa được cập nhật trong lịch sử quyên góp, vui
						lòng gọi đến: <span class="fw-bold">1900 800 </span>để được hỗ
						trợ. Xin cảm ơn!
					</p>
					<a class="text-decoration-none"
						href="<c:url value='/lich-su-quyen-gop?target=donation_history'/>">Kiểm
						tra lịch sử quyên góp</a>
				</div>
			</c:if>
			<!--  -->
			<!-- thông báo dành cho quyên góp ẩn danh -->
			<c:if test="${check_login != 'logged' }">
				<div class="col-md-4 border border-success p-3">
					<p class="">
						Xin cảm ơn bạn đã quyên góp cho chiến dịch! Mọi thắc mắc vui lòng
						gọi đến: <span class="fw-bold">1900 800 </span>để được hỗ trợ. Xin
						cảm ơn!
					</p>

				</div>
			</c:if>
			<!--  -->
		</div>
	</div>
	<!-- registered_notification -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>