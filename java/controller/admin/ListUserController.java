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
 * Servlet implementation class ListUserController
 */
@WebServlet("/ListUserController")
public class ListUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListUserController() {
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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String i = request.getParameter("index");

		int index = 1;
		if (i != null) {
			index = Integer.parseInt(i);
		}

		UserService us = new UserService();
		int endPage = 1;
		try {
			endPage = us.getEndPageByName("");
			//response.getWriter().println("s1");
		} catch (Exception e1) {
			e1.printStackTrace();
			//response.getWriter().println("e1");
		}

		List<User> list = null;
		try {
			list = us.getUserListOfPageByName("", index, new Pagination().getPageSize());
			for(User u : list) {
			response.getWriter().println(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//response.getWriter().println("e2");
		}
		request.setAttribute("users", list);
		request.setAttribute("endPage", endPage);

		request.getRequestDispatcher("/views/admin/manage_user.jsp?index=" + index).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String text_search = request.getParameter("name_search");
		String i = request.getParameter("index");

		int index = 1;
		if (i != null) {
			index = Integer.parseInt(i);
		}
        //tạo đối tượng xử lí các hành động của user
		UserService us = new UserService();
		int endPage = 1;
		
		if (text_search == null) {
			text_search = "";
		}
		//xác định tổng số trang để phân trang
		try {
			endPage = us.getEndPageByName(text_search);
			response.getWriter().println(endPage);
		} catch (Exception e1) {
			e1.printStackTrace();
			response.getWriter().println("e1");
		}
        //lấy danh sách người dùng hiển thị trên 1 trang
		List<User> list = null;
		try {
			list = us.getUserListOfPageByName(text_search, index, new Pagination().getPageSize());
			response.getWriter().println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("e2");
		}
		//truyền dữ liệu vào request
		request.setAttribute("users", list);
		request.setAttribute("endPage", endPage);
		request.setAttribute("text_search", text_search);
        //điều hướng lên view
		request.getRequestDispatcher("/views/admin/manage_user.jsp?index=" + index).forward(request, response);

	}

}
