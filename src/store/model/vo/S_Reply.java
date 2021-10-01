package store.model.vo;

import java.util.Date;

public class S_Reply {
	private int comment1_no;
	private int s_no;
	private int user_no;
	private String nickname;
	private String gender;
	private String comment_info;
	private Date comment_create_date;
	private Date comment_modify_date;
	private int score;
	private String comment_status;
	private double avg_score;
	
	public S_Reply() {}

	public S_Reply(int comment1_no, int s_no, int user_no, String nickname, String comment_info,
			Date comment_create_date, Date comment_modify_date, int score, String comment_status) {
		super();
		this.comment1_no = comment1_no;
		this.s_no = s_no;
		this.user_no = user_no;
		this.nickname = nickname;
		this.comment_info = comment_info;
		this.comment_create_date = comment_create_date;
		this.comment_modify_date = comment_modify_date;
		this.score = score;
		this.comment_status = comment_status;
	}
	

	public S_Reply(int comment1_no, int user_no, int s_no, String nickname, String gender, String comment_info, Date comment_create_date,
			int score, String comment_status, double avg_score) {
		super();
		this.comment1_no = comment1_no;
		this.user_no = user_no;
		this.s_no = s_no;
		this.nickname = nickname;
		this.gender = gender;
		this.comment_info = comment_info;
		this.comment_create_date = comment_create_date;
		this.score = score;
		this.comment_status = comment_status;
		this.avg_score = avg_score;
	}

	public int getComment1_no() {
		return comment1_no;
	}

	public void setComment1_no(int comment1_no) {
		this.comment1_no = comment1_no;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getComment_info() {
		return comment_info;
	}

	public void setComment_info(String comment_info) {
		this.comment_info = comment_info;
	}

	public Date getComment_create_date() {
		return comment_create_date;
	}

	public void setComment_create_date(Date comment_create_date) {
		this.comment_create_date = comment_create_date;
	}

	public Date getComment_modify_date() {
		return comment_modify_date;
	}

	public void setComment_modify_date(Date comment_modify_date) {
		this.comment_modify_date = comment_modify_date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment_status() {
		return comment_status;
	}

	public void setComment_status(String comment_status) {
		this.comment_status = comment_status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(double avg_score) {
		this.avg_score = avg_score;
	}

	@Override
	public String toString() {
		return "S_Reply [comment1_no=" + comment1_no + ", s_no=" + s_no + ", user_no=" + user_no + ", nickname="
				+ nickname + ", gender=" + gender + ", comment_info=" + comment_info + ", comment_create_date="
				+ comment_create_date + ", comment_modify_date=" + comment_modify_date + ", score=" + score
				+ ", comment_status=" + comment_status + ", avg_score=" + avg_score + "]";
	}
	
}
