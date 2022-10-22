package bean;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import DAO.DonationDAO;
import model.Donation;

public class DonationService {

	public Donation prepareDonationToAdd(HttpServletRequest request, HttpSession session) throws Exception {
		Donation d = new Donation();
		UserService us = new UserService();

		Object sess_id = session.getAttribute("u_id");
		String req_id = request.getParameter("u_id");
		int c_id = Integer.parseInt(request.getParameter("c_id"));
		String amount = request.getParameter("d_amount");
		String d_notes = request.getParameter("d_notes");
		Date d_date = new Date();

		int u_id = 0;
		if (sess_id != null) {
			u_id = (Integer) (sess_id);
		}

		else if (sess_id == null && req_id == null) {
			u_id = us.getNewUserId();
		} else {
			u_id = Integer.parseInt(req_id);
		}

		float d_amount = Float.parseFloat(amount);

		d.setU_id(u_id);
		d.setC_id(c_id);
		d.setD_date(d_date);
		d.setD_amount(d_amount);
		d.setD_notes(d_notes);

		return d;
	}

	public void saveDonation(Donation d) {
		try {
			new DonationDAO().saveDonation(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Donation> getDonationList() throws Exception {
		List<Donation> list = new DonationDAO().getDonationList();
		return list;
	}

	public List<Donation> getDonationListOfPage(int index, int pageSize) throws Exception {
		List<Donation> list = new DonationDAO().getDonationListOfPage(index, pageSize);
		return list;
	}

	public List<Donation> getDonationListOfPageByUser(int u_id, int index, int pageSize) throws Exception {
		List<Donation> list = new DonationDAO().getDonationListOfPageByUser(u_id, index, pageSize);
		return list;
	}

	public int getEndPage() {

		int num = 1;
		try {
			num = new DonationDAO().getEndPage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Pagination().getEndPage(num);
	}

	public int getEndPageByUser(int u_id) {

		int num = 1;
		try {
			num = new DonationDAO().getEndPageByUser(u_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new Pagination().getEndPage(num);
	}
}
