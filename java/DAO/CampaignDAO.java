package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Campaign;

public class CampaignDAO {

	// đếm chiến dịch theo tên
	public int countCampaignsByName(String characters) throws Exception {

		int num = 0;

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select count(*) from Campaign where c_name like ?";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, '%' + characters + '%');
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			num = rs.getInt(1);
		}

		conn.close();
		return num;
	}

	public int countActiveCampaignsByName(String characters) throws Exception {

		int num = 0;

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select count(*) from Campaign where c_name like ? and c_status = 'active'";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, '%' + characters + '%');
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			num = rs.getInt(1);
		}

		conn.close();
		return num;
	}

	// Ham dem so chien dich loc theo trang thai
	public int countCampaignsByStatus(String c_status) throws Exception {

		int num = 0;

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select count(c_status) from campaign where c_status = ?";

		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, c_status);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			num = rs.getInt(1);
		}

		conn.close();
		return num;
	}

	public List<Campaign> getCampaignListOfPageByName(String characters, int index, int pageSize) throws Exception {
		List<Campaign> list = new ArrayList<>();

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select t.c_id, t.c_name, t.charity_fund, t.c_goal,t.c_status, t.c_created_date,t.c_picture,t.c_details,u.u_name,t1.c_amount\r\n"
				+ "from (select row_number() over (order by c_id desc) as r, * from Campaign where c_name like ?) as t join [User] as u\r\n"
				+ "on t.u_id = u.u_id left join (select c_id, sum(d_amount) as c_amount from Donation group by c_id) as t1 on t.c_id = t1.c_id where r between ? and ?\r\n";

		// select Campaigns have name include charaters parameter
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, '%' + characters + '%');
		int begin = (index - 1) * pageSize + 1;
		int end = index * pageSize;
		stmt.setInt(2, begin);
		stmt.setInt(3, end);
		ResultSet rs = stmt.executeQuery();

		// show data
		while (rs.next()) {
			int c_id = rs.getInt(1);
			String c_name = rs.getString(2);
			String charity_fund = rs.getString(3);
			float c_goal = rs.getFloat(4);
			String c_status = rs.getString(5);
			Timestamp c_created_date = rs.getTimestamp(6);
			String c_picture = rs.getString(7);
			String c_details = rs.getString(8);
			String u_name = rs.getString(9);
			float c_amount = 0;
			try {
				c_amount = rs.getFloat(10);
			} catch (Exception e) {

			}

			Campaign c = new Campaign();
			c.setC_name(c_name);
			c.setC_id(c_id);
			c.setCharity_fund(charity_fund);
			c.setC_goal(c_goal);
			c.setC_status(c_status);
			c.setC_created_date(c_created_date);

			c.setC_picture(c_picture);
			c.setC_details(c_details);
			c.setU_name(u_name);
			c.setC_amount(c_amount);

			list.add(c);
		}

		conn.close();
		return list;

	}

	public List<Campaign> getActiveCampaignListOfPageByName(String characters, int index, int pageSize)
			throws Exception {
		List<Campaign> list = new ArrayList<>();

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select t.c_id, t.c_name, t.charity_fund, t.c_goal,t.c_status, t.c_created_date,t.c_picture,t.c_details,u.u_name,t1.c_amount\r\n"
				+ "from (select row_number() over (order by c_id desc) as r, * from Campaign where c_name like ? and c_status = 'active') as t join [User] as u\r\n"
				+ "on t.u_id = u.u_id left join (select c_id, sum(d_amount) as c_amount from Donation group by c_id) as t1 on t.c_id = t1.c_id where r between ? and ?\r\n";

		// select Campaigns have name include charaters parameter
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, '%' + characters + '%');
		int begin = (index - 1) * pageSize + 1;
		int end = index * pageSize;
		stmt.setInt(2, begin);
		stmt.setInt(3, end);
		ResultSet rs = stmt.executeQuery();

		// show data
		while (rs.next()) {
			int c_id = rs.getInt(1);
			String c_name = rs.getString(2);
			String charity_fund = rs.getString(3);
			float c_goal = rs.getFloat(4);
			String c_status = rs.getString(5);
			Timestamp c_created_date = rs.getTimestamp(6);
			String c_picture = rs.getString(7);
			String c_details = rs.getString(8);
			String u_name = rs.getString(9);
			float c_amount = 0;
			try {
				c_amount = rs.getFloat(10);
			} catch (Exception e) {

			}

			Campaign c = new Campaign();
			c.setC_name(c_name);
			c.setC_id(c_id);
			c.setCharity_fund(charity_fund);
			c.setC_goal(c_goal);
			c.setC_status(c_status);
			c.setC_created_date(c_created_date);
			c.setC_picture(c_picture);
			c.setC_details(c_details);
			c.setU_name(u_name);
			c.setC_amount(c_amount);

			list.add(c);
		}

		conn.close();
		return list;

	}

	// danh sach chien dich theo trang thai tren 1 trang
	public List<Campaign> getCampaignListOfPageByStatus(String c_status, int index, int pageSize) throws Exception {
		List<Campaign> list = new ArrayList<>();

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select t.c_id, t.c_name,t.charity_fund, t.c_goal,t.c_status, t.c_created_date,t.c_started_date, t.c_ended_date,t.c_picture,t.c_details,u.u_name,t2.c_amount\r\n"
				+ "					from (select  row_number() over (order by c.c_id desc) as r,* from Campaign as c where c_status = ?) as t	  join [User] as u\r\n"
				+ "						 on t.u_id = u.u_id left join (select c_id, sum(d_amount) as c_amount from Donation group by c_id) as t2 on t.c_id = t2.c_id \r\n"
				+ "				where r between ? and ?";

		PreparedStatement stmt = conn.prepareStatement(query);

		int begin = (index - 1) * pageSize + 1;
		int end = index * pageSize;
		stmt.setString(1, "" + c_status);
		stmt.setInt(2, begin);
		stmt.setInt(3, end);
		ResultSet rs = stmt.executeQuery();

		// show data
		while (rs.next()) {
			int c_id = rs.getInt(1);
			String c_name = rs.getString(2);
			String charity_fund = rs.getString(3);
			float c_goal = rs.getFloat(4);

			Timestamp c_created_date = rs.getTimestamp(6);

			String c_picture = rs.getString(7);
			String c_details = rs.getString(8);
			String u_name = rs.getString(9);
			float c_amount = 0;
			try {
				c_amount = rs.getFloat(10);
			} catch (Exception e) {

			}
			Campaign c = new Campaign();
			c.setC_name(c_name);
			c.setC_id(c_id);
			c.setCharity_fund(charity_fund);
			c.setC_goal(c_goal);
			c.setC_status(c_status);
			c.setC_created_date(c_created_date);

			c.setC_picture(c_picture);
			c.setC_details(c_details);
			c.setU_name(u_name);
			c.setC_amount(c_amount);
			list.add(c);
		}

		conn.close();
		return list;

	}

	// ham xoa 1 chien dich
	public void deleteCampaign(String c_id) throws Exception {
		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String sql = "update Campaign set c_status = 'deleted' where c_id = ? ";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, c_id);
		stmt.executeUpdate();

		conn.close();
	}
	
	// ham xoa nhieu chien dich
		public String deleteManyCampaigns (String[] ids) throws SQLException {
			String status = "";
			Connection conn = null;
			Savepoint savepoint = null;

			DBContext ds = new DBContext();
			try {
				conn = ds.getConnection();
				String sql = "update Campaign set c_status = 'deleted' where c_id = ? ";

				PreparedStatement stmt = conn.prepareStatement(sql);

				conn.setAutoCommit(false);
				savepoint = conn.setSavepoint("savepoint1");

				for (String c_id : ids) {
					stmt.setString(1, c_id);
					stmt.executeUpdate();
				}

				conn.commit();
				status = "success";

				// tra lai default mode cho jdbc khi thuc hien xong
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

	// ham tim thong tin cua 1 chien dich
	public Campaign findOneById(int c_id) throws Exception {
		Campaign c = null;

		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String query = "select t.c_id, t.c_name,t.charity_fund, t.c_goal,t.c_status, t.c_created_date,t.c_picture,t.c_details,u.u_name,t1.c_amount\r\n"
				+ "from (select row_number() over (order by c_id asc) as r, * from Campaign where c_id = ?) as t join [User] as u\r\n"
				+ "on t.u_id = u.u_id left join (select c_id, sum(d_amount) as c_amount from Donation group by c_id) as t1 on t.c_id = t1.c_id\r\n";

		// select Campaigns have name include charaters parameter
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setInt(1, c_id);
		ResultSet rs = stmt.executeQuery();

		// show data
		while (rs.next()) {
			String c_name = rs.getString(2);
			String charity_fund = rs.getString(3);
			float c_goal = rs.getFloat(4);
			String c_status = rs.getString(5);
			Timestamp c_created_date = rs.getTimestamp(6);
			String c_picture = rs.getString(7);
			String c_details = rs.getString(8);
			String u_name = rs.getString(9);
			float c_amount = 0;
			try {
				c_amount = rs.getFloat(10);
			} catch (Exception e) {

			}

			c = new Campaign();
			c.setC_name(c_name);
			c.setC_id(c_id);
			c.setCharity_fund(charity_fund);
			c.setC_goal(c_goal);
			c.setC_status(c_status);
			c.setC_created_date(c_created_date);
			c.setC_picture(c_picture);
			c.setC_details(c_details);
			c.setU_name(u_name);
			c.setC_amount(c_amount);

		}

		conn.close();
		return c;

	}

	public void addCampaign(Campaign c) throws Exception {
		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String sql = "insert into Campaign (c_name,charity_fund,c_goal,c_status,c_created_date,c_picture,c_details,u_id) values (?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, c.getC_name());
		stmt.setString(2, c.getCharity_fund());
		stmt.setFloat(3, c.getC_goal());
		stmt.setString(4, c.getC_status());
		stmt.setTimestamp(5, c.getC_created_date());
		stmt.setString(6, c.getC_picture());
		stmt.setString(7, c.getC_details());
		stmt.setInt(8, c.getU_id());

		stmt.executeUpdate();
		conn.close();

	}

	public void updateCampaign(Campaign c) throws Exception {
		Connection conn = null;

		DBContext ds = new DBContext();
		conn = ds.getConnection();

		String sql = "update Campaign\r\n"
				+ "set c_name=?, charity_fund=?, c_goal=?, c_status=?, c_picture=?, c_details=?\r\n" + "where c_id=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, c.getC_name());
		stmt.setString(2, c.getCharity_fund());
		stmt.setFloat(3, c.getC_goal());
		stmt.setString(4, c.getC_status());

		stmt.setString(5, c.getC_picture());
		stmt.setString(6, c.getC_details());
		stmt.setInt(7, c.getC_id());

		stmt.executeUpdate();
		conn.close();

	}

}
