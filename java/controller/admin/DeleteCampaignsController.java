package controller.admin;

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
 * Servlet implementation class DeleteCampaignsController
 */
@WebServlet("/DeleteCampaignsController")
public class DeleteCampaignsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCampaignsController() {
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
		response.getWriter().append("DeleteCampaignController");
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

		String result = "";

		CampaignService cs = new CampaignService();

		// xoa 1 chien dich
		if (action != null && action.equals("deleteOne")) {
			String c_id = request.getParameter("c_id");
			try {
				cs.deleteOneCampaign(c_id);
				result = "delete_success";
			} catch (Exception e) {
				result = "delete_failure";
			}

		}

		// xoa nhieu chien dich

				if (action != null && action.equals("deleteMany")) {

					String[] c_ids = request.getParameterValues("c_check");

					if (c_ids == null) {
						result = "empt";
					} else {
						String status = "";
						try {
							status = cs.deleteManyCampaigns(c_ids);
							if (status.equals("success")) {
								result = "delete_success";
							} else {
								result = "delete_failure";
							}
						} catch (Exception e) {

						}
					}

				}

		int endPage = cs.getEndPageByName("");

		// lay ve list campaign ma moi trang cho phep hien thi
		List<Campaign> campaignListOfPageByName = cs.getCampaignListOfPageByName("", 1);

		request.setAttribute("campaigns", campaignListOfPageByName);
		request.setAttribute("endPage", endPage);
		request.setAttribute("result", result);

		RequestDispatcher rd = request.getRequestDispatcher("views/admin/manage_campaign.jsp?index=" + 1);
		rd.forward(request, response);

	}

}
