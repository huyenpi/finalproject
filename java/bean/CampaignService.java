package bean;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.CampaignDAO;
import model.Campaign;

public class CampaignService {
//lay du lieu tu request tao campaign de add vao db
	public Campaign prepareCampaignToAdd(HttpServletRequest request) {
		String c_name = request.getParameter("c_name");
		String goal = request.getParameter("c_goal");
		String charity_fund = request.getParameter("charity_fund");
		String c_status = request.getParameter("c_status");

		String c_details = request.getParameter("c_details");

		Timestamp c_created_date = new Timestamp(System.currentTimeMillis());
		String c_picture = "";

		float c_goal = 0;
		if (goal != null) {
			c_goal = Float.parseFloat(goal);
		}

		// upload hinh anh

		Part part = null;
		try {
			part = request.getPart("c_picture");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String realPath = request.getServletContext().getRealPath("/pictures/campaign");

		try {
			c_picture = new UploadFile().upload(part, realPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Campaign c = new Campaign();
		c.setC_name(c_name);
		c.setCharity_fund(charity_fund);
		c.setC_goal(c_goal);
		c.setC_status(c_status);
		c.setC_picture(c_picture);
		c.setC_created_date(c_created_date);
		c.setC_details(c_details);
		c.setU_id(1);
		return c;
	}

	public void addCampaign(Campaign c) throws Exception {
		new CampaignDAO().addCampaign(c);
	}

//lay du lieu tu request tao chien dich de cap nhat vao db
	public Campaign prepareCampaignToUpdate(HttpServletRequest request) {
		String id = request.getParameter("c_id");
		String c_name = request.getParameter("c_name");
		String goal = request.getParameter("c_goal");
		String c_status = request.getParameter("c_status");
		String charity_fund = request.getParameter("charity_fund");
		String c_details = request.getParameter("c_details");

		int c_id = 0;
		if (id != null) {
			c_id = Integer.parseInt(id);
		}

		String c_picture = "";

		float c_goal = 0;
		if (goal != null) {
			c_goal = Float.parseFloat(goal);
		}

		// upload hinh anh

		Part part = null;
		try {
			part = request.getPart("c_picture");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String realPath = request.getServletContext().getRealPath("/pictures/campaign");

		try {
			c_picture = new UploadFile().upload(part, realPath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Campaign c = new Campaign();
		c.setC_id(c_id);
		c.setC_name(c_name);
		c.setCharity_fund(charity_fund);
		c.setC_goal(c_goal);
		c.setC_status(c_status);
		c.setC_picture(c_picture);
		c.setC_details(c_details);
		return c;
	}

	public void updateCampaign(Campaign c) throws Exception {
		new CampaignDAO().updateCampaign(c);
	}

//tim 1 chien dich trong db theo id 
	public Campaign findOneCampaign(String id) {
		int c_id = 0;
		if (id != null) {
			c_id = Integer.parseInt(id);
		}
		Campaign c = null;
		try {
			c = new CampaignDAO().findOneById(c_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	// tinh so trang loc theo name
	public int getEndPageByName(String characters) {
		int num = 0;
		try {

			num = new CampaignDAO().countCampaignsByName(characters);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Pagination pag = new Pagination();
		return pag.getEndPage(num);
	}

//tinh so trang loc theo trang thai
	public int getEndPageByStatus(String characters) {
		int num = 0;
		try {

			num = new CampaignDAO().countCampaignsByStatus(characters);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Pagination pag = new Pagination();
		return pag.getEndPage(num);
	}

	// get list hien thi phan trang
	public List<Campaign> getCampaignListOfPageByName(String characters, int index) {

		List<Campaign> list = null;
		try {
			list = new CampaignDAO().getCampaignListOfPageByName(characters, index, new Pagination().getPageSize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

//get list theo trang thai hien thi phan trang
	public List<Campaign> getCampaignListOfPageByStatus(String characters, int index) {

		List<Campaign> list = null;
		try {
			list = new CampaignDAO().getCampaignListOfPageByStatus(characters, index, new Pagination().getPageSize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<Campaign> getActiveCampaignListOfPageByName(String characters, int index) {

		List<Campaign> list = null;
		try {
			list = new CampaignDAO().getActiveCampaignListOfPageByName(characters, index,
					new Pagination().getPageSize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public void deleteOneCampaign(String c_id) throws Exception {
		new CampaignDAO().deleteCampaign(c_id);
	}

	public String deleteManyCampaigns(String[] c_ids) throws Exception {
		String status = "";
		CampaignDAO c_DAO = new CampaignDAO();
		status = c_DAO.deleteManyCampaigns(c_ids);
		return status;
	}

	// kiểm tra xem tên chiến dịch đã tồn tại hay chưa
	public boolean isNameExist(String c_name) throws Exception {
		int count = new CampaignDAO().countCampaignsByName(c_name);
		if (count == 0) {
			return false;
		}
		return true;

	}

	public int getEndPageActiveCampaignByName(String characters) {

		int num = 0;
		try {

			num = new CampaignDAO().countActiveCampaignsByName(characters);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Pagination pag = new Pagination();
		return pag.getEndPage(num);
	}

}