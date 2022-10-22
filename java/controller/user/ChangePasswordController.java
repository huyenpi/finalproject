package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserService;
import helper.BCrypt;

/**
 * Servlet implementation class ChangePasswordController
 */
@MultipartConfig
@WebServlet("/ChangePasswordController")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePasswordController() {
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

		request.getRequestDispatcher("/views/user/change_password.jsp").forward(request, response);
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

		String u_email = (String) session.getAttribute("u_email");
		String u_password = request.getParameter("u_password");
		String new_password = request.getParameter("new_password");

		UserService us = new UserService();
		BCrypt bCrypt = new BCrypt();

		String pass = "";
		try {
			pass = us.findPasswordByEmail(u_email);
			if (bCrypt.checkpw(u_password, pass)) {
				try {
					us.changePassword(u_email, bCrypt.hashpw(new_password, bCrypt.gensalt()));
					response.getWriter().println("change_password_success");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				response.getWriter().println("wrong_password");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
