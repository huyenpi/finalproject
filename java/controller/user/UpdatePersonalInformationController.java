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
import model.User;

/**
 * Servlet implementation class UpdatePersonalInformationController
 */
@MultipartConfig
@WebServlet("/UpdatePersonalInformationController")
public class UpdatePersonalInformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePersonalInformationController() {
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
		// ket qua cap nhat thong tin ca nhan gui tu ajax jquery

		int u_id = (Integer) session.getAttribute("u_id");

		User u = null;
		try {
			u = new UserService().findOne(u_id);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("u", u);

		request.getRequestDispatcher("/views/user/personal_information.jsp").forward(request, response);

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
		int u_id = (Integer) session.getAttribute("u_id");
		String u_email = request.getParameter("u_email");

		UserService us = new UserService();
		User u = null;
		try {
			u = us.findUserByEmail(u_email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (u != null && u.getU_id() != u_id) {
			response.getWriter().println("email_exist");
		}
		if ((u != null && u.getU_id() == u_id) || u == null) {

			try {
				User usr = us.prepareUserToUpdate(request);
				us.updateUser(usr);
				response.getWriter().println("update_success");

			} catch (Exception e) {
				// TODO Auto-generatedcatch block
				e.printStackTrace();
				response.getWriter().println("update_failure");
			}
		}
	}

}
