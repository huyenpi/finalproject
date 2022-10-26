package controller.admin;

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
import model.User;

/**
 * Servlet implementation class CreateAndEditUserController
 */
@MultipartConfig
@WebServlet("/CreateAndEditUserController")
public class CreateAndEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAndEditUserController() {
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
		String action = request.getParameter("action");
		String id = request.getParameter("u_id");
		int u_id = 0;
		if (id != null) {
			u_id = Integer.parseInt(id);
		}
		response.getWriter().println("ok");
		if (action != null && action.equals("edit")) {

			User u = null;
			try {
				u = new UserService().findOne(u_id);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.setAttribute("u", u);
		}
		request.setAttribute("action", action);
		request.getRequestDispatcher("/views/admin/editUserInformation.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String u_email = request.getParameter("u_email");
		String u_phone = request.getParameter("u_phone");
		String u_id = request.getParameter("u_id");
		String action = request.getParameter("action");

		PrintWriter out = response.getWriter();
		UserService us = new UserService();
		BCrypt bCrypt = new BCrypt();
		RandomPassword rp = new RandomPassword();
		User u = null;
		if (action != null && action.equals("create")) {
			try {
				if (!us.isEmailExist(u_email)) {
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
							us.sendPasswordAndVerifyEmail(u_email, u.getU_password(), u.getU_name());

							out.println("create_success");
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (us.isEmailExist(u_email)) {
					
					response.getWriter().println("email_exist");
				}
			} catch (Exception e) {
				// response.getWriter().println("create_failure");
				response.getWriter().println(u);

				e.printStackTrace();
			}
		}

		if (action != null && action.equals("edit")) {
			try {

				u = us.prepareUserToUpdate(request);
				us.updateUser(u);
				response.getWriter().println("edit_success");

			} catch (Exception e) {
				response.getWriter().println("edit_failure");
				e.printStackTrace();
			}
		}

	}
}
