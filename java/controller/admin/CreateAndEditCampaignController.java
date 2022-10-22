package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CampaignDAO;
import bean.CampaignService;
import model.Campaign;

/**
 * Servlet implementation class CreateAndEditCampaignController
 */
@MultipartConfig
@WebServlet("/CreateAndEditCampaignController")
public class CreateAndEditCampaignController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateAndEditCampaignController() {
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
		String id = request.getParameter("c_id");

		// Tìm chiến dịch theo id trong db, truyển lên view chỉnh sửa chiến dịch
		if (action != null && action.equals("edit")) {

			Campaign c = new CampaignService().findOneCampaign(id);

			request.setAttribute("c", c);
		}
		request.setAttribute("action", action);
		request.getRequestDispatcher("/views/admin/editCampaign.jsp").forward(request, response);

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

		Campaign c = null;
		// Hành động tạo chiến dịch,lưu vào db
		if (action != null && action.equals("create")) {

			c = new CampaignService().prepareCampaignToAdd(request);

			try {
				new CampaignService().addCampaign(c);
				response.getWriter().println("create_success");
			} catch (Exception e) {

				e.printStackTrace();

				response.getWriter().println("create_failure");
			}
			;

		}
		// hành động chỉnh sửa chiến dịch, lưu vào db
		if (action != null && action.equals("edit")) {
			
			c = new CampaignService().prepareCampaignToUpdate(request);
			
			try {

				new CampaignService().updateCampaign(c);

				response.getWriter().println("edit_success");

			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().println("edit_failure");

			}

		}

	}

}
