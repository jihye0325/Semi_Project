package admin.model.vo;

import java.util.Date;

public class Board {
//	B_NO	NUMBER
//	B_ID	NUMBER
//	USER_NO	NUMBER
//	B_TITLE	VARCHAR2(100 BYTE)
//	B_DATE	DATE
//	B_CONTENT	VARCHAR2(4000 BYTE)
//	B_CREATE_DATE	DATE
//	B_MODIFY_DATE	DATE
//	B_COUNT	NUMBER
//	COMPANION	VARCHAR2(1 BYTE)
//	STATUS	VARCHAR2(1 BYTE)
//	B_TAG	VARCHAR2(1000 BYTE)
	private int b_no;
	private int b_id;
	private	int user_no;
	private	String b_title;
	private	Date b_date;
	private	String b_content;
	private	Date b_create_date;
	private	Date b_modifi_date;
	private	int b_count;
	private	String companion;
	private String status;
	private	String b_tag;
	private String user_name;
	private String id;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Board() {
		super();
	}
	
	public Board(int b_no, int b_id, int user_no, String b_title, Date b_date, String b_content, Date b_create_date,
			Date b_modifi_date, int b_count, String companion, String status, String b_tag, String id) {
		super();
		this.b_no = b_no;
		this.b_id = b_id;
		this.user_no = user_no;
		this.b_title = b_title;
		this.b_date = b_date;
		this.b_content = b_content;
		this.b_create_date = b_create_date;
		this.b_modifi_date = b_modifi_date;
		this.b_count = b_count;
		this.companion = companion;
		this.status = status;
		this.b_tag = b_tag;
		this.id = id;
	}
	public Board(int b_no, int b_id, int user_no, String b_title, Date b_date, String b_content, Date b_create_date,
			Date b_modifi_date, int b_count, String companion, String status, String b_tag) {
		super();
		this.b_no = b_no;
		this.b_id = b_id;
		this.user_no = user_no;
		this.b_title = b_title;
		this.b_date = b_date;
		this.b_content = b_content;
		this.b_create_date = b_create_date;
		this.b_modifi_date = b_modifi_date;
		this.b_count = b_count;
		this.companion = companion;
		this.status = status;
		this.b_tag = b_tag;
	}
	
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public Date getB_create_date() {
		return b_create_date;
	}
	public void setB_create_date(Date b_create_date) {
		this.b_create_date = b_create_date;
	}
	public Date getB_modifi_date() {
		return b_modifi_date;
	}
	public void setB_modifi_date(Date b_modifi_date) {
		this.b_modifi_date = b_modifi_date;
	}
	public int getB_count() {
		return b_count;
	}
	public void setB_count(int b_count) {
		this.b_count = b_count;
	}
	public String getCompanion() {
		return companion;
	}
	public void setCompanion(String companion) {
		this.companion = companion;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getB_tag() {
		return b_tag;
	}
	
	public void setB_tag(String b_tag) {
		this.b_tag = b_tag;
	}
	@Override
	public String toString() {
		return "Board [b_no=" + b_no + ", b_id=" + b_id + ", user_no=" + user_no + ", b_title=" + b_title + ", b_date="
				+ b_date + ", b_content=" + b_content + ", b_create_date=" + b_create_date + ", b_modifi_date="
				+ b_modifi_date + ", b_count=" + b_count + ", companion=" + companion + ", status=" + status
				+ ", b_tag=" + b_tag + ", user_name=" + user_name + "]";
	}

}
