package admin.model.vo;

import java.util.Date;

public class Notice {
	private int n_no;
	private int user_no;
	private String n_title;
	private String n_content;
	private Date n_date;
	private int n_hit;
	private String n_status;
	private Date n_modify_date;
	public Notice() {
		super();
	}
	public Notice(int n_no, int user_no, String n_title, String n_content, Date n_date, int n_hit, String n_status,
			Date n_modify_date) {
		super();
		this.n_no = n_no;
		this.user_no = user_no;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_date = n_date;
		this.n_hit = n_hit;
		this.n_status = n_status;
		this.n_modify_date = n_modify_date;
	}
	public int getN_no() {
		return n_no;
	}
	public void setN_no(int n_no) {
		this.n_no = n_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public Date getN_date() {
		return n_date;
	}
	public void setN_date(Date n_date) {
		this.n_date = n_date;
	}
	public int getN_hit() {
		return n_hit;
	}
	public void setN_hit(int n_hit) {
		this.n_hit = n_hit;
	}
	public String getN_status() {
		return n_status;
	}
	public void setN_status(String n_status) {
		this.n_status = n_status;
	}
	public Date getN_modify_date() {
		return n_modify_date;
	}
	public void setN_modify_date(Date n_modify_date) {
		this.n_modify_date = n_modify_date;
	}
	@Override
	public String toString() {
		return "Notice [n_no=" + n_no + ", user_no=" + user_no + ", n_title=" + n_title + ", n_content=" + n_content
				+ ", n_date=" + n_date + ", n_hit=" + n_hit + ", n_status=" + n_status + ", n_modify_date="
				+ n_modify_date + "]";
	}
	
	
}
