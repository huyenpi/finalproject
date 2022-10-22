package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DonationService;
import bean.Pagination;
import model.Donation;

/**
 * Servlet implementation class DonationHistoryController
 */
@WebServlet("/DonationHistoryController")
public class DonationHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonationHistoryController() {
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
		int index = 1;

		if (i != null) {
			index = Integer.parseInt(i);
		}

		HttpSession session = request.getSession();
		DonationService ds = new DonationService();

		// biến lưu số trang cần hiển thị, dùng để phân trang
		int endPage = ds.getEndPage();
		try {
			List<Donation> list = ds.getDonationListOfPage(index, new Pagination().getPageSize());

			request.setAttribute("donations", list);
			request.setAttribute("endPage", endPage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.getRequestDispatcher("/views/admin/donation_history.jsp?index=" + index).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
