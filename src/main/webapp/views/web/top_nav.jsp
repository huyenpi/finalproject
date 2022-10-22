<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Toơnav-->
<div id="topNav" class="position-sticky top-0">
	<nav class="navbar navbar-expand-lg bg-light small px-5">
		<div class="px-2 me-auto">
			<a class="navbar-brand" href="<c:url value='/quy-tu-thien-ban-mai?page=home'/>"><img
				style="width: 15%" class="rounded-circle"
				src="<c:url value='/pictures/web/logo.png'/>" /> <span
				class="text-primary fw-bold"
				style="background: #4F19CF; background: linear-gradient(to top, #4F19CF 0%, #04ADCF 56%); -webkit-background-clip: text; -webkit-text-fill-color: transparent;">
					QUỸ TỪ THIỆN BAN MAI</span> </a>
		</div>

		<c:if test='${check_login == null}'>
			<a href="<c:url value='/dang-nhap'/>"
				class="text-info fw-bold text-decoration-none">Đăng Nhập | </a>
			<a href="<c:url value='/dang-ky'/>"
				class="text-info fw-bold text-decoration-none pe-5">Đăng Ký </a>
		</c:if>

		<c:if test='${check_login == "logged"}'>
			<span class="pe-5 fw-bold">Xin chào ${u_name}!</span>
			<div class="dropdown">
				<a class="btn btn-light btn-sm text-info fw-bold dropdown-toggle"
					href="<c:url value='/trang-ca-nhan'/>" role="button"
					id="dropdownMenuLink" data-bs-toggle="dropdown"
					aria-expanded="false"> Cài đặt </a>

				<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
					<li><a class="dropdown-item"
						href="<c:url value='/thong-tin-ca-nhan'/>">Thông tin cá nhân</a></li>
					<li><a class="dropdown-item"
						href="<c:url value='/thay-doi-mat-khau'/>">Đổi mật khẩu</a></li>
					<li><a class="dropdown-item"
						href="<c:url value='/lich-su-quyen-gop?target=donation_history'/>">Lịch
							sử quyên góp</a></li>
					<c:if test="${u_id == 1}">
						<li><a class="dropdown-item"
							href="<c:url value='/trang-quan-tri'/>">Trang quản trị</a></li>
					</c:if>
				</ul>
			</div>


			<a href="<c:url value='/dang-xuat'/>"
				class="text-info fw-bold text-decoration-none pe-5">| Đăng xuất
			</a>
		</c:if>



	</nav>
	<nav id="menu" class="navbar navbar-expand-lg bg-info px-5">

		<ul class="navbar-nav mb-2 mb-lg-0 me-auto">
			<li class="nav-item px-3"><a class="nav-link fw-bold text-light"
				href="<c:url value='/quy-tu-thien-ban-mai?page=home'/>">Trang chủ</a></li>
			<li class="nav-item px-3"><a class="nav-link fw-bold"
				href="<c:url value='/quy-tu-thien-ban-mai?page=introduce'/>">Giới thiệu</a></li>
			<li class="nav-item px-3"><a class="nav-link fw-bold"
				href="<c:url value='/quy-tu-thien-ban-mai?page=contact'/>">Liên hệ</a></li>
		</ul>

		<!-- search box -->
		<div class="">
			<form action="<c:url value='/quy-tu-thien-ban-mai'/>" class="d-flex"
				role="search">
				<div class="input-group">
					<input class="form-control form-control-sm" type="text"
						name="search" placeholder="Tìm chiến dịch...">
					<button type="submit" class="btn btn-sm btn-primary">
						<!-- Search Icon -->
						<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
							fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                <path
								d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z" />
                            </svg>
					</button>
				</div>
			</form>
		</div>
		<!-- search box -->
	</nav>


	<!-- Menu -->


</div>

<!-- Topnav -->