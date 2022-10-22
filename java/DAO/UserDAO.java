package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import context.DBContext;
import model.Donation;
import model.User;

@WebServlet("/UserDAO")
public class UserDAO {

	public int countUsersByName(String characters) throws Exception {
		int num = 0;

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select count(*) from [User] where u_name like ?";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, '%' + characters + '%');
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			num = rs.getInt(1);
		}

		conn.close();
		return num;
	}

	public List<User> getUserListOfPageByName(String characters, int index, int pageSize) throws Exception {
		List<User> list = new ArrayList<>();

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String sql = "select u_name, u_email,u_phone,u_address,u_role,u_status,u_id from (select *, row_number() over (order by u_name asc) as r from [User] where u_name like ?) as t where r between ? and ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + characters + "%");
		int begin = (index - 1) * pageSize + 1;
		int end = index * pageSize;
		stmt.setInt(2, begin);
		stmt.setInt(3, end);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			String u_name = rs.getString(1);
			String u_email = rs.getString(2);
			String u_phone = rs.getString(3);
			String u_address = rs.getString(4);
			boolean u_role = rs.getBoolean(5);
			String u_status = rs.getString(6);
			int u_id = rs.getInt(7);

			User u = new User();
			u.setU_name(u_name);
			u.setU_email(u_email);
			u.setU_phone(u_phone);
			u.setU_address(u_address);
			u.setU_role(u_role);
			u.setU_id(u_id);
			u.setU_status(u_status);
			list.add(u);

		}
		conn.close();
		return list;

	}

	public void deleteOne(int u_id) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "update [user] set u_status = 'deleted' where u_id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, u_id);
		stmt.executeUpdate();
		conn.close();

	}

	public String deleteManyUsers(String[] u_ids) throws SQLException {
		String status = "";
		Connection conn = null;
		Savepoint savepoint = null;
		try {
			conn = new DBContext().getConnection();

			String sql = "update [user] set u_status = 'deleted' where u_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			savepoint = conn.setSavepoint("savepoint1");

			for (String u_id : u_ids) {
				stmt.setString(1, u_id);
				stmt.executeUpdate();
			}

			conn.commit();
			status = "success";
			conn.setAutoCommit(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			conn.rollback(savepoint);
			status = "error";

		}

		conn.close();
		return status;

	}

	public User findOne(int u_id) throws Exception {
		User u = new User();
		Connection conn = new DBContext().getConnection();

		String sql = "select u_name,u_email,u_address,u_phone,u_role,u_status from [User] where u_id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, u_id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String u_name = rs.getString(1);
			String u_email = rs.getString(2);
			String u_address = rs.getString(3);
			String u_phone = rs.getString(4);
			boolean u_role = rs.getBoolean(5);
			String u_status = rs.getString(6);

			u.setU_name(u_name);
			u.setU_email(u_email);
			u.setU_phone(u_phone);
			u.setU_address(u_address);
			u.setU_role(u_role);
			u.setU_id(u_id);
			u.setU_status(u_status);
		}
		conn.close();
		return u;

	}

	public void addUser(User u) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "insert into [User](u_email,u_password,u_name,u_address,u_phone,u_status,u_role) values (?,?,?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, u.getU_email());
		stmt.setString(2, u.getU_password());
		stmt.setString(3, u.getU_name());
		stmt.setString(4, u.getU_address());
		stmt.setString(5, u.getU_phone());
		stmt.setString(6, u.getU_status());
		stmt.setBoolean(7, u.getU_role());

		stmt.executeUpdate();

		conn.close();

	}

	public void updateUser(User u) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "update [User] set u_name=?,u_address=?,u_phone=?,u_status=?,u_role=? where u_id=?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, u.getU_name());
		stmt.setString(2, u.getU_address());
		stmt.setString(3, u.getU_phone());
		stmt.setString(4, u.getU_status());
		stmt.setBoolean(5, u.getU_role());
		stmt.setInt(6, u.getU_id());

		stmt.executeUpdate();

		conn.close();

	}

	public int countEmail(String email) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "select count(*) from [User] where u_email = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		conn.close();
		return count;
	}

	public int countPhone(String email) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "select count(*) from [User] where u_phone = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		conn.close();
		return count;

	}

	public int countPhone(String email, String id) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "select count(*) from [User] where u_phone = ? and u_id <> ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);
		stmt.setString(2, id);

		ResultSet rs = stmt.executeQuery();
		int count = 0;
		while (rs.next()) {
			count = rs.getInt(1);
		}
		conn.close();
		return count;
	}

	public User findUserByEmail(String email) throws Exception {
		User u = null;

		Connection conn = new DBContext().getConnection();

		String sql = "select u_name, u_email,u_phone,u_address,u_role,u_status,u_id from [User] where u_email = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String u_name = rs.getString(1);
			String u_email = rs.getString(2);
			String u_phone = rs.getString(3);
			String u_address = rs.getString(4);
			boolean u_role = rs.getBoolean(5);
			String u_status = rs.getString(6);
			int u_id = rs.getInt(7);

			u = new User();
			u.setU_name(u_name);
			u.setU_email(u_email);
			u.setU_phone(u_phone);
			u.setU_address(u_address);
			u.setU_role(u_role);
			u.setU_id(u_id);
			u.setU_status(u_status);

		}
		conn.close();
		return u;

	}

	public String findPasswordByEmail(String email) throws Exception {
		String password = "";

		Connection conn = new DBContext().getConnection();

		String sql = "select u_password from [User] where u_email = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			password = rs.getString(1);
		}
		conn.close();
		return password;

	}

	public void changePassword(String email, String password) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "update [User] set u_password=? where u_email=?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, password);
		stmt.setString(2, email);

		stmt.executeUpdate();
		conn.close();
	}

	public void changeStatus(String u_email, String status) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "update [user] set u_status = ? where u_email = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, status);
		stmt.setString(2, u_email);
		stmt.executeUpdate();
		conn.close();
	}

	public List<Donation> getDonationsOfUser(int u_id) throws Exception {

		List<Donation> list = new ArrayList<>();

		Connection conn = new DBContext().getConnection();

		String sql = "select d_date, c.c_name, d.d_amount, d.d_notes from Donation as d join Campaign as c on d.c_id=c.c_id where d.u_id = ? order by d_date";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, u_id);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Date d_date = rs.getDate(1);
			String c_name = rs.getString(2);
			Float d_amount = rs.getFloat(3);
			String d_notes = rs.getString(4);

			Donation d = new Donation();

			d.setD_date(d_date);
			d.setC_name(c_name);
			d.setD_amount(d_amount);
			d.setD_notes(d_notes);
			list.add(d);

		}
		conn.close();
		return list;
	}

	public int getNewUserId() throws Exception {
		int u_id = 0;
		Connection conn = new DBContext().getConnection();

		String sql = "select top 1 u_id from [User] order by u_id desc";

		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			u_id = rs.getInt(1);
		}
		conn.close();
		return u_id;
	}

}
