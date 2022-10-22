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
<title>Quản lí người dùng</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/admin/manage_user.js'/>"></script>


</head>

<body class="bg-light">

	<c:import url="header.jsp"></c:import>

	<!-- Thông báo kết quả xóa-sửa chiến dịch -->
	<div class="container mt-3">
		<c:if test="${result == 'empt' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Chưa có người dùng nào được chọn!</strong>
			</div>
		</c:if>

		<c:if test="${result == 'delete_success' }">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Xóa người dùng thành công!</strong>
			</div>
		</c:if>
		<c:if test="${result == 'delete_failure' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Xóa người dùng thất bại!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'create_success' }">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Tạo người dùng thành công!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'create_failure' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Tạo người dùng thất bại!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'edit_success' }">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Cập nhật người dùng thành công!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'edit_failure' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Cập nhật người dùng thất bại!</strong>
			</div>
		</c:if>

	</div>
	<!-- Thông báo kết quả xóa-sửa chiến dịch -->

	<!-- Tiêu đề màn hình -->
	<div class="mt-md-3">
		<h3 class="text-center">QUẢN LÍ NGƯỜI DÙNG</h3>
	</div>
	<!--  Tiêu đề màn hình -->

	<!-- searchbox/create,delete button-->
	<div class="container-fluid px-md-2 mt-md-5">
		<div class="row my-3">
			<div class="col-md-8 my-3 my-md-0">
				<div class="container">
					<div class="d-flex justify-content-start">
						<div class="pe-3">
							<div class="input-group">
								<a href="<c:url value='/tao-nguoi-dung-moi?action=create'/>"
									class="btn btn-sm btn-success"> <!-- Create Icon --> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-plus-circle"
										viewBox="0 0 16 16">
                                        <path
											d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                        <path
											d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                                    </svg> <span>Thêm người dùng mới</span>
								</a>
							</div>
						</div>
						<div>
							<form action="<c:url value='/xoa-nguoi-dung?action=deleteMany'/>"
								method="post" id="form1">
								<input type="hidden" name="action" value="deleteMany" />
							</form>
							<div class="input-group">
								<button type="submit" id="btnDeleteMany"
									class="btn btn-sm btn-danger" form="form1">
									<!-- Delete Icon -->
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                                        <path
											d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                        <path fill-rule="evenodd"
											d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                    </svg>
									<span>Xóa người dùng</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- search box -->
			<div class="col-md-4">
				<form action="<c:url value='/quan-li-nguoi-dung'/>" method="post"
					class="d-flex" role="search">
					<div class="input-group">
						<input class="form-control form-control-sm" type="text"
							name="name_search" placeholder="Tìm theo tên...">
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
		</div>
	</div>
	<!-- searchbox/create,delete button-->

	<!-- bảng danh sách người dùng-->
	<div class="container-fluid mt-md-3 px-md-2">
		<div class="row justify-content-center">
			<div class="card shadow-sm border-0 mb-3">
				<table id="tableC"
					class="table table-sm small table-bordered table  align-middle justify-content-center mb-0">
					<tr class="table-secondary">
						<th>
							<div class="form-check">
								<input type="checkbox" id="select_all" class="form-check-input">
							</div>
						</th>
						<th>Tên người dùng</th>
						<th>Email</th>
						<th>Số điện thoại</th>
						<th>Địa chỉ</th>
						<th>Vai trò</th>
						<th>Trạng thái</th>
						<th>Thao tác</th>

					</tr>
					<c:forEach var="u" items="${users}" varStatus="status">
						<tr>
							<td>
								<div class="form-check">
									<input type="checkbox"
										class="checkbox form-check-input u_check" name="u_check"
										value="${u.u_id}" form="form1">
								</div>
							</td>
							<td>${u.u_name}</td>
							<td>${u.u_email}</td>
							<td>${u.u_phone}</td>
							<td>${u.u_address}</td>
							<td><c:if test="${u.u_role == 'false'}">User</c:if> <c:if
									test="${u.u_role == 'true'}">Admin</c:if></td>
							<td
								<c:if test="${u.u_status == 'active'}">class="text-success"</c:if>
								<c:if test="${u.u_status == 'inactive'}">class="text-danger"</c:if>
								<c:if test="${u.u_status == 'deleted'}">class="text-secondary"</c:if>>
								${u.u_status}</td>
							<td>
								<div class="d-flex">

									<div>

										<a
											href="<c:url value='/chinh-sua-thong-tin-nguoi-dung?action=edit&u_id=${u.u_id}'/>"
											class="btn
											btn-sm btn-warning"
											title="Chỉnh sửa thông tin người dùng"> <!-- icon edit -->
											<svg xmlns="http://www.w3.org/2000/svg" width="16"
												height="16" fill="currentColor" class="bi bi-pencil-square"
												viewBox="0 0 16 16">
                                            <path
													d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z" />
                                            <path fill-rule="evenodd"
													d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z" />
                                        </svg> <!-- icon edit -->
										</a>

									</div>



									<div>
										<form action="<c:url value='/xoa-nguoi-dung?'/>" method="post">

											<input type="hidden" name="action" value="deleteOne" /> <input
												type="hidden" name="u_id" value="${u.u_id}" />
											<button class="btn btn-sm btn-danger mx-2 btnDeleteOne"
												title="Xóa người dùng"
												<c:if test="${u.u_role == 'true' }">disabled</c:if>>
												<!--  icon delete -->
												<svg xmlns="http://www.w3.org/2000/svg" width="16"
													height="16" fill="currentColor" class="bi bi-x-square-fill"
													viewBox="0 0 16 16">
  <path
														d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2H2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708z" />
</svg>
												<!--  icon delete -->
											</button>
										</form>
									</div>


								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- UserList -->

	<!-- Pagination -->
	<div class="d-flex justify-content-center">

		<div>
			<form
				action="<c:url value='/quan-li-nguoi-dung?index=${param.index-1}&$name_search=${text_search}'/>"
				method="post">

				<c:if test="${param.index > 1}">
					<button type="submit" class="btn btn-sm border text-primary mx-1"><<</button>
			</form>
			</c:if>
		</div>
		<div class="d-flex">
			<c:forEach begin="1" end="${endPage}" var="i">
				<div>
					<form
						action="<c:url value='/quan-li-nguoi-dung?index=${i}&name_search=${text_search}'/>"
						method="post">

						<button type="submit"
							<c:if test="${param.index == i}">class="btn btn-sm border mx-1 text-light bg-primary"</c:if>
							<c:if test="${param.index != i}">class="btn btn-sm border mx-1 text-primary bg-light"</c:if>>${i}
						</button>
					</form>
				</div>
			</c:forEach>
		</div>
		<div>
			<c:if test="${param.index < endPage}">
				<form
					action="<c:url value='/quan-li-nguoi-dung?index=${param.index+1}&name_search=${text_search}'/>"
					method="post">

					<button type="submit" class="btn btn-sm border mx-1 text-primary">>></button>
				</form>
			</c:if>
		</div>
	</div>
	<!-- Pagination -->
	<c:import url="footer.jsp"></c:import>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>