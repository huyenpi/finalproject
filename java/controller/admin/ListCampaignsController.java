package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CampaignDAO;
import bean.CampaignService;
import bean.Pagination;
import model.Campaign;

/**
 * Servlet implementation class ListCampaignsController
 */
@WebServlet("/ListCampaignsController")
public class ListCampaignsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListCampaignsController() {
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

		String i = request.getParameter("index");

		CampaignService cs = new CampaignService();

		int index = 1;

		if (i != null) {
			index = Integer.parseInt(i);
		}

		// biến lưu số trang cần hiển thị, dùng để phân trang
		int endPage = cs.getEndPageByName("");

		// lay ve list campaign ma moi trang cho phep hien thi
		List<Campaign> campaignListOfPageByName = cs.getCampaignListOfPageByName("", index);

		request.setAttribute("campaigns", campaignListOfPageByName);
		request.setAttribute("endPage", endPage);
		response.getWriter().println();

		RequestDispatcher rd = request.getRequestDispatcher("views/admin/manage_campaign.jsp?index=" + index);
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String text_search = request.getParameter("search");
		String text_filter = request.getParameter("filter");
		String i = request.getParameter("index");

		CampaignService cs = new CampaignService();

		int index = 1;

		if (i != null) {
			index = Integer.parseInt(i);
		}

		// lay các chiến dịch theo từ khóa tìm kiếm
		if (text_search != null) {

			int endPage = cs.getEndPageByName(text_search);

			// lay ve list campaign ma moi trang cho phep hien thi
			List<Campaign> campaignListOfPageByName = cs.getCampaignListOfPageByName(text_search, index);
			request.setAttribute("campaigns", campaignListOfPageByName);
			request.setAttribute("endPage", endPage);

			request.setAttribute("name_of_param", "search");
			request.setAttribute("text", text_search);
			RequestDispatcher rd = request.getRequestDispatcher("views/admin/manage_campaign.jsp?index=" + index);
			rd.forward(request, response);

		}

		// danh sách các chiến dịch loc theo trạng thái
		if (text_filter != null) {

			int endPage = cs.getEndPageByStatus(text_filter);

			List<Campaign> campaignListOfPageByStatus = cs.getCampaignListOfPageByStatus(text_filter, index);
			response.getWriter().println(campaignListOfPageByStatus.size());

			request.setAttribute("campaigns", campaignListOfPageByStatus);
			request.setAttribute("endPage", endPage);

			request.setAttribute("name_of_param", "filter");
			request.setAttribute("text", text_filter);
			RequestDispatcher rd = request.getRequestDispatcher("views/admin/manage_campaign.jsp?index=" + index);
			rd.forward(request, response);

		}

		if (text_search == null && text_filter == null) {
			int endPage = cs.getEndPageByName("");

			// lay ve list campaign ma moi trang cho phep hien thi
			List<Campaign> campaignListOfPageByName = cs.getCampaignListOfPageByName("", index);

			request.setAttribute("campaigns", campaignListOfPageByName);
			request.setAttribute("endPage", endPage);
			

			RequestDispatcher rd = request.getRequestDispatcher("views/admin/manage_campaign.jsp?index=" + index);
			rd.forward(request, response);
		}

	}

}
