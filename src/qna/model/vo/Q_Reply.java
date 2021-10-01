package qna.model.vo;

import java.util.Date;

public class Q_Reply {
	private int comment2_no;
	private int user_no;
	private String id;
	private Date c_create_date;
	private Date c_modify_date;
	private String c_comment;
	private int q_no;
	private String c_status;
	/*COMMENT2_NO
	USER_NO
	C_CREATE_DATE
	C_MODIFY_DATE
	C_CONTENT
	B_NO
	N_NO
	Q_NO
	ORIGIN_RID
	C_STATUS*/
	
	public Q_Reply() {}
	
	public Q_Reply(int comment2_no, String id, Date c_create_date, Date c_modify_date, String c_comment, int q_no,
			String c_status) {
		super();
		this.comment2_no = comment2_no;
		this.id = id;
		this.c_create_date = c_create_date;
		this.c_modify_date = c_modify_date;
		this.c_comment = c_comment;
		this.q_no = q_no;
		this.c_status = c_status;
	}
	

	public Q_Reply(int comment2_no, String c_comment) {
		super();
		this.comment2_no = comment2_no;
		this.c_comment = c_comment;
	}

	public Q_Reply(int comment2_no, String id, Date c_create_date, String c_comment, int q_no) {
		super();
		this.comment2_no = comment2_no;
		this.id = id;
		this.c_create_date = c_create_date;
		this.c_comment = c_comment;
		this.q_no = q_no;
	}

	public int getComment2_no() {
		return comment2_no;
	}

	public void setComment2_no(int comment2_no) {
		this.comment2_no = comment2_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getC_create_date() {
		return c_create_date;
	}

	public void setC_create_date(Date c_create_date) {
		this.c_create_date = c_create_date;
	}

	public Date getC_modify_date() {
		return c_modify_date;
	}

	public void setC_modify_date(Date c_modify_date) {
		this.c_modify_date = c_modify_date;
	}

	public String getC_comment() {
		return c_comment;
	}

	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}

	public int getQ_no() {
		return q_no;
	}

	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}

	public String getC_status() {
		return c_status;
	}

	public void setC_status(String c_status) {
		this.c_status = c_status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "Q_Reply [comment2_no=" + comment2_no + ", user_no=" + user_no + ", id=" + id + ", c_create_date="
				+ c_create_date + ", c_modify_date=" + c_modify_date + ", c_comment=" + c_comment + ", q_no=" + q_no
				+ ", c_status=" + c_status + "]";
	}

}
