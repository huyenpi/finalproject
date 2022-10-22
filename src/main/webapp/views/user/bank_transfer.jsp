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
	<link rel="stylesheet" href="<c:url value='/css/bank_transfer.css'/>">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="<c:url value='/js/user/bank_transfer.js'/>"></script>

</head>

<body class="bg-light">
	<!-- import top nav -->
	<c:import url="top_nav.jsp"></c:import>
	<!-- import top nav -->

	<h5 class="text-center mt-3">THỰC HIỆN QUYÊN GÓP QUA CHUYỂN KHOẢN
		NGÂN HÀNG</h5>
	<!-- campaign card -->
	<div class="container mt-5">

		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-6">
				<form id="formSubmit" action="<c:url value='/luu-quyen-gop'/>"
					method="post" myContextPath="${pageContext.request.contextPath}">
					<input type="hidden" name="target" value="save_payment">

					<c:if test="${check_login == 'logged'}">
						<div class="form-group mb-2">
							<div class="row mb-2">
								<label for="d_amount">Số tiền quyên góp<span
									class="text-danger">*</span>:
								</label>
								<div class="col-lg-6">
									<input class="form-control" type="number" name="d_amount"
										id="d_amount">
								</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="d_notes">Lời nhắn:</label>
							<textarea class="form-control" type="text" name="d_notes"
								id="d_notes"></textarea>
						</div>
					</c:if>

					<c:if test="${check_login != 'logged' && u_name == null}">
						<div class="container">
							<div class="row">

								<div class="col-lg-6">
									<div class="form-group mb-2">
										<div class="row mb-2">
											<label for="u_name">Họ tên<span class="text-danger">*</span>:
											</label>
											<div class="col-lg-12">
												<input class="form-control" type="text" name="u_name"
													id="u_name">
											</div>
										</div>

										<div class="row mb-2">
											<label for="d_amount">Số tiền quyên góp<span
												class="text-danger">*</span>:
											</label>
											<div class="col-lg-12">
												<input class="form-control" type="number" name="d_amount"
													id="d_amount">
											</div>
										</div>

										<div class="mb-3">
											<label for="d_notes">Lời nhắn:</label>
											<textarea class="form-control" type="text" name="d_notes"
												id="d_notes"></textarea>
										</div>
									</div>
								</div>
								<div class="col-lg-6">

									<div class="form-group mb-2">

										<div class="row mb-2">
											<label for="u_email">Email<span class="text-danger">*</span>:
											</label>
											<div class="col-lg-12">
												<input class="form-control" type="text" name="u_email"
													id="u_email" value="${u_email}" readonly>
											</div>
										</div>

										<div class="row mb-2">
											<label for="u_address">Địa chỉ: </label>
											<div class="col-lg-12">
												<input class="form-control" type="text" name="u_address"
													id="u_address">
											</div>
										</div>

										<div class="row mb-2">
											<label for="u_phone">Số điện thoại: </label>
											<div class="col-lg-12">
												<input class="form-control" type="number" name="u_phone"
													id="u_phone">
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</c:if>

					<c:if test="${check_login != 'logged' && u_name != null}">
						<input type="hidden" name="u_id" value="${u_id}">
						<div class="container">
							<div class="row">

								<div class="col-lg-6">
									<div class="form-group mb-2">
										<div class="row mb-2">
											<label for="u_name">Họ tên<span class="text-danger">*</span>:
											</label>
											<div class="col-lg-12">
												<input class="form-control" type="text" name="u_name"
													id="u_name" value="${u_name}" readonly>
											</div>
										</div>
										<div class="form-group mb-3">
											<div class="row mb-2">
												<label for="u_email">Email<span class="text-danger">*</span>:
												</label>
												<div class="col-lg-12">
													<input class="form-control" type="text" name="u_email"
														id="u_email" value="${u_email}" readonly>
												</div>
											</div>
										</div>


									</div>
								</div>
								<div class="col-lg-6">

									<div class="row mb-2">
										<label for="d_amount">Số tiền quyên góp<span
											class="text-danger">*</span>:
										</label>
										<div class="col-lg-12">
											<input class="form-control" type="number" name="d_amount"
												id="d_amount">
										</div>
									</div>

									<div class="mb-3">
										<label for="d_notes">Lời nhắn:</label>
										<textarea class="form-control" type="text" name="d_notes"
											id="d_notes"></textarea>
									</div>
								</div>
							</div>
						</div>
					</c:if>

					<h6>Vui lòng chuyển khoản đến:</h6>

					<div class="my-2 border border-success px-5 py-2">
						<p>
							Ngân hàng:<span class="fw-bold"> Techcombank</span>
						</p>
						<p>
							Số tài khoản:<span class="fw-bold"> 190358584959959</span>
						</p>
						<p>
							Chủ tài khoản:<span class="fw-bold"> QUỸ TỪ THIỆN BAN MAI</span>
						</p>
						<p>
							Chi nhánh: <span class="fw-bold">Hà Nội</span>
						</p>

						<p>
							Nội dung chuyển khoản: <span class="fw-bold">${param.c_id}</span>
						</p>

						<p class="fw-bold">Chú ý:</p>
						<p class="text-danger">Nội dung chuyển khoản là mã của chiến
							dịch. Bạn cần viết nội dung chuyển khoản chính xác như trên để
							giúp chúng tôi xác nhận và gửi số tiền bạn quyên góp tới đúng
							chiến dịch bạn đang quan tâm!</p>

						<p <c:if test="${check_login != 'logged'}">class="d-none"</c:if>>
							Nếu sau vài giờ lượt quyên góp của bạn chưa được cập nhật trong
							lịch sử quyên góp, vui lòng gọi đến: <span class="fw-bold">1900
								800 </span>để được hỗ trợ. Xin cảm ơn!
						</p>

					</div>

					<div class="d-flex justify-content-center">
						<input type="hidden" name="target" value="save_payment"> <input
							type="hidden" name="c_id" value="${c.c_id}"> <input
							class="btn btn-sm btn-primary my-2" type="submit"
							value="Đã quyên góp">
					</div>
				</form>
				<!-- loader -->
				<div class="d-flex justify-content-center">
					<div id="loader" class="mb-5"></div>
				</div>
				<!-- loader -->

			</div>
			<div class="col-lg-3">
				<div class="">
					<a href="<c:url value='/cau-chuyen?c_id=${c.c_id}'/>"> <img
						src="<c:url value='/pictures/campaign/${c.c_picture}'/>"
						class="img-fluid" alt="..."></a>
					<div class="p-4">
						<a href="<c:url value='/cau-chuyen?c_id=${c.c_id}'/>"
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