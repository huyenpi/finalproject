package controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CampaignService;
import bean.DonationService;
import bean.Pagination;
import bean.RandomPassword;
import bean.UserService;
import helper.BCrypt;
import model.Campaign;
import model.Donation;
import model.User;

/**
 * Servlet implementation class DonationController
 */
@MultipartConfig
@WebServlet("/DonationController")
public class DonationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserService us = new UserService();
		CampaignService cs = new CampaignService();
		DonationService ds = new DonationService();

		String target = request.getParameter("target");

		// xem lịch sử quyên góp
		String i = request.getParameter("index");
		int index = 1;
		if (i != null) {
			index = Integer.parseInt(i);
		}

		if (session.getAttribute("u_id") != null) {

			int u_id = (Integer) session.getAttribute("u_id");

			if (target != null && target.equals("donation_history")) {

				try {
					// biến lưu số trang cần hiển thị, dùng để phân trang
					int endPage = ds.getEndPageByUser(u_id);
					List<Donation> list = ds.getDonationListOfPageByUser(u_id, index, new Pagination().getPageSize());

					session.setAttribute("donations", list);
					request.setAttribute("endPage", endPage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				request.getRequestDispatcher("/views/user/donation_history.jsp?index=" + index).forward(request,
						response);
			}
		}

		// thực hiện quyên góp, check login trươc khi chuyển hướng đến màn hình chọn
		// phương thức quyên góp
		if (target != null && target.equals("make_donation")) {

			if (session.getAttribute("u_id") == null) {
				request.getRequestDispatcher("/views/user/check_login.jsp").forward(request, response);
			} else {

				String c_id = request.getParameter("c_id");

				Campaign c = cs.findOneCampaign(c_id);
				request.setAttribute("c", c);

				request.getRequestDispatcher("/views/user/choose_payment.jsp").forward(request, response);
			}
		}

		// quyên góp ẩn danh
		if (target != null && target.equals("make_anonymous_donation")) {
			String u_email = request.getParameter("u_email");
			String c_id = request.getParameter("c_id");
			User u = null;

			try {
				u = us.findUserByEmail(u_email);

				if (u != null) {
					request.setAttribute("u_name", u.getU_name());

					request.setAttribute("u_id", u.getU_id());
				}
				Campaign c = cs.findOneCampaign(c_id);
				request.setAttribute("u_email", u_email);
				request.setAttribute("c", c);

				request.getRequestDispatcher("/views/user/choose_payment.jsp").forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// chuyển hướng đến màn hình chuyển khoản ngân hàng
		String paymentMethod = request.getParameter("paymentMethod");
		String u_email = request.getParameter("u_email");
		String u_name = request.getParameter("u_name");
		String u_id = request.getParameter("u_id");
		if (target != null && target.equals("choose_payment") && paymentMethod != null
				&& paymentMethod.equals("bank")) {
			String c_id = request.getParameter("c_id");

			Campaign c = cs.findOneCampaign(c_id);
			request.setAttribute("u_name", u_name);
			request.setAttribute("u_email", u_email);
			request.setAttribute("u_id", u_id);
			request.setAttribute("c", c);

			request.getRequestDispatcher("/views/user/bank_transfer.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// khi khác hàng đã chuyển khoản, lưu quyên góp
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		Object check_login = session.getAttribute("check_login");

		UserService us = new UserService();
		DonationService ds = new DonationService();
		RandomPassword rp = new RandomPassword();
		BCrypt bCrypt = new BCrypt();
		String target = request.getParameter("target");

		if (target != null && target.equals("save_payment")) {

			if (check_login != null) {
				Donation d = null;
				try {
					d = ds.prepareDonationToAdd(request, session);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ds.saveDonation(d);
				out.println("donation_success");
			}
			if (check_login == null) {
				String u_email = request.getParameter("u_email");
				User u = null;
				try {
					if (us.isEmailExist(u_email)) {
						Donation d = ds.prepareDonationToAdd(request, session);
						ds.saveDonation(d);
						out.println("donation_success");
					} else if (!us.isEmailExist(u_email)) {
						// tao nguoi dung moi
						u = us.prepareUserToAdd(request);
						String pass = rp.randomPassword();
						u.setU_password(bCrypt.hashpw(pass, bCrypt.gensalt()));
						u.setU_role(false);
						u.setU_status("inactive");
						us.addUser(u);
						us.sendPasswordAndVerifyEmail(u_email, pass, u.getU_name());
						Donation d = ds.prepareDonationToAdd(request, session);
						ds.saveDonation(d);
						out.println("donation_success");

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					out.println("looix");
				}

			}

		}

	}

}
