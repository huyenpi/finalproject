<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Toơnav-->
<nav id="topNav"
	class="navbar navbar-dark navbar-expand-lg bg-primary shadow small mb-3 px-3 position-sticky top-0">
	<div class="px-2 me-auto">
		<a class="navbar-brand" href="/trang-quan-tri"> <span
			class="text-warning fw-bold">Trang quản trị</span>
		</a>
	</div>
	<c:if test='${check_login == null}'>
		<a href="<c:url value='/dang-nhap'/>"
			class="text-info fw-bold text-decoration-none">Đăng Nhập | </a>
		<a href="<c:url value='/dang-ky'/>"
			class="text-info fw-bold text-decoration-none pe-5">Đăng Ký </a>
	</c:if>

	<c:if test='${check_login == "logged"}'>
		<span class="pe-5 fw-bold text-light">Xin chào ${u_name}!</span>

		<a href="<c:url value='/quy-tu-thien-ban-mai'/>"
			class="text-warning fw-bold text-decoration-none">Trang người
			dùng </a>
		<a href="<c:url value='/dang-xuat'/>"
			class="text-warning fw-bold text-decoration-none pe-5">| Đăng
			xuất </a>
	</c:if>


</nav>
<!-- Topnav -->