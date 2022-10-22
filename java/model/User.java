package model;

public class User {
	private int u_id;
	private String u_email;
	private String u_password;
	private String u_name;
	private String u_address;
	private String u_phone;
	private String u_picture;
	private String u_status;
	private boolean u_role;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int u_id, String u_email, String u_password, String u_name, String u_address, String u_phone,
			String u_picture, String u_status, boolean u_role) {
		super();
		this.u_id = u_id;
		this.u_email = u_email;
		this.u_password = u_password;
		this.u_name = u_name;
		this.u_address = u_address;
		this.u_phone = u_phone;
		this.u_picture = u_picture;
		this.u_status = u_status;
		this.u_role = u_role;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_password() {
		return u_password;
	}

	public void setU_password(String u_password) {
		this.u_password = u_password;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_address() {
		return u_address;
	}

	public void setU_address(String u_address) {
		this.u_address = u_address;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_picture() {
		return u_picture;
	}

	public void setU_picture(String u_picture) {
		this.u_picture = u_picture;
	}

	public String getU_status() {
		return u_status;
	}

	public void setU_status(String u_status) {
		this.u_status = u_status;
	}

	public boolean getU_role() {
		return u_role;
	}

	public void setU_role(boolean u_role) {
		this.u_role = u_role;
	}

	@Override
	public String toString() {
		return "User [u_id=" + u_id + ", u_email=" + u_email + ", u_password=" + u_password + ", u_name=" + u_name
				+ ", u_address=" + u_address + ", u_phone=" + u_phone + ", u_picture=" + u_picture + ", u_status="
				+ u_status + ", role=" + u_role + "]";
	}

}
