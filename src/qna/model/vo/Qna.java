package qna.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

public class Qna {

	private int q_no;
	private int q_cid;
	private String q_cname;
	private int user_no;
	private String user_name;
	private String id;
	private String q_title;
	private String q_content;
	private int q_count;
	private String open_status;
	private Date q_create_date;
	private Date q_modify_date;
	private String q_status;
	private Q_Image q_image = new Q_Image();
	private List<Q_Reply> q_replyList;
	private int reply_count;
	/*A_NO
	A_CID
	USER_NO
	A_TITLE
	A_CONTENT
	A_COUNT
	A_CREATE_DATE
	A_MODIFY_DATE
	OPEN_STATUS
	A_STATUS*/
	
	public Qna() {}
	
	
	
	public Qna(int q_no, String q_cname, String q_title, int user_no, String id, int q_count, Date q_create_date, String open_status, int reply_count) {
		super();
		this.q_no = q_no;
		this.q_cname = q_cname;
		this.user_no = user_no;
		this.id = id;
		this.q_title = q_title;
		this.q_count = q_count;
		this.q_create_date = q_create_date;
		this.open_status = open_status;
		this.reply_count = reply_count;
	}

	public Qna(int user_no, int q_cid, String q_title, String q_content, String open_status, Q_Image q_image) {
		super();
		this.user_no = user_no;
		this.q_cid = q_cid;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_image = q_image;
		this.open_status = open_status;
	}


	public Qna(int q_no, int q_cid, String q_cname, int user_no, String id, String q_title, String q_content, int q_count, Date q_create_date,
			Date q_modify_date, String open_status, String q_status) {
		super();
		this.q_no = q_no;
		this.q_cid = q_cid;
		this.q_cname = q_cname;
		this.user_no = user_no;
		this.id = id;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_count = q_count;
		this.q_create_date = q_create_date;
		this.q_modify_date = q_modify_date;
		this.open_status = open_status;
		this.q_status = q_status;
	}

	public Qna(int q_no, int q_cid, String q_cname, String id, String q_title, String q_content, int q_count,
			String open_status, Date q_create_date, Date q_modify_date, String q_status, List<Q_Reply> q_replyList) {
		super();
		this.q_no = q_no;
		this.q_cid = q_cid;
		this.q_cname = q_cname;
		this.id = id;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_count = q_count;
		this.open_status = open_status;
		this.q_create_date = q_create_date;
		this.q_modify_date = q_modify_date;
		this.q_status = q_status;
		this.q_replyList = q_replyList;
	}

	public int getQ_no() {
		return q_no;
	}

	public void setQ_no(int q_no) {
		this.q_no = q_no;
	}

	public int getQ_cid() {
		return q_cid;
	}

	public void setQ_cid(int q_cid) {
		this.q_cid = q_cid;
	}

	public String getQ_cname() {
		return q_cname;
	}

	public void setQ_cname(String q_cname) {
		this.q_cname = q_cname;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQ_title() {
		return q_title;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public int getQ_count() {
		return q_count;
	}

	public void setQ_count(int q_count) {
		this.q_count = q_count;
	}

	public Date getQ_create_date() {
		return q_create_date;
	}

	public void setQ_create_date(Date q_create_date) {
		this.q_create_date = q_create_date;
	}

	public Date getQ_modify_date() {
		return q_modify_date;
	}

	public void setQ_modify_date(Date q_modify_date) {
		this.q_modify_date = q_modify_date;
	}

	public String getOpen_status() {
		return open_status;
	}

	public void setOpen_status(String open_status) {
		this.open_status = open_status;
	}

	public String getQ_status() {
		return q_status;
	}

	public void setQ_status(String q_status) {
		this.q_status = q_status;
	}

	public Q_Image getQ_image() {
		return q_image;
	}

	public void setQ_image(Q_Image q_image) {
		this.q_image = q_image;
	}

	public List<Q_Reply> getQ_replyList() {
		return q_replyList;
	}

	public void setQ_replyList(List<Q_Reply> q_replyList) {
		this.q_replyList = q_replyList;
	}
	
	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	@Override
	public String toString() {
		return "Qna [q_no=" + q_no + ", q_cid=" + q_cid + ", q_cname=" + q_cname + ", user_no=" + user_no
				+ ", user_name=" + user_name + ", id=" + id + ", q_title=" + q_title + ", q_content=" + q_content
				+ ", q_count=" + q_count + ", open_status=" + open_status + ", q_create_date=" + q_create_date
				+ ", q_modify_date=" + q_modify_date + ", q_status=" + q_status + ", q_image=" + q_image
				+ ", q_replyList=" + q_replyList + ", reply_count=" + reply_count + "]";
	}


}
