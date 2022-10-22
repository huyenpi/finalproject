package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Donation;

public class DonationDAO {

	public void saveDonation(Donation d) throws Exception {
		Connection conn = new DBContext().getConnection();

		String sql = "insert into Donation (u_id,c_id,d_amount,d_date,d_notes) values(?,?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, d.getU_id());
		stmt.setInt(2, d.getC_id());
		stmt.setFloat(3, d.getD_amount());
		stmt.setDate(4, new Date(d.getD_date().getTime()));
		stmt.setString(5, d.getD_notes());

		stmt.executeUpdate();
		conn.close();
	}

	public List<Donation> getDonationList() throws Exception {

		List<Donation> list = new ArrayList<>();

		Connection conn = new DBContext().getConnection();

		String sql = "select d_date, c.c_name, d.d_amount, d.d_notes from Donation as d join Campaign as c on d.c_id=c.c_id order by d_date";

		PreparedStatement stmt = conn.prepareStatement(sql);

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

	public List<Donation> getDonationListOfPage(int index, int pageSize) throws Exception {

		List<Donation> list = new ArrayList<>();

		Connection conn = new DBContext().getConnection();

		String sql = "select * from (select ROW_NUMBER() OVER(ORDER BY d_id DESC) AS R, d_date, c.c_name, d.d_amount, d.d_notes from Donation as d join Campaign as c on d.c_id=c.c_id) as t where t.R between ? and ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		int begin = (index - 1) * pageSize + 1;
		int end = index * pageSize;
		stmt.setInt(1, begin);
		stmt.setInt(2, end);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Date d_date = rs.getDate(2);
			String c_name = rs.getString(3);
			Float d_amount = rs.getFloat(4);
			String d_notes = rs.getString(5);

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

	public List<Donation> getDonationListOfPageByUser(int u_id, int index, int pageSize) throws Exception {

		List<Donation> list = new ArrayList<>();

		Connection conn = new DBContext().getConnection();

		String sql = "select * from (select ROW_NUMBER() OVER(ORDER BY d_id DESC) AS R, d_date, c.c_name, d.d_amount, d.d_notes from Donation as d join Campaign as c on d.c_id=c.c_id where d.u_id = ?) as t where t.R between ? and ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		int begin = (index - 1) * pageSize + 1;
		int end = index * pageSize;
		stmt.setInt(1, u_id);
		stmt.setInt(2, begin);
		stmt.setInt(3, end);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			Date d_date = rs.getDate(2);
			String c_name = rs.getString(3);
			Float d_amount = rs.getFloat(4);
			String d_notes = rs.getString(5);

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

	public int getEndPage() throws Exception {
		int count = 0;
		Connection conn = new DBContext().getConnection();

		String sql = "select count(*) from Donation";

		PreparedStatement stmt = conn.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			count = rs.getInt(1);
		}
		conn.close();
		return count;
	}

	public int getEndPageByUser(int u_id) throws Exception {
		int count = 0;
		Connection conn = new DBContext().getConnection();

		String sql = "select count(*) from Donation where u_id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, u_id);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			count = rs.getInt(1);
		}
		conn.close();
		return count;
	}
}
