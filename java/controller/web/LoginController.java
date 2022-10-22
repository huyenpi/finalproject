package controller.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserService;
import helper.BCrypt;
import model.User;

/**
 * Servlet implementation class LoginController
 */
@MultipartConfig
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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

		String verify_email = request.getParameter("verify_email");
		UserService us = new UserService();
		response.getWriter().println(verify_email);
		if (verify_email != null && verify_email.equals("true")) {
			String u_email = request.getParameter("u_email");

			try {
				us.activeAccount(u_email);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		HttpSession session = request.getSession();
		String check_login = (String) session.getAttribute("check_login");

		if (check_login != null && check_login.equals("logged")) {
			request.getRequestDispatcher("/dang-xuat").forward(request, response);
		} else {

			request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();

		String u_email = request.getParameter("u_email");
		String u_password = request.getParameter("u_password");
		String remember_me = request.getParameter("remember_me");

		PrintWriter out = response.getWriter();
		UserService us = new UserService();
		BCrypt bCrypt = new BCrypt();

		User u = null;

		if (!u_email.equals("")) {

			try {
				u = us.findUserByEmail(u_email);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.println("login_failure");
			}
		}

		if (u == null) {
			out.println("email_not_exist");
		} else if (u.getU_status().equals("inactive")) {
			try {
				us.sendVerifyEmail(u_email,u.getU_name());
				out.println("not_active");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (u.getU_status().equals("deleted")) {
			out.println("deleted_account");
		} else {
			String password = "";
			try {
				password = us.findPasswordByEmail(u_email);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!password.equals("") && bCrypt.checkpw(u_password, password)) {

				if (u.getU_role() == true) {
					out.println("admin_login");
				}

				if (u.getU_role() == false) {
					out.println("user_login");
				}
				session.setAttribute("check_login", "logged");
				session.setAttribute("u_name", u.getU_name());
				session.setAttribute("u_email", u_email);
				session.setAttribute("u_id", u.getU_id());

				if (remember_me != null && remember_me.equals("on")) {
					Cookie email = new Cookie("u_email", u_email);
					email.setMaxAge(60 * 60 * 24);
					response.addCookie(email);
				}
			} else {
				out.print("wrong_password");
			}
		}

	}

}
