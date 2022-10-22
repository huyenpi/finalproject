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
<title>Trang chủ</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>

<body class="bg-light">

	<c:import url="/views/admin/topnav.jsp"></c:import>
	<div class="row">
		<div class="d-md-flex" id="wrapper">
			<!-- sidebar -->
			<div id="sidebar" class="col-md-2">
				<c:import url="views/admin/sidebar.jsp"></c:import>
			</div>
			<!-- sidebar -->

			<!-- content -->
			<div id="content" class="col-md-10 px-3">
				<p>Quỹ Từ Thiện Ban Mai xin chào quản trị viên!</p>
			</div>
			<!-- content -->
		</div>
	</div>
	<c:import url="/views/admin/footer.jsp"></c:import>





	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>