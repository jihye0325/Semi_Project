package notice.model.vo;

import java.util.Date;

public class Comment2 {
	private int cid;			// 댓글 고유번호
	private int cWriter;		// 댓글 작성자 userNo
	private String gender;		// 작성자 성별
	private String nickname;	// 작성자 닉네임
	private String cContent;	// 내용
	private Date createDate;	// 작성일
	private Date modifyDate;	// 수정일
	private int RefNno;			// 게시글 번호
	private int originCid;		// 원댓
	private String status;		// 상태
	
	/*
	 	COMMENT2_NO	NUMBER
		USER_NO	NUMBER 
		C_CREATE_DATE	DATE
		C_MODIFY_DATE	DATE
		C_CONTENT	VARCHAR2(600 BYTE)
		B_NO	NUMBER
		N_NO	NUMBER
		A_NO	NUMBER
		ORIGIN_RID	NUMBER
		C_STATUS	VARCHAR2(1 BYTE)
	*/
	
	public Comment2() {}

	public Comment2(int cid, int cWriter, String nickname, String cContent, Date modifyDate, int refNno) {
		super();
		this.cid = cid;
		this.cWriter = cWriter;
		this.nickname = nickname;
		this.cContent = cContent;
		this.modifyDate = modifyDate;
		RefNno = refNno;
	}
	
	/* 기존 selectCmtList
	public Comment2(int cid, int cWriter, String gender, String nickname, String cContent, Date modifyDate,
			int refNno) {
		super();
		this.cid = cid;
		this.cWriter = cWriter;
		this.gender = gender;
		this.nickname = nickname;
		this.cContent = cContent;
		this.modifyDate = modifyDate;
		RefNno = refNno;
	} */
	
	

	public Comment2(int cWriter, String cContent, int refNno, int originCid) {
		super();
		this.cWriter = cWriter;
		this.cContent = cContent;
		RefNno = refNno;
		this.originCid = originCid;
	}

	public Comment2(int cid, int cWriter, String gender, String nickname, String cContent, int refNno,
			int originCid, Date modifyDate) {
		super();
		this.cid = cid;
		this.cWriter = cWriter;
		this.gender = gender;
		this.nickname = nickname;
		this.cContent = cContent;
		this.modifyDate = modifyDate;
		RefNno = refNno;
		this.originCid = originCid;
	}

	public Comment2(int cid, String cContent) {
		super();
		this.cid = cid;
		this.cContent = cContent;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}


	public int getRefNno() {
		return RefNno;
	}

	public void setRefNno(int refNno) {
		RefNno = refNno;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public int getcWriter() {
		return cWriter;
	}

	public void setcWriter(int cWriter) {
		this.cWriter = cWriter;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getOriginCid() {
		return originCid;
	}

	public void setOriginCid(int originCid) {
		this.originCid = originCid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "Comment2 [cid=" + cid + ", cWriter=" + cWriter + ", gender=" + gender + ", nickname=" + nickname
				+ ", cContent=" + cContent + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", RefNno="
				+ RefNno + ", originCid=" + originCid + ", status=" + status + "]";
	}


}
