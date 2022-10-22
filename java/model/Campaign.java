package model;

import java.sql.Timestamp;
import java.util.Date;

public class Campaign {
	private int c_id;
	private String c_name;
	private float c_goal;
	private String charity_fund;
	private Timestamp c_created_date;
	private String c_picture;
	private String c_status;
	private String c_details;
	private float c_amount;
	private String u_name;
	private int u_id;

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public Campaign() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Campaign(int c_id, String c_name, String charity_fund, float c_goal, Timestamp c_created_date,
			String c_picture, String c_status, String c_details, float c_amount, String u_name) {
		super();
		this.c_id = c_id;
		this.c_name = c_name;
		this.charity_fund = charity_fund;
		this.c_goal = c_goal;
		this.c_created_date = c_created_date;

		this.c_picture = c_picture;
		this.c_status = c_status;
		this.c_details = c_details;
		this.c_amount = c_amount;
		this.u_name = u_name;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getCharity_fund() {
		return charity_fund;
	}

	public void setCharity_fund(String charity_fund) {
		this.charity_fund = charity_fund;
	}

	public float getC_goal() {
		return c_goal;
	}

	public void setC_goal(float c_goal) {
		this.c_goal = c_goal;
	}

	public Timestamp getC_created_date() {
		return c_created_date;
	}

	public void setC_created_date(Timestamp c_created_date) {
		this.c_created_date = c_created_date;
	}

	public String getC_picture() {
		return c_picture;
	}

	public void setC_picture(String c_picture) {
		this.c_picture = c_picture;
	}

	public String getC_status() {
		return this.c_status;
	}

	public void setC_status(String c_status) {
		this.c_status = c_status;
	}

	public String getC_details() {
		return c_details;
	}

	public void setC_details(String c_details) {
		this.c_details = c_details;
	}

	public float getC_amount() {
		return c_amount;
	}

	public void setC_amount(float c_amount) {
		this.c_amount = c_amount;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	@Override
	public String toString() {
		return "Campaign [c_id=" + c_id + ", c_name=" + c_name + ", c_goal=" + c_goal + ", charity_fund=" + charity_fund
				+ ", c_created_date=" + c_created_date + ", c_started_date=" + ", c_ended_date=" + ", c_picture="
				+ c_picture + ", c_status=" + c_status + ", c_details=" + c_details + ", c_amount=" + c_amount
				+ ", u_name=" + u_name + ", u_id=" + u_id + "]";
	}

}
