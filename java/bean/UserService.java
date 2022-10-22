package bean;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import DAO.UserDAO;
import model.Donation;
import model.User;

public class UserService {

	public int getEndPageByName(String characters) throws Exception {
		int count = new UserDAO().countUsersByName(characters);
		int endPage = new Pagination().getEndPage(count);
		return endPage;
	}

	public List<User> getUserListOfPageByName(String characters, int index, int pageSize) throws Exception {
		List<User> list = new UserDAO().getUserListOfPageByName(characters, index, pageSize);
		return list;
	}

	public void deleteOne(int u_id) throws Exception {
		new UserDAO().deleteOne(u_id);
	}

	public String deleteManyUsers(String[] u_ids) throws Exception {
		String status = "";
		UserDAO u_DAO = new UserDAO();
		status = u_DAO.deleteManyUsers(u_ids);
		return status;
	}

	public User findOne(int u_id) throws Exception {
		User u = new UserDAO().findOne(u_id);
		return u;
	}

	public User prepareUserToAdd(HttpServletRequest request) {
		User u = new User();
		String u_name = request.getParameter("u_name").toUpperCase();
		String u_email = request.getParameter("u_email");
		String u_password = request.getParameter("u_password");
		String u_address = request.getParameter("u_address");
		String u_phone = request.getParameter("u_phone");
		String u_status = request.getParameter("u_status");
		String u_role = request.getParameter("u_role");

		u.setU_name(u_name);
		u.setU_email(u_email);
		u.setU_address(u_address);
		u.setU_password(u_password);
		u.setU_status("inactive");
		u.setU_phone(u_phone);
		u.setU_role(false);

		return u;

	}

	public User prepareUserToUpdate(HttpServletRequest request) {
		User u = new User();
		String id = request.getParameter("u_id");
		String u_name = request.getParameter("u_name");
		String u_address = request.getParameter("u_address");
		String u_phone = request.getParameter("u_phone");
		String u_status = request.getParameter("u_status");
		String role = request.getParameter("u_role");
		int u_id = 0;
		if (id != null) {
			u_id = Integer.parseInt(id);
		}
		u.setU_id(u_id);
		u.setU_name(u_name);
		u.setU_address(u_address);
		u.setU_status(u_status);
		u.setU_phone(u_phone);
		boolean u_role = false;
		if (role != null && role.equals("true")) {
			u_role = true;
		}
		u.setU_role(u_role);

		return u;

	}

	public void addUser(User u) throws Exception {
		new UserDAO().addUser(u);
	}

	public void updateUser(User u) throws Exception {
		new UserDAO().updateUser(u);
	}

	public boolean isEmailExist(String email) throws Exception {

		int count = new UserDAO().countEmail(email);
		if (count > 0) {
			return true;
		}
		return false;
	}

