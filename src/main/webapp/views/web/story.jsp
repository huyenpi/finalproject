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
<title>Chiến dịch - Câu chuyện</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/web/story.js'/>"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->


	<!-- campaign card -->
	<div class="container mt-5">
		<h4 class="text-center mb-5">${c.c_name}</h4>
		<div class="row">
			<div class="col-9 px-5">

				<div id="story" class="container-fluid">
					<h5 class="mb-3">Câu chuyện</h5>
					${c.c_details}
				</div>

			</div>
			<div class="col-3">
				<div class="shadow-lg">
					<a
						href="<c:url value='/thuc-hien-quyen-gop?target=make_donation&c_id=${c.c_id}'/>">
						<img src="<c:url value='/pictures/campaign/${c.c_picture}'/>"
						class="img-fluid" alt="...">
					</a>
					<div class="p-4">
						<a
							href="<c:url value='/thuc-hien-quyen-gop?target=make_donation&c_id=${c.c_id}'/>"
							class="text-decoration-none text-dark"><h5>${c.c_name}</h5></a>

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

		</div>
	</div>
	<!-- form login -->
	<c:import url="footer.jsp"></c:import>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>