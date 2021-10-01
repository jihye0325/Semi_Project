package booking.model.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import member.model.vo.Member;

public class R_comment1 {
/*COMMENT_NO	NUMBER
S_NO	NUMBER
R_NO	NUMBER
USER_NO	NUMBER
COMMENT_INFO	VARCHAR2(200 BYTE)
COMMENT_CREATE_DATE	DATE
COMMENT_MODIFY_DATE	DATE
LIKE	NUMBER
COMMENT_STATUS	VARCHAR2(1 BYTE)*/
	private int comment_no; //댓글 번호
	private int s_no; // 식당고유번호
	private int r_no; // 숙소고유번호 = 게시글번호
	private int user_no; //작성자고유번호
	private String user_name; //작성자 이름
	private String nickName;
	private String comment_info; // 댓글내용
	private Date create_date; //댓글 작성날
	private Date comment_modify_date; //댓글 수정날
	private int score;	//좋아요 개수
	private String comment_status; // 댓글 활성화
	private String gender;
	private double avg_score;
	private List<Member> member  = new ArrayList<>();
	
	
	
	public R_comment1() { }

	public R_comment1(int comment_no, int r_no, int user_no,String nickName, String comment_info, Date create_date,
			Date comment_modify_date, int score, String comment_status) {
		super();
		this.comment_no = comment_no;
		this.r_no = r_no;
		this.user_no = user_no;
		this.nickName=nickName;
		this.comment_info = comment_info;
		this.create_date = create_date;
		this.comment_modify_date = comment_modify_date;
		this.score = score;
		this.comment_status = comment_status;
		
	}
	
	


	//게시글 당 댓글 리스트 조회
	public R_comment1(int comment_no, int r_no, int user_no,String user_name,String nickName, String comment_info,
			Date create_date, int score,String gender,double avg_score ) {
		super();
		this.comment_no = comment_no;
		this.r_no = r_no;
		this.user_no = user_no;
		this.user_name = user_name;
		this.nickName=nickName;
		this.comment_info = comment_info;
		this.create_date = create_date;
		this.score = score;
		this.gender=gender;
		this.avg_score=avg_score;
	}



	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getS_no() {
		return s_no;
	}

	public void setS_no(int s_no) {
		this.s_no = s_no;
	}

	public int getR_no() {
		return r_no;
	}

	public void setR_no(int r_no) {
		this.r_no = r_no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getComment_info() {
		return comment_info;
	}

	public void setComment_info(String comment_info) {
		this.comment_info = comment_info;
	}
	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}


	public Date getComment_modify_date() {
		return comment_modify_date;
	}

	public void setComment_modify_date(Date comment_modify_date) {
		this.comment_modify_date = comment_modify_date;
	}

	
	public String getComment_status() {
		return comment_status;
	}

	public void setComment_status(String comment_status) {
		this.comment_status = comment_status;
	}
	
	

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	

	public double getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(double avg_score) {
		this.avg_score = avg_score;
	}

	@Override
	public String toString() {
		return "R_comment1 [comment_no=" + comment_no + ", s_no=" + s_no + ", r_no=" + r_no + ", user_no=" + user_no
				+ ", user_name=" + user_name + ", nickName=" + nickName + ", comment_info=" + comment_info
				+ ", create_date=" + create_date + ", comment_modify_date=" + comment_modify_date + ", score=" + score
				+ ", comment_status=" + comment_status + ", gender=" + gender + ", avg_score=" + avg_score + ", member="
				+ member + "]";
	}

	

	
}
