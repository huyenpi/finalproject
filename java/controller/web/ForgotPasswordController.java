package controller.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.RandomPassword;
import bean.SendEmail;
import bean.UserService;
import helper.BCrypt;
import model.User;

/**
 * Servlet implementation class ForgotPasswordController
 */
@MultipartConfig
@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgotPasswordController() {
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

		request.getRequestDispatcher("/views/web/forgot_password.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		String u_email = request.getParameter("u_email");

		UserService us = new UserService();
		RandomPassword rp = new RandomPassword();
		BCrypt bCrypt = new BCrypt();
		User u = null;
		try {
			if (!us.isEmailExist(u_email)) {
				out.println("email_not_exist");
			} else if (us.isEmailExist(u_email)) {
				String pass = rp.randomPassword();
				us.changePassword(u_email, bCrypt.hashpw(pass, bCrypt.gensalt()));
				u = us.findUserByEmail(u_email);
				try {
					us.sendNewPassword(u_email,pass, u.getU_name());
					out.print("send_password_success");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
