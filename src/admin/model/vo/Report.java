package admin.model.vo;

public class Report {
	private int report_no;
	private int b_no;
	private int user_no;
	private int report_id;
	private int b_id;
	private String id;
	private String user_name;
	private String report_con;
	private String b_title;
	private String b_content;
	public Report() {
	
	}
	
	@Override
	public String toString() {
		return "Report [report_no=" + report_no + ", b_no=" + b_no + ", user_no=" + user_no + ", report_id=" + report_id
				+ ", b_id=" + b_id + ", id=" + id + ", user_name=" + user_name + ", report_con=" + report_con
				+ ", b_title=" + b_title + ", b_content=" + b_content + "]";
	}

	public Report(int report_no, int b_no, int user_no, int report_id, String id, String user_name, String report_con,
			String b_title) {
		super();
		this.report_no = report_no;
		this.b_no = b_no;
		this.user_no = user_no;
		this.report_id = report_id;
		this.id = id;
		this.user_name = user_name;
		this.report_con = report_con;
		this.b_title = b_title;
	}
	public int getReport_no() {
		return report_no;
	}
	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}
	public int getB_no() {
		return b_no;
	}
	public void setB_no(int b_no) {
		this.b_no = b_no;
	}
	public int getUser_no() {
		return user_no;
	}
	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}
	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getReport_con() {
		return report_con;
	}
	public void setReport_con(String report_con) {
		this.report_con = report_con;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	
	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public Report(int report_no, String id, String report_con, String b_title) {
		super();
		this.report_no = report_no;
		this.id = id;
		this.report_con = report_con;
		this.b_title = b_title;
	}

	public Report(int b_no, int b_id, String id, String user_name, String report_con, String b_title, String b_content) {
		
		this.b_no = b_no;
		this.b_id = b_id;
		this.id = id;
		this.user_name = user_name;
		this.report_con = report_con;
		this.b_title = b_title;
		this.b_content = b_content;
	}
	
	
	}
