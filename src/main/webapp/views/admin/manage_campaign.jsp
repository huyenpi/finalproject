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
<title>Quản lí chiến dịch</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/admin/manage_campaign.js'/>"></script>

</head>

<body class="bg-light">

	<c:import url="header.jsp"></c:import>

	<!-- Thông báo kết quả xóa-sửa chiến dịch -->
	<div class="container mt-3">
		<c:if test="${result == 'empt' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Chưa có chiến dịch nào được chọn!</strong>
			</div>
		</c:if>

		<c:if test="${result == 'delete_success' }">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Xóa chiến dịch thành công!</strong>
			</div>
		</c:if>
		<c:if test="${result == 'delete_failure' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Xóa chiến dịch thất bại!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'create_success' }">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Tạo chiến dịch thành công!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'create_failure' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Tạo chiến dịch thất bại!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'edit_success' }">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Cập nhật chiến dịch thành công!</strong>
			</div>
		</c:if>
		<c:if test="${param.result == 'edit_failure' }">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<strong>Cập nhật chiến dịch thất bại!</strong>
			</div>
		</c:if>

	</div>
	<!-- Thông báo kết quả xóa-sửa chiến dịch -->

	<!-- Tiêu đề màn hình -->
	<div class="mt-md-3">
		<h3 class="text-center">QUẢN LÍ CHIẾN DỊCH QUYÊN GÓP</h3>
	</div>
	<!-- Tiêu đề màn hình -->

	<!-- searchbox/create,delete button-->
	<div class="container-fluid px-md-2 mt-md-5">
		<div class="row my-3">
			<div class="col-md-8 my-3 my-md-0">
				<div class="container">
					<div class="d-flex justify-content-start">
						<div class="pe-3">
							<div class="input-group">
								<a href="<c:url value='/tao-chien-dich-moi'/>"
									class="btn btn-sm btn-success"> <!-- Create Icon --> <svg
										xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-plus-circle"
										viewBox="0 0 16 16">
                                        <path
											d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                        <path
											d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z" />
                                    </svg> <span>Tạo chiến dịch mới</span>
								</a>
							</div>
						</div>
						<div>
							<form action="<c:url value='/xoa-chien-dich?action=deleteMany'/>"
								method="post" id="form1"></form>
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
									<span>Xóa chiến dịch</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- search box -->
			<div class="col-md-4">
				<form action="<c:url value='/quan-li-chien-dich'/>" method="post"
					class="d-flex" role="search">
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
		</div>
	</div>
	<!-- searchbox/create,delete button-->

	<!-- bảng danh sách chiến dịch-->
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
						<th>Tên chiến dịch</th>
						<th>Mục tiêu</th>
						<th>Đã Đạt được</th>
						<th>Trạng thái
							<form action="<c:url value='/quan-li-chien-dich'/>" method="post">
								<div class="input-group">
									<select name="filter"
										class="form-select form-select-sm border-warning">
										<option value="all"
											<c:if test="${text =='all'}"> selected</c:if>>Tất cả</option>
										<option value="active"
											<c:if test="${text=='active'}"> selected</c:if>>active</option>
										<option value="inactive"
											<c:if test="${text =='inactive'}"> selected</c:if>>inactive</option>

										<option value="completed"
											<c:if test="${text =='completed'}"> selected</c:if>>completed</option>
										<option value="deleted"
											<c:if test="${text =='deleted'}"> selected</c:if>>deleted</option>
									</select>
									<button type="submit" class="btn btn-sm btn-primary">Lọc</button>
								</div>
							</form>
						</th>
						<th>Ngày tạo</th>
						<th>Thao tác</th>
					</tr>
					<c:forEach var="c" items="${campaigns}" varStatus="status">
						<tr>
							<td>
								<div class="form-check">
									<input type="checkbox"
										class="checkbox form-check-input c_check" name="c_check"
										value="${c.c_id}" form="form1">
								</div>
							</td>
							<td>${c.c_name}</td>
							<td><fmt:formatNumber value="${c.c_goal}" type="number" /></td>
							<td><fmt:formatNumber value="${c.c_amount}" type="number" /></td>
							<td
								<c:if test="${c.c_status == 'active'}">class="text-success"</c:if>
								<c:if test="${c.c_status == 'inactive'}">class="text-warning"</c:if>
								<c:if test="${c.c_status == 'completed'}">class="text-info"</c:if>
								<c:if test="${c.c_status == 'deleted'}">class="text-secondary"</c:if>>${c.c_status}</td>

							<td>${c.c_created_date}</td>
							<td>
								<div class="d-flex">
									<div>
										<form action="<c:url value='/xoa-chien-dich'/>" method="post">

											<input type="hidden" name="action" value="deleteOne" /> <input
												type="hidden" name="c_id" value="${c.c_id}" />
											<button class="btn btn-sm btn-danger mx-2 btnDeleteOne"
												title="Xóa chiến dịch">
												<!--  icon delete -->
												<svg xmlns="http://www.w3.org/2000/svg" width="16"
													height="16" fill="currentColor" class="bi bi-trash"
													viewBox="0 0 16 16">
                                            <path
														d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
                                            <path fill-rule="evenodd"
														d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
                                        </svg>
												<!--  icon delete -->
											</button>
										</form>
									</div>
									<div>

										<a
											href="<c:url value='/chinh-sua-chien-dich?action=edit&c_id=${c.c_id}'/>"
											class="btn
											btn-sm btn-warning"
											title="Chỉnh sửa chiến dịch" data-toggle="tooltip"> <!-- icon edit -->
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

								</div>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<!-- Campaign List -->

	<!-- Pagination -->
	<div class="d-flex justify-content-center mb-5">

		<div class="mb-5">
			<form
				action="<c:url value='/quan-li-chien-dich?index=${param.index-1}&${name_of_param}=${text}'/>"
				method="post">

				<c:if test="${param.index > 1}">
					<button type="submit" class="btn btn-sm border text-primary mx-1"><<</button>
			</form>
			</c:if>
		</div>
		<div class="d-flex mb-5">
			<c:forEach begin="1" end="${endPage}" var="i">
				<div>
					<form
						action="<c:url value='/quan-li-chien-dich?index=${i}&${name_of_param}=${text}'/>"
						method="post">

						<button type="submit"
							<c:if test="${param.index == i}">class="btn btn-sm border mx-1 text-light bg-primary"</c:if>
							<c:if test="${param.index != i}">class="btn btn-sm border mx-1 text-primary bg-light"</c:if>>${i}
						</button>
					</form>
				</div>
			</c:forEach>
		</div>
		<div class="mb-5">
			<c:if test="${param.index < endPage}">
				<form
					action="<c:url value='/quan-li-chien-dich?index=${param.index+1}&${name_of_param}=${text}'/>"
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