	public boolean isPhoneExist(String phone) throws Exception {
		if (phone != null && !phone.equals("")) {

			int count = new UserDAO().countPhone(phone);
			if (count > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean isPhoneExist(String phone, String id) throws Exception {
		if (phone != null && !phone.equals("")) {

			int count = new UserDAO().countPhone(phone, id);
			if (count > 0) {
				return true;
			}
		}
		return false;
	}

	public User findUserByEmail(String u_email) throws Exception {
		return new UserDAO().findUserByEmail(u_email);
	}

	public String findPasswordByEmail(String u_email) throws Exception {
		return new UserDAO().findPasswordByEmail(u_email);
	}

	public void changePassword(String email, String password) throws Exception {
		new UserDAO().changePassword(email, password);
	}

	public void sendPasswordAndVerifyEmail(String u_email, String u_password, String u_name) throws MessagingException {
		String sub = "Dang ky tai khoan thanh cong/Quy Tu Thien Ban Mai";
		String msg = "<!DOCTYPE html>\r\n" + "<html lang=\"vi\">\r\n" + "<head>\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<div style=\"display:flex; justify-content: center;\">\r\n" + "        <div>"
				+ "<img src='file:///C:/Users/ADMIN/eclipse-workspace/Huyenpptfx13136_FinalProject/src/main/webapp/pictures/web/logo.png'/>"
				+ "<h3>Chào mừng bạn đến với Quỹ Từ Thiện Ban Mai</h3>"
				+ "    <p>Xin chào <span style='font-weight:bold;'>" + u_name + "</span>!</p>\r\n"
				+ "    <p>Mật khẩu của bạn là: <span style='font-weight:bold;'>" + u_password + "</span></p>\r\n"
				+ "<p>Vui lòng bấm vào liên kết bên dưới để xác minh email và đăng nhập.</p>"
				+ "<a href='http://localhost:8080/Huyenpptfx13136_FinalProject/dang-nhap?verify_email=true&u_email="
				+ u_email + "'>" + "Xác minh email</a>" + "</div>\r\n" + "</div>" + "</body>\r\n"
				+ "</html>";

		new SendEmail().send(u_email, u_password, u_name, sub, msg);

	}
	
	public void sendVerifyEmail(String u_email, String u_name) throws MessagingException {
		String sub = "Kich hoat tai khoan/Quy Tu Thien Ban Mai";
		String msg = "<!DOCTYPE html>\r\n" + "<html lang=\"vi\">\r\n" + "<head>\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<div style=\"display:flex; justify-content: center;\">\r\n" + "        <div>"
				+ "<img src='file:///C:/Users/ADMIN/eclipse-workspace/Huyenpptfx13136_FinalProject/src/main/webapp/pictures/web/logo.png'/>"
				+ "<h3>Chào mừng bạn đến với Quỹ Từ Thiện Ban Mai</h3>"
				+ "    <p>Xin chào <span style='font-weight:bold;'>" + u_name + "</span>!</p>\r\n"
				 + "</span></p>\r\n"
				+ "<p>Vui lòng bấm vào liên kết bên dưới để xác minh email và đăng nhập.</p>"
				+ "<a href='http://localhost:8080/Huyenpptfx13136_FinalProject/dang-nhap?verify_email=true&u_email="
				+ u_email + "'>" + "Xác minh email</a>" + "</div>\r\n" + "</div>" + "</body>\r\n"
				+ "</html>";

		new SendEmail().send(u_email, "", u_name, sub, msg);

	}

	public void sendNewPassword(String u_email, String u_password, String u_name) throws MessagingException {
		String sub = "Quen mat khau/Tao mat khau moi thanh cong/Quy Tu Thien Ban Mai";
		String msg = "<!DOCTYPE html>\r\n" + "<html lang=\"vi\">\r\n" + "<head>\r\n" + "</head>\r\n" + "<body>\r\n"
				+ "<div style=\"display:flex; justify-content: center;\">\r\n" + "        <div>"
				+ "<img src='file:///C:/Users/ADMIN/eclipse-workspace/Huyenpptfx13136_FinalProject/src/main/webapp/pictures/web/logo.png'/>"
				+ "    <p>Xin chào <span style='font-weight:bold;'>" + u_name + "</span>!</p>\r\n"
				+ "    <p>Mật khẩu của bạn là: <span style='font-weight:bold;'>" + u_password + "</span></p>\r\n"
				+ "</div>\r\n" + "</div>" + "</body>\r\n" + "</html>";

		new SendEmail().send(u_email, u_password, u_name, sub, msg);

	}

	public void activeAccount(String u_email) throws Exception {
		new UserDAO().changeStatus(u_email, "active");
	}

	public List<Donation> getDonationsOfUser(int u_id) throws Exception {
		List<Donation> list = new UserDAO().getDonationsOfUser(u_id);
		return list;
	}

	public int getNewUserId() throws Exception {
		return new UserDAO().getNewUserId();
	}
}
