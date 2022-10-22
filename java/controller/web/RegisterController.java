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
import bean.UserService;
import helper.BCrypt;
import model.User;

/**
 * Servlet implementation class RegisterController
 */
@MultipartConfig
@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
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

		request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
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
			if (us.isEmailExist(u_email)) {
				out.println("email_exist");
			} else if (!us.isEmailExist(u_email)) {
//tao nguoi dung moi
				u = us.prepareUserToAdd(request);
				String pass = rp.randomPassword();

				u.setU_password(bCrypt.hashpw(pass, bCrypt.gensalt()));
				u.setU_role(false);
				u.setU_status("inactive");

				// luu nguoi dung vao db
				try {
					us.addUser(u);

					// gui mat khau den email nguoi dung
					try {
						us.sendPasswordAndVerifyEmail(u_email, pass, u.getU_name());

						out.println("register_success");
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
