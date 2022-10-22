package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Pagination;
import bean.UserService;
import model.User;

/**
 * Servlet implementation class DeleteUserController
 */
@WebServlet("/DeleteUserController")
public class DeleteUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("DeleteUserController ");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		String id = request.getParameter("u_id");
		String result = "";
		UserService us = new UserService();
		int u_id = 0;
		if (id != null) {
			u_id = Integer.parseInt(id);
		}

		// xoa 1 chien dich
		if (action != null && action.equals("deleteOne")) {

			try {
				us.deleteOne(u_id);
				result = "delete_success";
			} catch (Exception e) {
				result = "delete_failure";
			}

		}

		// xoa nhieu chien dich
		if (action != null && action.equals("deleteMany")) {

			String[] u_ids = request.getParameterValues("u_check");

			if (u_ids == null) {
				result = "empt";
			} else {

				try {
					String status = us.deleteManyUsers(u_ids);
					if (status.equals("success")) {
						result = "delete_success";
					} else {
						result = "delete_failure";
					}

				} catch (Exception e) {

				}
			}

			// lay danh sach truyenf len view
			int endPage = 1;
			try {
				endPage = us.getEndPageByName("");
				response.getWriter().println("s1");
			} catch (Exception e1) {
				e1.printStackTrace();
				response.getWriter().println("e1");
			}

			List<User> list = null;
			try {
				list = us.getUserListOfPageByName("", 1, new Pagination().getPageSize());
				response.getWriter().println("s2");
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("e2");
			}
			request.setAttribute("users", list);
			request.setAttribute("endPage", endPage);
			request.setAttribute("result", result);

			request.getRequestDispatcher("/views/admin/manage_user.jsp?index=" + 1).forward(request, response);

		}

	}
}
