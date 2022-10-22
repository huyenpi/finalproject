package controller.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CampaignService;
import model.Campaign;

/**
 * Servlet implementation class LandingPageController
 */
@WebServlet("/LandingPageController")
public class LandingPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LandingPageController() {
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

		String page = request.getParameter("page");
		

		if (page != null && page.equals("home") || page ==null) {
			String i = request.getParameter("index");
			String search = request.getParameter("search");

			CampaignService cs = new CampaignService();

			int index = 1;

			if (i != null) {
				index = Integer.parseInt(i);
			}

			if (search == null) {
				search = "";
			}

			// biến lưu số trang cần hiển thị, dùng để phân trang
			int endPage = cs.getEndPageActiveCampaignByName(search);

			// lay ve list campaign ma moi trang cho phep hien thi
			List<Campaign> list = cs.getActiveCampaignListOfPageByName(search, index);

			request.setAttribute("campaigns", list);
			request.setAttribute("endPage", endPage);
			request.setAttribute("search", search);
			

			RequestDispatcher rd = request.getRequestDispatcher("views/web/landing_page.jsp?index=" + index);
			rd.forward(request, response);
		}

		if (page != null && page.equals("introduce")) {
			RequestDispatcher rd = request.getRequestDispatcher("views/web/introduce.jsp");
			rd.forward(request, response);
		}

		if (page != null && page.equals("contact")) {
			RequestDispatcher rd = request.getRequestDispatcher("views/web/contact.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
