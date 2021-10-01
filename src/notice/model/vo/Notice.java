package notice.model.vo;

import java.util.Date;
import java.util.List;

public class Notice {
	private int nno;			// 글번호
	private int userNo;			// 작성자 회원번호
	private String nickname;	// 작성자 닉네임
	private String nTitle;		// 공지사항 제목
	private String nContent;	// 공지사항 내용
	private Date createDate;	// 작성일
	private int ncount;			// 조회수
	private Date modifyDate;	// 수정일
	private List<Comment2> cmtList;
	
	/*  n_no	NUMBER
		user_no	NUMBER
		n_title	VARCHAR2(100 BYTE)
		n_content	VARCHAR2(4000 BYTE)
		N_DATE	DATE
		n_hit	NUMBER
		n_status	VARCHAR2(1 BYTE)
		n_modify_date	DATE
	 */
	
	public Notice() {}

	public Notice(int nno, String nickname, String nTitle, String nContent, Date createDate, int ncount,
			Date modifyDate) {
		super();
		this.nno = nno;
		this.nickname = nickname;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.createDate = createDate;
		this.ncount = ncount;
		this.modifyDate = modifyDate;
	}

	public Notice(int userNo, String nTitle, String nContent) {
		super();
		this.userNo = userNo;
		this.nTitle = nTitle;
		this.nContent = nContent;
	}

	public Notice(int nno, int userNo, String nickname, String nTitle, String nContent, Date createDate, int ncount,
			Date modifyDate) {
		super();
		this.nno = nno;
		this.userNo = userNo;
		this.nickname = nickname;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.createDate = createDate;
		this.ncount = ncount;
		this.modifyDate = modifyDate;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getNcount() {
		return ncount;
	}

	public void setNcount(int ncount) {
		this.ncount = ncount;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<Comment2> getCmtList() {
		return cmtList;
	}

	public void setCmtList(List<Comment2> cmtList) {
		this.cmtList = cmtList;
	}

	@Override
	public String toString() {
		return "Notice [nno=" + nno + ", userNo=" + userNo + ", nickname=" + nickname + ", nTitle=" + nTitle
				+ ", nContent=" + nContent + ", createDate=" + createDate + ", ncount=" + ncount + ", modifyDate="
				+ modifyDate + ", cmtList=" + cmtList + "]";
	}


}
