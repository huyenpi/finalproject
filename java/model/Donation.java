package model;

import java.util.Date;

public class Donation {
	private int d_id;
	private int u_id;
	private int c_id;
	private float d_amount;
	private Date d_date;
	private String d_notes;
	private String c_name;

	public Donation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Donation(int d_id, int u_id, int c_id, float d_amount, Date d_date, String d_notes, String c_name) {
		super();
		this.d_id = d_id;
		this.u_id = u_id;
		this.c_id = c_id;
		this.d_amount = d_amount;
		this.d_date = d_date;
		this.d_notes = d_notes;
		this.c_name = c_name;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public int getD_id() {
		return d_id;
	}

	public void setD_id(int d_id) {
		this.d_id = d_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public float getD_amount() {
		return d_amount;
	}

	public void setD_amount(float d_amount) {
		this.d_amount = d_amount;
	}

	public Date getD_date() {
		return d_date;
	}

	public void setD_date(Date d_date) {
		this.d_date = d_date;
	}

	public String getD_notes() {
		return d_notes;
	}

	public void setD_notes(String d_notes) {
		this.d_notes = d_notes;
	}

}
